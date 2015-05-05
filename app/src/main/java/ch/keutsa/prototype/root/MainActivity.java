
package ch.keutsa.prototype.root;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import prototype.keutsa.ch.root.R;

public class MainActivity extends FragmentActivity {

    private static boolean wifiConnected = false;
    private static boolean mobileConnected = false;
    private Log log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.test_action);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkNetworkConnection();
            }
        });
        final Button button2 = (Button) findViewById(R.id.clear_action);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log.clear();
            }
        });
        TextView tv = (TextView) findViewById(R.id.outputText);
        tv.setMovementMethod(new ScrollingMovementMethod());

        log = new Log();
    }

    private String networkType() {
        TelephonyManager teleMan = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
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

    private void checkNetworkConnection() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            log.clear();
            if (wifiConnected) {
                log.i(getString(R.string.wifi_connection));
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    log.i("getSSID: " + connectionInfo.getSSID());
                    log.i("getBSSID: " + connectionInfo.getBSSID());
                    log.i("getMacAddress: " + connectionInfo.getMacAddress());
                    //Log.i("getFrequency: " + connectionInfo.getFrequency());
                    log.i("getHiddenSSID: " + connectionInfo.getHiddenSSID());
                    log.i("getIpAddress: " + connectionInfo.getIpAddress());
                    log.i("getSupplicantState: " + connectionInfo.getSupplicantState());
                    log.i("getLinkSpeed: " + connectionInfo.getLinkSpeed());
                    log.i("getRssi: " + connectionInfo.getRssi());
                    log.i("getNetworkId: " + connectionInfo.getNetworkId());
                }
            } else if (mobileConnected) {
                log.i(getString(R.string.mobile_connection));
                log.i(networkType());
            }
        } else {
            log.i(getString(R.string.no_wifi_or_mobile));
        }
    }

    class Log {
        TextView tv;

        public Log() {
            tv = (TextView) findViewById(R.id.outputText);
        }

        public void i(String message) {
            tv.setText(tv.getText() + "\n" + message);
        }

        public void clear() {
            tv.setText("");
        }
    }
}