package tech.thdev.butter_knife_example.view;

import android.widget.TextView;

import butterknife.BindView;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.base.BaseActivity;
import tech.thdev.butter_knife_example.constant.Constant;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class TwoActivity extends BaseActivity {

    @BindView(R.id.tv_message)
    TextView tvMessage;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_two;
    }

    @Override
    protected void onCreate() {
        String message = getIntent().getStringExtra(Constant.KEY_INTENT_MESSAGE);
        tvMessage.setText(message);
    }
}
