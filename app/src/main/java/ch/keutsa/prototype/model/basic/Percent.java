package ch.keutsa.prototype.model.basic;

/**
 * Created by SoullessStone on 05.05.2015.
 */
@Deprecated
public final class Percent {
    private final byte value;

    public Percent(byte initialValue) {
        if (initialValue <= 100)
            this.value = initialValue;
        else
            throw new IllegalArgumentException();
    }

    public int getValue() {
        return value;
    }
}
