package ch.keutsa.prototype.networking;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by SoullessStone on 24.05.2015.
 */
public class MqttHandler {
    private static final String TAG = MqttHandler.class.getName();
    private static final String MQTT_TOPIC = "ch.keutsa.prototype";
    private static final int MQTT_QOS = 2;
    private static final String MQTT_BROKER = "tcp://iot.eclipse.org:1883";

    public static void sendMessage(String content, String clientId){
        sendMessage(MQTT_TOPIC, content, clientId);
    }

    public static void sendMessage(String topic, String content, String clientId){
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(MQTT_BROKER, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // TODO (erfasst von Michel): evtl. lastWill usw.
            connOpts.setCleanSession(true);
            Log.v(TAG, "Connecting to broker: "+MQTT_BROKER);
            sampleClient.connect(connOpts);
            Log.v(TAG, "Connected");
            Log.v(TAG, "Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(MQTT_QOS);
            sampleClient.publish(topic, message);
            Log.v(TAG, "Message published");
            sampleClient.disconnect();
            Log.v(TAG, "Disconnected");
        } catch(MqttException me) {
            Log.e(TAG, "reason "+me.getReasonCode());
            Log.e(TAG, "msg "+me.getMessage());
            Log.e(TAG, "loc "+me.getLocalizedMessage());
            Log.e(TAG, "cause "+me.getCause());
            Log.e(TAG, "excep "+me);
            me.printStackTrace();
        }
    }

}
