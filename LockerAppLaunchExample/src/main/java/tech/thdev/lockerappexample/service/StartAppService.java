package tech.thdev.lockerappexample.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import tech.thdev.lockerappexample.Data;
import tech.thdev.lockerappexample.broadcast.StartAppBroadcastReceiver;

/**
 * Created by Tae-hwan on 8/17/16.
 */

public class StartAppService extends Service {

    private final IBinder mBinder = new LocalBinder(this);

    private BroadcastReceiver broadcastReceiver = new StartAppBroadcastReceiver();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void startApp(String url) {
        // Checked Locker...
        Toast.makeText(getApplicationContext(), "StartApp...", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "StartApp...");

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void startAppToUnlock(String url) {
        Log.d("TAG", "url : " + url);
        Data data = Data.getInstance();
        Log.i("TAG", "data : " + data);
        data.url = url;

        IntentFilter filter = new IntentFilter(Intent.ACTION_USER_PRESENT);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(broadcastReceiver);
    }
}
