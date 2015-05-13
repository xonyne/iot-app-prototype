package ch.keutsa.prototype.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SoullessStone on 13.05.2015.
 */
public class TestService extends Service{
    private static final int RUN_INTERVAL = 30 * 1000;
    private static final int START_DELAY = 10;
    private Timer timer;



    // Tag serves to identify the origin of the Log
    private static final String TAG = TestService.class.getName();

    @Override
    public void onCreate() {
        Log.d(TAG, "Starting Service");

        // Initializing all the listeners

        // Starting the scheduled task
        Log.v(TAG, "Starting timertasks");
        timer = new Timer();
        timer.schedule(new ScheduledSender(), START_DELAY, RUN_INTERVAL);
        Log.v(TAG, "Timertask got started");

    }

    private class ScheduledSender extends TimerTask{

        @Override
        public void run() {
            Log.v(TAG, "Woke up, did something and went back to sleep");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        // Removing the location update requests
        Log.v(TAG, "Service stopped");
    }
}
