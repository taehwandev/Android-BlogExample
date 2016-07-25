package tech.thdev.butter_knife_example.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.adapter.RadioRecyclerAdapter;
import tech.thdev.butter_knife_example.base.BaseActivity;
import tech.thdev.butter_knife_example.bean.RadioItem;
import tech.thdev.butter_knife_example.listener.OnRecyclerItemClickListener;
import tech.thdev.butter_knife_example.presenter.RadioPresenter;
import tech.thdev.butter_knife_example.presenter.view.RadioPresenterView;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class RadioRecyclerViewActivity extends BaseActivity implements OnRecyclerItemClickListener, RadioPresenterView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rl_bottom_sheet)
    RelativeLayout rlBottomSheet;

    @BindView(R.id.tv_message)
    TextView tvBottomSheetMessage;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private float tmpBottomSlideOffset;
    private BottomSheetBehavior bottomSheetBehavior;

    private RadioPresenter radioPresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void onCreate() {
        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200.f, getResources().getDisplayMetrics()));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset > 0.8) {
                    if (tmpBottomSlideOffset > slideOffset) {
                        floatingActionButton.show();

                    } else {
                        floatingActionButton.hide();
                    }

                } else {
                    floatingActionButton.show();
                }

                tmpBottomSlideOffset = slideOffset;
            }
        });

        RadioRecyclerAdapter radioRecyclerAdapter = new RadioRecyclerAdapter(this);
        radioRecyclerAdapter.setOnRecyclerItemClickListener(this);

        recyclerView.setAdapter(radioRecyclerAdapter);

        // aa
        radioPresenter = new RadioPresenter(this, radioRecyclerAdapter);

        setDummyData();
    }

    @Override
    public void onItemClick(int position) {
        radioPresenter.onRecyclerItemClick(position);
    }

    @Override
    public void showBottomSheet(RadioItem item) {
        tvBottomSheetMessage.setText(item.name);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    /**
     * Dummy data add
     */
    private void setDummyData() {
        for (int i = 0; i < 5; i++) {
            radioPresenter.addItem("Item " + i);
        }
    }
}
