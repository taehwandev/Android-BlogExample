package tech.thdev.app.ui;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import tech.thdev.app.R;
import tech.thdev.app.base.BaseActivity;
import tech.thdev.app.constant.Constant;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class IntentExampleActivity extends BaseActivity {

    @BindView(R.id.tv_message)
    TextView tvMessage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_intent_example;
    }

    @Override
    protected void onCreate() {
        setSupportActionBar(toolbar);
        setTitle(R.string.intent_title);
        String message = getIntent().getStringExtra(Constant.KEY_INTENT_MESSAGE);
        tvMessage.setText(message);
    }
}
