package tech.thdev.lockerappexample;

/**
 * Created by Tae-hwan on 8/17/16.
 */

public class Data {

    private Data() {

    }

    private static Data data;

    public static Data getInstance() {
        if (data == null) {
            synchronized (Data.class) {
                if (data == null) {
                    data = new Data();
                }
            }
        }
        return data;
    }

    public String url;
}
