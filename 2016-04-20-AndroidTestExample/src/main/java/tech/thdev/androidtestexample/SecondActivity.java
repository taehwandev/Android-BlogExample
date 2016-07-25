package tech.thdev.androidtestexample;

import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import tech.thdev.androidtestexample.base.BaseActivity;
import tech.thdev.androidtestexample.constant.Constant;

/**
 * Created by Tae-hwan on 4/20/16.
 */
public class SecondActivity extends BaseActivity {

    @BindView(R.id.tv_message)
    TextView textView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_second;
    }

    @Override
    protected void onCreate() {
        Log.d("TAG", "getIntent().getStringExtra(Constrant.INTENT_MESSAGE) " + getIntent().getStringExtra(Constant.INTENT_MESSAGE));
        textView.setText(getIntent().getStringExtra(Constant.INTENT_MESSAGE));
    }
}
