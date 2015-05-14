package ch.keutsa.prototype.model.basic;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class Percent {
    private final int value;// TODO (erfasst von Michel): change to byte

    public Percent(int initialValue) {
        if (initialValue <= 100)
            this.value = initialValue;
        else
            throw new IllegalArgumentException();
    }

    public int getValue() {
        return value;
    }
}
