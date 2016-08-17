package tech.thdev.lockerappexample.service;

import android.os.Binder;

/**
 * Created by Tae-hwan on 8/17/16.
 */

public class LocalBinder extends Binder {

    private StartAppService service;

    public LocalBinder(StartAppService service) {
        this.service = service;
    }

    public StartAppService getService() {
        return service;
    }
}
