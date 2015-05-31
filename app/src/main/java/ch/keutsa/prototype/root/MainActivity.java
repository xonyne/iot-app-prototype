package ch.keutsa.prototype.root;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;

import ch.keutsa.prototype.model.RegularBundle;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.networking.MqttHandler;
import ch.keutsa.prototype.networking.NetworkUtil;
import ch.keutsa.prototype.networking.SerialHelper;
import ch.keutsa.prototype.service.TestServiceController;
import prototype.keutsa.ch.root.R;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();
    private TestServiceController testServiceController = new TestServiceController();
    private StatusTextviewWriter writer;
    private TextView serviceStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.test_action);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writer.clear();
                writer.i(NetworkUtil.getConnectivityStatusString(getApplicationContext()));
            }
        });
        final Button button2 = (Button) findViewById(R.id.clear_action);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writer.clear();
            }
        });
        final Button button3 = (Button) findViewById(R.id.serviceControlButton);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleService();
            }
        });
        final Button button4 = (Button) findViewById(R.id.sendMQTTButton);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            RegularBundle bundle = new RegularBundle(NetworkUtil.getMacAddress(getApplicationContext()), NetworkUtil.getSSID(getApplicationContext()), new Location(1898.0, 1898.0), new Date(), NetworkUtil.getConnectionCode(getApplicationContext()));
                            String content = SerialHelper.toString(bundle);
                            MqttHandler.sendMessage(content, "CLIENTID");
                            Log.v(TAG, "Finished");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
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

        writer = new StatusTextviewWriter();
    }

    private void toggleService() {
        android.util.Log.v(TAG, "Toggling Service");
        if (!testServiceController.isTestServiceRunning(getApplicationContext())) {
            testServiceController.startService(this);
            android.util.Log.v(TAG, "Service was not running, started it");
            serviceStatus.setText("Started");
        } else {
            testServiceController.stopService(this);
            android.util.Log.v(TAG, "Service was  running, stopped it");
            serviceStatus.setText("Stopped");
        }
    }

    class StatusTextviewWriter {
        TextView tv;

        public StatusTextviewWriter() {
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