package ch.keutsa.prototype.model;

import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.CustomTimestamp;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class RegularBundle {
    public final MacAddress clientMac;
    public final SSID clientSSID;
    public final Location clientLocation;
    public final CustomTimestamp clientTime;
    public final ConnectionCode connectionCode;

    public RegularBundle(MacAddress clientMac, SSID clientSSID, Location clientLocation, CustomTimestamp clientTime, ConnectionCode connectionCode) {
        this.clientMac = clientMac;
        this.clientSSID = clientSSID;
        this.clientLocation = clientLocation;
        this.clientTime = clientTime;
        this.connectionCode = connectionCode;
    }
}
