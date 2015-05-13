package ch.keutsa.prototype.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by SoullessStone on 13.05.2015.
 */
public class TestServiceContoller {
    private static final String TAG = TestServiceContoller.class.getName();

    public void startService(Context c) {
        // Starts the service
        c.startService(new Intent(c, TestService.class));
    }

    public boolean stopService(Context c) {
        Log.i(TAG, "Stopping service");
        return c.stopService(new Intent(c, TestService.class));
    }

    public boolean isTestServiceRuning(Context c) {
        return isServiceRunning(TestService.class.getName(), c);
    }

    public boolean isServiceRunning(String serviceName, Context c) {
        Log.v(TAG, "Trying to get running of service" + serviceName);
        try {
            ActivityManager manager = (ActivityManager) c.getSystemService(Activity.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceName.equals(service.service.getClassName())) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "Error while get service running");
        }
        return false;
    }

}
