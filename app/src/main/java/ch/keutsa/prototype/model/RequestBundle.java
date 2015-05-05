package ch.keutsa.prototype.model;

import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.CustomTimestamp;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class RequestBundle {
    public final MacAddress masterMac;
    public final MacAddress clientMac;
    public final CustomTimestamp requestTime;

    public RequestBundle(MacAddress masterMac, MacAddress clientMac, CustomTimestamp requestTime) {
        this.masterMac = masterMac;
        this.clientMac = clientMac;
        this.requestTime = requestTime;
    }
}
