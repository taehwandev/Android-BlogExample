package tech.thdev.recycler_lambda_example;

import android.support.annotation.DrawableRes;

import tech.thdev.recycler_lambda_example.base.AdapterItem;

/**
 * Created by Tae-hwan on 4/12/16.
 */
public class Image extends AdapterItem {

    @DrawableRes
    public int res;

    public Image(@DrawableRes int res) {
        this.res = res;
    }
}
