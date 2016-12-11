package tech.thdev.app.data;

import android.support.annotation.DrawableRes;

/**
 * Created by Tae-hwan on 09/12/2016.
 */

public class BottomData {

    public String title;
    public int iconRes;

    public BottomData(String title, @DrawableRes int iconRes) {
        this.title = title;
        this.iconRes = iconRes;
    }
}
