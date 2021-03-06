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
public class TestService extends Service {
    private static final int RUN_INTERVAL = 60 * 1000;
    private static final int START_DELAY = 10;
    private static final String TAG = TestService.class.getName();
    private Timer timer;

    @Override
    public void onCreate() {
        timer = new Timer();
        timer.schedule(new ScheduledSender(), START_DELAY, RUN_INTERVAL);
        Log.v(TAG, "Service started");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        timer.purge();
        Log.v(TAG, "Service stopped");
    }

    private class ScheduledSender extends TimerTask {

        @Override
        public void run() {
            Log.v(TAG, "Woke up, did something and went back to sleep");
        }
    }
}
