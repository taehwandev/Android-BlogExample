package tech.thdev.butter_knife_example.view;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnLongClick;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.base.BaseActivity;
import tech.thdev.butter_knife_example.bean.RadioItem;
import tech.thdev.butter_knife_example.constant.Constant;

public class MainActivity extends BaseActivity {

    /**
     * get String res
     */
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

    @BindViews({R.id.text_one})
    List<TextView> nameViews;

    @BindView(R.id.et_message)
    EditText etMessage;

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
        Intent intent = new Intent(this, TwoActivity.class);
        intent.putExtra(Constant.KEY_INTENT_MESSAGE, etMessage.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.btn_recycler_ex)
    public void onClickRecyclerExample(View view) {
        startActivity(new Intent(this, RadioRecyclerViewActivity.class));
    }

    @OnClick(R.id.btn_photo_view)
    public void onClickPhotoView(View view) {
        startActivity(new Intent(this, PhotoRecyclerViewActivity.class));
    }

    @OnClick(R.id.btn_photo_search_view)
    public void onClickPhotoSearchView(View view) {
        startActivity(new Intent(this, PhotoSearchRecyclerViewActivity.class));
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
