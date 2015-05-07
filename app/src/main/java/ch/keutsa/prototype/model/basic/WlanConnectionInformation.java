package ch.keutsa.prototype.model.basic;

import android.net.wifi.SupplicantState;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class WlanConnectionInformation {
    public final SSID ssid;
    public final String bssid;
    public final MacAddress macAddress;
    public final boolean hiddenSSID;
    public final int ipAddress;
    public final SupplicantState supplicantState;
    public final int linkSpeed;
    public final int rssi;
    public final int networkId;

    // TODO (erfasst von Michel): Add more information

    public WlanConnectionInformation(SSID ssid, String bssid, MacAddress macAddress, boolean hiddenSSID, int ipAddress, SupplicantState supplicantState, int linkSpeed, int rssi, int networkId) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.macAddress = macAddress;
        this.hiddenSSID = hiddenSSID;
        this.ipAddress = ipAddress;
        this.supplicantState = supplicantState;
        this.linkSpeed = linkSpeed;
        this.rssi = rssi;
        this.networkId = networkId;
    }
}
