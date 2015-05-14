package ch.keutsa.prototype.model.basic;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class MacAddress {
    private static final String MAC_REGEX_DEFINITION = "^([a-fA-F0-9]{2}[:-]{1}){5}[a-fA-F0-9]{2}$";
    private final String mac;

    public MacAddress(String mac) {
        if (isValid(mac))
            this.mac = mac;
        else
            throw new IllegalArgumentException();
    }

    private boolean isValid(String string) {
        return string.matches(MAC_REGEX_DEFINITION);
    }

}
