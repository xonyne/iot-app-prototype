package ch.keutsa.prototype.networking;

/**
 * Created by SoullessStone on 14.05.2015.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // TODO (erfasst von Michel): Send a mqtt-message to a broker
        String status = NetworkUtil.getConnectivityStatusString(context, true);
        Log.v(NetworkChangeReceiver.class.getName(), "Network changed to " + NetworkUtil.getConnectivityStatusString(context));
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}