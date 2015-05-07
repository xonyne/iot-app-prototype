package ch.keutsa.prototype.model;

import java.util.ArrayList;

import ch.keutsa.prototype.model.basic.CustomTimestamp;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.MobileConnectionInformation;
import ch.keutsa.prototype.model.basic.Percent;
import ch.keutsa.prototype.model.basic.SSID;
import ch.keutsa.prototype.model.basic.WlanConnectionInformation;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class RequestAnswerBundle {
    public final MacAddress masterMac;
    public final MacAddress clientMac;
    public final CustomTimestamp clientTime;
    public final Location clientLocation;
    public final ArrayList<SSID> surroundingSSIDs;
    public final WlanConnectionInformation wlanInfo;
    public final MobileConnectionInformation mobileInfo;
    public final Percent remainingAccu;

    public RequestAnswerBundle(MacAddress masterMac, MacAddress clientMac, CustomTimestamp clientTime, Location clientLocation, ArrayList<SSID> surroundingSSIDs, WlanConnectionInformation wlanInfo, MobileConnectionInformation mobileInfo, Percent remainingAccu) {
        this.masterMac = masterMac;
        this.clientMac = clientMac;
        this.clientTime = clientTime;
        this.clientLocation = clientLocation;
        this.surroundingSSIDs = surroundingSSIDs;
        this.wlanInfo = wlanInfo;
        this.mobileInfo = mobileInfo;
        this.remainingAccu = remainingAccu;
    }
}
