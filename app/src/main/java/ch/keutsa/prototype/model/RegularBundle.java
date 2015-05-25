package ch.keutsa.prototype.model;


import java.io.Serializable;
import java.util.Date;

import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;

/**
 * Created by SoullessStone on 05.05.2015.
 */
// TODO (erfasst von Michel): final okay für alle?
public final class RegularBundle implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -1695384745702291446L;
    public final MacAddress clientMac;
    public final SSID clientSSID;
    public final Location clientLocation;
    public final Date clientTime;
    public final ConnectionCode connectionCode;

    public RegularBundle(){
        this(new MacAddress("00-80-41-ae-fd-7e"), new SSID("Example"), new Location(0.0, 0.0), new Date(), ConnectionCode.MOBILE);
    }

    public RegularBundle(MacAddress clientMac, SSID clientSSID, Location clientLocation, Date clientTime, ConnectionCode connectionCode) {
        this.clientMac = clientMac;
        this.clientSSID = clientSSID;
        this.clientLocation = clientLocation;
        this.clientTime = clientTime;
        this.connectionCode = connectionCode;
    }

    @Override
    public String toString() {
        return "RegularBundle [\n   clientMac=" + clientMac + "\n   clientSSID="
                + clientSSID + "\n   clientLocation=" + clientLocation
                + "\n   clientTime=" + clientTime + "\n   connectionCode="
                + connectionCode + "\n]";
    }

}
