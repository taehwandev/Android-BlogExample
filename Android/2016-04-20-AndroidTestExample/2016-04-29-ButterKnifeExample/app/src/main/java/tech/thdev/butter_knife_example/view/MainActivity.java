package tech.thdev.butter_knife_example.view;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnLongClick;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindString(R.string.btn_one_message)
    String btnOneMessage;

    @BindString(R.string.btn_one_message_long_click)
    String btnOneMessageLongClick;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.btn_one)
    Button buttonOne;

    @BindView(R.id.btn_two)
    Button buttonTwo;

    @BindViews({R.id.text_one, R.id.text_two})
    List<TextView> nameViews;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate() {

    }

    @OnClick(R.id.btn_one)
    public void onClickBtnOne(View view) {
        showSnackbar(btnOneMessage);
        nameViews.get(0).setText(btnOneMessage);
    }

    @OnLongClick(R.id.btn_one)
    public boolean onLongClickBtnOne(View view) {
        showSnackbar(btnOneMessageLongClick);
        nameViews.get(0).setText(btnOneMessageLongClick);
        return true;
    }

    @OnClick(R.id.btn_two)
    public void onClickBtnTwo(View view) {
        startActivity(new Intent(this, TwoActivity.class));
    }

    @OnClick(R.id.btn_recycler_ex)
    public void onClickRecyclerExample(View view) {
        startActivity(new Intent(this, RadioRecyclerViewActivity.class));
    }

    private void showSnackbar(@StringRes int messageRes) {
        showSnackbar(getString(messageRes));
    }

    private void showSnackbar(String message) {
        Snackbar.make(floatingActionButton, message, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
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
