package tech.thdev.app.ui;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.appcompat.view.ContextThemeWrapper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnLongClick;
import tech.thdev.app.R;
import tech.thdev.app.base.BaseActivity;
import tech.thdev.app.constant.Constant;
import tech.thdev.app.ui.photo.PhotoRecyclerViewActivity;
import tech.thdev.app.ui.photosearch.PhotoSearchRecyclerViewActivity;
import tech.thdev.app.ui.radio.RadioRecyclerViewActivity;

public class MainActivity extends BaseActivity {

    /**
     * get String res
     */
    @BindString(R.string.sample_message)
    String sampleMessage;

    @BindString(R.string.sample_message_long_click)
    String sampleMessageLongClick;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindViews({R.id.text_sample_one_message})
    List<TextView> textSampleMessages;

    @BindView(R.id.et_intent_sample)
    EditText etIntentSample;

    @BindView(R.id.view)
    View view;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        view.startAnimation(animation);
    }

    @OnClick(R.id.btn_sample_one)
    public void onClickBtnOne(View view) {
        showSnackbar(sampleMessage);
        textSampleMessages.get(0).setText(sampleMessage);
    }

    @OnLongClick(R.id.btn_sample_one)
    public boolean onLongClickBtnOne(View view) {
        showSnackbar(sampleMessageLongClick);
        textSampleMessages.get(0).setText(sampleMessageLongClick);
        return true;
    }

    @OnClick(R.id.btn_intent_example)
    public void onClickBtnTwo(View view) {
        Intent intent = new Intent(this, IntentExampleActivity.class);
        intent.putExtra(Constant.KEY_INTENT_MESSAGE, etIntentSample.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.btn_recycler_radio_btn)
    public void onClickRecyclerExample(View view) {
        startActivity(new Intent(this, RadioRecyclerViewActivity.class));
    }

    @OnClick(R.id.btn_photo_recycler_view)
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

    @OnClick(R.id.btn_holo_date_picker)
    public void onClickHoloDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog), dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.btn_material_date_picker)
    public void onClickMaterialDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(this, android.R.style.Theme_Material_Dialog), dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.btn_date_picker)
    public void onClickDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context = this;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Snackbar.make(floatingActionButton, year + "-" + monthOfYear + "-" + dayOfMonth, Snackbar.LENGTH_SHORT).show();
        }
    };
}
