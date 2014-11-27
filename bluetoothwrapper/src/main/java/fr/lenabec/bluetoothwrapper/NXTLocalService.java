package fr.lenabec.bluetoothwrapper;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Process;
import android.os.Looper;
import android.os.Message;


/**
 * Created by Briag Le Nabec on 27/11/2014.
 */
public class NXTLocalService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private Messenger mMessenger;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceNXT", Process.THREAD_PRIORITY_BACKGROUND);

        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        mMessenger = new Messenger(mServiceHandler);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
