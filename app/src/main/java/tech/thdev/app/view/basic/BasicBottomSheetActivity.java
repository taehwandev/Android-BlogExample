package tech.thdev.app.view.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.app.R;
import tech.thdev.base.view.BaseActivity;

/**
 * Created by tae-hwan on 07/12/2016.
 */

public class BasicBottomSheetActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rl_bottom_sheet)
    RelativeLayout rlBottomSheet;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        setSupportActionBar(toolbar);

        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        setTitle(R.string.basic_sample_bottom_title);
                        break;

                    default:
                        setTitle(R.string.label_basic_activity);
                        break;
                }
                Log.d("TAG", "newState " + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // Slide ...
                // 1이면 완전 펼쳐진 상태
                // 0이면 peekHeight인 상태
                // -1이면 숨김 상태
                Log.i("TAG", "slideOffset " + slideOffset);
            }
        });
    }

    @OnClick(R.id.btn_show)
    public void onClickShow() {
        // height 만큼 화면에 노출
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @OnClick(R.id.btn_hidden)
    public void onClickHidden() {
        // 숨김 처리
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @OnClick(R.id.btn_expanded)
    public void onClickExpanded() {
        // 확장
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
