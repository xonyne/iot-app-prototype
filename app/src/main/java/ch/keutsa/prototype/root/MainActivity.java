package ch.keutsa.prototype.root;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ch.keutsa.prototype.networking.NetworkUtil;
import ch.keutsa.prototype.service.TestServiceController;
import prototype.keutsa.ch.root.R;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();
    private static boolean wifiConnected = false;
    private static boolean mobileConnected = false;
    private TestServiceController testServiceController = new TestServiceController();
    private Log log;
    private TextView serviceStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.test_action);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log.i(NetworkUtil.getConnectivityStatusString(getApplicationContext()));
            }
        });
        final Button button2 = (Button) findViewById(R.id.clear_action);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log.clear();
            }
        });
        final Button button3 = (Button) findViewById(R.id.serviceControlButton);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleService();
            }
        });
        TextView tv = (TextView) findViewById(R.id.outputText);
        tv.setMovementMethod(new ScrollingMovementMethod());

        serviceStatus = (TextView) findViewById(R.id.serviceStatusTextview);
        if (testServiceController.isTestServiceRunning(getApplicationContext())) {
            serviceStatus.setText("Started");
        } else {
            serviceStatus.setText("Stopped");
        }
        log = new Log();
    }

    private void toggleService() {
        android.util.Log.v(TAG, "Started toggling Service: " + testServiceController.isTestServiceRunning(getApplicationContext()));
        if (!testServiceController.isTestServiceRunning(getApplicationContext())) {
            testServiceController.startService(this);
            serviceStatus.setText("Started");
        } else {
            testServiceController.stopService(this);
            serviceStatus.setText("Stopped");
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