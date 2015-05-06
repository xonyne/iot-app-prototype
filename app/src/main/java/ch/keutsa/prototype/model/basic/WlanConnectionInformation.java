package ch.keutsa.prototype.model.basic;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class WlanConnectionInformation {
    public final MacAddress macAddress;
    public final SSID ssid;
    // TODO (erfasst von Michel): Add more information

    public WlanConnectionInformation(MacAddress macAddress, SSID ssid) {
        this.macAddress = macAddress;
        this.ssid = ssid;
    }
}