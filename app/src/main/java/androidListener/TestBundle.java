package androidListener;


import java.io.Serializable;

/**
 * Created by SoullessStone on 24.05.2015.
 */
public class TestBundle implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5937367717582432263L;
    private String client = "halloooo";

    @Override
    public String toString() {
        return "TestBundle{" +
                "client='" + client + '\'' +
                '}';
    }
}