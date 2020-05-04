package tech.thdev.app.ui.radio;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import kotlin.jvm.functions.Function2;
import tech.thdev.app.R;
import tech.thdev.app.base.BaseActivity;
import tech.thdev.app.data.RadioItem;
import tech.thdev.app.listener.OnRecyclerItemClickListener;
import tech.thdev.app.ui.radio.adapter.OnItemClickListener;
import tech.thdev.app.ui.radio.adapter.RadioViewHolder;
import tech.thdev.simpleadapter.SimpleRecyclerViewAdapter;
import tech.thdev.simpleadapter.holder.BaseViewHolder;

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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private float tmpBottomSlideOffset;
    private BottomSheetBehavior bottomSheetBehavior;

    private RadioPresenter radioPresenter;

    private SimpleRecyclerViewAdapter radioRecyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void onCreate() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.radio_button_recycler_example);

        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200.f, getResources().getDisplayMetrics()));
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback);

        radioRecyclerAdapter = getSimpleAdapter();
        recyclerView.setAdapter(radioRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        radioPresenter = new RadioPresenter(this, radioRecyclerAdapter);

        setDummyData();
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
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
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback);
    }

    private SimpleRecyclerViewAdapter getSimpleAdapter() {
        return new SimpleRecyclerViewAdapter(new Function2<ViewGroup, Integer, BaseViewHolder<?>>() {
            @Override
            public BaseViewHolder<?> invoke(ViewGroup viewGroup, Integer integer) {
                return new RadioViewHolder(viewGroup, new OnItemClickListener() {
                    @Override
                    public void onClickItem(int adapterPosition) {
                        radioPresenter.onRecyclerItemClick(adapterPosition);
                    }
                });
            }
        });
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

    @Override
    public void notifyDataSetChanged() {
        radioRecyclerAdapter.notifyDataSetChanged();
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
