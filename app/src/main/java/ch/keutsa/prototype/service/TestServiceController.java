package ch.keutsa.prototype.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by SoullessStone on 13.05.2015.
 */
public class TestServiceController {
    private static final String TAG = TestServiceController.class.getName();

    public void startService(Context c) {
        c.startService(new Intent(c, TestService.class));
    }

    public boolean stopService(Context c) {
        return c.stopService(new Intent(c, TestService.class));
    }

    public boolean isTestServiceRunning(Context c) {
        return isServiceRunning(TestService.class.getName(), c);
    }

    private boolean isServiceRunning(String serviceName, Context c) {
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
