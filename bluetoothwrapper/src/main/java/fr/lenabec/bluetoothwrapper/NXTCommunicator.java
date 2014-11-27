package fr.lenabec.bluetoothwrapper;

import java.io.IOException;

/**
 * Created by Briag Le Nabec on 27/11/2014.
 */
public class NXTCommunicator extends BluetoothCommunicator {

    Response sendCommand(Command command) throws IOException {
        return new Response(command.getType(),send(command.getCommand(), command.getReplyLength()));
    }
}
