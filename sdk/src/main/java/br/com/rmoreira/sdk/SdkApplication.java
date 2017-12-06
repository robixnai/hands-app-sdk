package br.com.rmoreira.sdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import java.io.File;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public final class SdkApplication {

    @SuppressLint("StaticFieldLeak")
    private static Application mApplication;

    public static void setApplication(Application application) {
        mApplication = application;
    }

    public static File getFileCacheDir() {
        return mApplication.getCacheDir();
    }

    public static Context getContext() {
        return mApplication.getApplicationContext();
    }

}
