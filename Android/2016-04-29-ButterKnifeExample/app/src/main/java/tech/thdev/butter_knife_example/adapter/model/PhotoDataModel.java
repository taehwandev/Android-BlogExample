package tech.thdev.butter_knife_example.adapter.model;

import tech.thdev.butter_knife_example.network.domain.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoDataModel {

    void add(Photo photo);

    void add(Photo photo, boolean isNotify);

    void remove(int position);

    Photo getPhotoItem(int position);

    int getSize();
}
