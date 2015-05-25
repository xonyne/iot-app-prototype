package ch.keutsa.prototype.model;

import java.util.Date;

import ch.keutsa.prototype.model.basic.MacAddress;

/**
 * Created by SoullessStone on 05.05.2015.
 */
@Deprecated
public final class RequestBundle {
    public final MacAddress masterMac;
    public final MacAddress clientMac;
    public final Date requestTime;

    public RequestBundle(MacAddress masterMac, MacAddress clientMac, Date requestTime) {
        this.masterMac = masterMac;
        this.clientMac = clientMac;
        this.requestTime = requestTime;
    }
}
