package tech.thdev.multiwindow;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Tae-hwan on 4/6/16.
 * <p/>
 * Transparent activity sample
 */
public class TransparentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_window_transparent);
    }
}
