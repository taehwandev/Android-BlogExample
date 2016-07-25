package tech.thdev.recycler_lambda_example.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import tech.thdev.recycler_lambda_example.R;

/**
 * Created by Tae-hwan on 4/15/16.
 * <p>
 * Bottom sheet dialog custom.
 */
public class CustomBottomSheetDialog extends AppCompatDialog {

    public CustomBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public CustomBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected CustomBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(wrapInBottomSheet(layoutResID, null, null));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(wrapInBottomSheet(0, view, params));
    }

    private View wrapInBottomSheet(@LayoutRes int layoutResId, View view, ViewGroup.LayoutParams params) {
        final CoordinatorLayout coordinator = (CoordinatorLayout) View.inflate(getContext(), R.layout.bottom_sheet_dialog, null);
        if (layoutResId != 0 && view == null) {
            view = getLayoutInflater().inflate(layoutResId, coordinator, false);
        }

        FrameLayout bottomSheet = (FrameLayout) coordinator.findViewById(R.id.design_bottom_sheet);


        return coordinator;
    }
}
