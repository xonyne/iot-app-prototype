package ch.keutsa.prototype.networking;

/**
 * Created by SoullessStone on 14.05.2015.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


public class NetworkUtil {

    public static String getConnectivityStatusString(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        String output = "";
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                output += "Wifi Connection:\n";
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    output += ("getSSID: " + connectionInfo.getSSID());
                    output += ("getBSSID: " + connectionInfo.getBSSID());
                    output += ("getMacAddress: " + connectionInfo.getMacAddress());
                    //output += ("getFrequency: " + connectionInfo.getFrequency());
                    output += ("getHiddenSSID: " + connectionInfo.getHiddenSSID());
                    output += ("getIpAddress: " + connectionInfo.getIpAddress());
                    output += ("getSupplicantState: " + connectionInfo.getSupplicantState());
                    output += ("getLinkSpeed: " + connectionInfo.getLinkSpeed());
                    output += ("getRssi: " + connectionInfo.getRssi());
                    output += ("getNetworkId: " + connectionInfo.getNetworkId());
                }
            } else if (mobileConnected) {
                output += "Mobile Connection: " + NetworkUtil.getNetworkType(context);
            }
        } else {
            output += "No mobile or wifi connection...";
        }
        return output;
    }

    public static String getShortConnectivityStatusString(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        String output = "";
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                output += "Connected to Network: ";
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    output += ("getSSID: " + connectionInfo.getSSID());
                }
            } else if (mobileConnected) {
                output += "Mobile Connection: " + NetworkUtil.getNetworkType(context);
            }
        } else {
            output += "No mobile or wifi connection...";
        }
        return output;
    }

    private static String getNetworkType(Context context) {
        TelephonyManager teleMan = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = teleMan.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return "eHRPD";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "EVDO rev. 0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "EVDO rev. A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return "EVDO rev. B";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "HSPA+";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "iDen";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "Unknown";
        }
        throw new RuntimeException("New type of network");
    }
}
