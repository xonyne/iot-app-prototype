package ch.keutsa.prototype.model.basic;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public class Percent {
    private Integer value;
    public Percent(int initialValue){
        this.value = initialValue;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
