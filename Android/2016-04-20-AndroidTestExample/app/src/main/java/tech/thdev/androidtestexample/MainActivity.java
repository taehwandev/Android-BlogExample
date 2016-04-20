package tech.thdev.androidtestexample;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import tech.thdev.androidtestexample.base.BaseActivity;
import tech.thdev.androidtestexample.constant.Constant;

public class MainActivity extends BaseActivity {

    @Bind(R.id.et_message)
    EditText etMessage;

    @Bind(R.id.btn_second_activity)
    Button btnSecondActivity;

    @Bind(R.id.btn_set_message)
    Button btnTextChange;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate() {

    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @OnClick(R.id.btn_set_message)
    public void onBtnClick(View view) {
        etMessage.setText(R.string.instrumentation_api_title);
    }

    @OnClick(R.id.btn_second_activity)
    public void onBtnSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constant.INTENT_MESSAGE, etMessage.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.btn_web_view)
    public void onBtnWebView(View view) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(Constant.INTENT_URL, etMessage.getText().toString());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
