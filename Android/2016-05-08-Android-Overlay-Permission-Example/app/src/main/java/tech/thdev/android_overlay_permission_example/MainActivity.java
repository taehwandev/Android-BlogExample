package tech.thdev.android_overlay_permission_example;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import tech.thdev.android_overlay_permission_example.presenter.WindowOverlayPresenter;
import tech.thdev.android_overlay_permission_example.presenter.view.WindowOverlayView;

public class MainActivity extends AppCompatActivity implements WindowOverlayView {

    public static final int REQ_CODE_OVERLAY_PERMISSION = 9999;

    private View windowView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowViewLayoutParams;

    private WindowOverlayPresenter windowOverlayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        windowOverlayPresenter = new WindowOverlayPresenter(this);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        Button btnStartOverlay = (Button) findViewById(R.id.btn_start_overlay);
        btnStartOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowOverlayPresenter.startOverlayWindowService(MainActivity.this);
            }
        });

        Button btnPerVersion = (Button) findViewById(R.id.btn_prev_version);
        btnPerVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initWindowLayout((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            }
        });
    }

    @Override
    public void onStartOverlay() {
        initWindowLayout((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onObtainingPermissionOverlayWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_OVERLAY_PERMISSION:
                windowOverlayPresenter.onOverlayResult(this);
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Window View 를 초기화 한다. X, Y 좌표는 0, 0으로 지정한다.
     */
    private void initWindowLayout(LayoutInflater layoutInflater) {
        windowView = layoutInflater.inflate(R.layout.window_view, null);
        windowViewLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                30, 30, // X, Y 좌표
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        windowViewLayoutParams.gravity = Gravity.TOP | Gravity.START;
        windowManager.addView(windowView, windowViewLayoutParams);

        initWindowCreate();
    }

    private void initWindowCreate() {
        Button button = (Button) windowView.findViewById(R.id.btn_hide);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (windowView != null) {
                    if (windowManager != null) {
                        windowManager.removeView(windowView);
                    }

                    windowView = null;
                }
            }
        });
    }
}
