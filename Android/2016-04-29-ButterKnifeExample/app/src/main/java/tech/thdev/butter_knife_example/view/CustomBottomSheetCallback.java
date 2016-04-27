package tech.thdev.butter_knife_example.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

/**
 * Created by Tae-hwan on 4/27/16.
 */
public class CustomBottomSheetCallback extends BottomSheetBehavior.BottomSheetCallback {

    private FloatingActionButton floatingActionButton;

    public CustomBottomSheetCallback(FloatingActionButton floatingActionButton) {
        this.floatingActionButton = floatingActionButton;
    }

    @Override
    public void onStateChanged(@NonNull View bottomSheet, int newState) {

    }

    @Override
    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//        if (slideOffset > 0.8) {
//            if (tmpBottomSlideOffset > slideOffset) {
//                floatingActionButton.show();
//
//            } else {
//                floatingActionButton.hide();
//            }
//
//        } else {
//            floatingActionButton.show();
//        }
//
//        tmpBottomSlideOffset = slideOffset;
    }
}
