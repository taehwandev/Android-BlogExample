package tech.thdev.lockerappexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import tech.thdev.lockerappexample.service.LocalBinder;
import tech.thdev.lockerappexample.service.StartAppService;

public class MainActivity extends AppCompatActivity {

    private StartAppService mService;
    private boolean mBound = false;

    private EditText etUrl;
    private Button btnStartWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        // Bind to StartAppService
        Intent intent = new Intent(this, StartAppService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        etUrl = (EditText) findViewById(R.id.et_url);
        btnStartWeb = (Button) findViewById(R.id.btn_start_chrome);
        btnStartWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound) {
                    String url = etUrl.getText().toString();
                    Log.d("TAG", "URL : " + url);
                    mService.startAppToUnlock(url);
//                    mService.startApp(url);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unbind from the service
//        if (mBound) {
//            unbindService(mConnection);
//            mBound = false;
//        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalBinder binder = (LocalBinder) iBinder;
            mService = binder.getService();

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };
}
