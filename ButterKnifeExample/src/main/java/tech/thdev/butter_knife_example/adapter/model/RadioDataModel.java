package tech.thdev.butter_knife_example.adapter.model;

import tech.thdev.butter_knife_example.bean.RadioItem;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public interface RadioDataModel {

    void add(RadioItem name);

    RadioItem remove(int position);

    RadioItem getRadioItem(int position);

    int getSize();
}
