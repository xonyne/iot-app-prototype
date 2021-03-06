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

import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;


public class NetworkUtil {

    public static String getConnectivityStatusString(Context context){
        return getConnectivityStatusString(context, false);
    }

    public static String getConnectivityStatusString(Context context, boolean shortVersion) {
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
                    output += ("SSID: " + connectionInfo.getSSID());
                    if (!shortVersion) {
                        output += ("\ngetBSSID: " + connectionInfo.getBSSID() + "\n");
                        output += ("getMacAddress: " + connectionInfo.getMacAddress() + "\n");
                        //output += ("getFrequency: " + connectionInfo.getFrequency() + "\n");
                        output += ("getHiddenSSID: " + connectionInfo.getHiddenSSID() + "\n");
                        output += ("getIpAddress: " + connectionInfo.getIpAddress() + "\n");
                        output += ("getSupplicantState: " + connectionInfo.getSupplicantState() + "\n");
                        output += ("getLinkSpeed: " + connectionInfo.getLinkSpeed() + "\n");
                        output += ("getRssi: " + connectionInfo.getRssi() + "\n");
                        output += ("getNetworkId: " + connectionInfo.getNetworkId() + "\n");
                    }
                }
            } else if (mobileConnected) {
                output += "Mobile Connection: " + NetworkUtil.getNetworkType(context);
            }
        } else {
            output += "No mobile or wifi connection...";
        }
        return output;
    }

    public static SSID getSSID(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            if (wifiConnected) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return new SSID(connectionInfo.getSSID());
                }
            }
        }
        return new SSID("No SSID");
    }

    public static MacAddress getMacAddress(Context context){
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        return new MacAddress(address);
    }

    public static ConnectionCode getConnectionCode(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                return ConnectionCode.WLAN;
            } else if (mobileConnected) {
                return ConnectionCode.MOBILE;
            }
        } else {
            return ConnectionCode.OFFLINE;
        }
        return ConnectionCode.UNKNOWN;
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
