package ch.keutsa.prototype.model.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class MacAddress {
    private final String mac = "";
    private static final String MAC_REGEX_DEFINITION = "^([0-9A-F]{2}[-]){5}([0-9A-F]{2})$";

    public MacAddress(String mac){
        System.out.println(isValid(mac));
    }

    private boolean isValid(String mac){
        // FIXME (erfasst von Michel): Implementation (via regex)
        return mac.matches(MAC_REGEX_DEFINITION);
    }
}
