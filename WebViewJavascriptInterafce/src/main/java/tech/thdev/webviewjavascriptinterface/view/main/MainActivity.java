package tech.thdev.webviewjavascriptinterface.view.main;

import android.os.Bundle;

import tech.thdev.webviewjavascriptinterface.R;
import tech.thdev.webviewjavascriptinterface.base.view.BaseActivity;
import tech.thdev.webviewjavascriptinterface.util.ActivityUtilKt;
import tech.thdev.webviewjavascriptinterface.view.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        MainFragment fragment = MainFragment.getInstance();

        MainPresenter presenter = new MainPresenter();
        presenter.attachView(fragment);

        // Replace content
        ActivityUtilKt.replaceContentFragment(this, R.id.frame_layout, fragment);
    }
}