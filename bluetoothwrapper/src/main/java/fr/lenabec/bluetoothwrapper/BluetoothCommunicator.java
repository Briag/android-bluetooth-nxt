package fr.lenabec.bluetoothwrapper;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * Created by Briag Le Nabec on 27/11/2014.
 */
public class BluetoothCommunicator {

    private static final String TAG = BluetoothCommunicator.class.getSimpleName();

    private OutputStream out;
    private InputStream in;
    private BluetoothSocket mSocket;

    private boolean isConnected;
    private String mDeviceMacAdress;


    public void connect(String deviceMacAdress) throws IOException {
        mDeviceMacAdress = deviceMacAdress;
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(deviceMacAdress);
        BluetoothSocket tmp = null;

        try {
            Method m = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
            tmp = (BluetoothSocket) m.invoke(device, 1);
        } catch (Exception e) {
            Log.d(TAG, "Socket creation exception:" + e.getMessage());
            return;
        }
        
        mSocket = tmp;
        mSocket.connect();
        out = mSocket.getOutputStream();
        in  = mSocket.getInputStream();
        isConnected = true;
    }

    public void disconnect() throws IOException {
        if (isConnected) {
            isConnected = false;
            in.close();
            out.close();
            mSocket.close();
        }
    }

    public void reconnect() throws IOException {
        disconnect();
        connect(mDeviceMacAdress);
    }
    public byte[] send(byte[] message, int replyLength) throws IOException {

        int LSB = message.length;
        int MSB = message.length >>> 8;

        if(out == null) {
            return new byte[0];
        }
        try {
            /**
             * Data package in Bluetooth is
             * | Length LSB | Length MSB | CommandType | Command | ...
             */
            out.write((byte) LSB);
            out.write((byte) MSB);

            out.write(message);
            out.flush();

            if(replyLength == 0) {
                return new byte[0];
            }

            byte[] reply = null;
            int lengthLSB = -1;

            if(in == null) {
                return new byte[0];
            }

            do {
                lengthLSB = in.read();
            } while(lengthLSB < 0);

            int lengthMSB = in.read();
            int length = (0xFF & lengthLSB) | ((0xFF & lengthMSB) << 8);

            reply = new byte[length];

            int readLength = in.read(reply);
            if(readLength != length) {
                throw new IOException("Unexpected reply length");
            }
            return (reply == null) ? new byte[0] : reply;
        } catch (IOException e) {
            throw e;
        }
    }
    public byte[] read() throws IOException {
        try {
            /**
             * Data package in Bluetooth is
             * | Length LSB | Length MSB | CommandType | Command | ...
             */

            int lsb = in.read();
            if(lsb < 0) {
                return null;
            }
            int msb = in.read();
            if(msb < 0) {
                return null;
            }

            // Construct the length from 2 byte
            int length = (msb << 8) | lsb;

            byte message[] = new byte[length];
            for(int i = 0; i < length; i++) {
                message[i] = (byte) in.read();
            }

            return message;
        } catch (IOException e) {
            throw e;
        }
    }
}
