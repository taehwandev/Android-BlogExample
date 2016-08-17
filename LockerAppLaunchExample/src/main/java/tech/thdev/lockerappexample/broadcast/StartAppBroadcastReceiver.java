package tech.thdev.lockerappexample.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import tech.thdev.lockerappexample.Data;

/**
 * Created by Tae-hwan on 8/17/16.
 */

public class StartAppBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // User present
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Uri webpage = Uri.parse(Data.getInstance().url);

            Intent startApp = new Intent(Intent.ACTION_VIEW, webpage);
            context.startActivity(startApp);
        }
    }
}
