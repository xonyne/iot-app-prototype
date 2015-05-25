package ch.keutsa.prototype.networking;

/**
 * Created by SoullessStone on 14.05.2015.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;

import ch.keutsa.prototype.model.RegularBundle;
import ch.keutsa.prototype.model.basic.Location;


public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context, true);
        Log.v(NetworkChangeReceiver.class.getName(), "Network changed to " + NetworkUtil.getConnectivityStatusString(context));
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();

        RegularBundle bundle = new RegularBundle(NetworkUtil.getMacAddress(context), NetworkUtil.getSSID(context), new Location(1898.0, 1898.0), new Date(), NetworkUtil.getConnectionCode(context));
        String content = null;
        try {
            content = SerialHelper.toString(bundle);
            MqttHandler.sendMessage(content, "CLIENTID");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}