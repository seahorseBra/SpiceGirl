package core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import core.ApiDel;

/**
 * Created by zchao on 2016/5/31.
 */
public class CApp extends Application {
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initeSharedPrefrence();
        ApiDel.newInstance().initeApiDel(mContext);

    }

    private void initeSharedPrefrence() {
        AppSetting.newInstance(mContext);
    }
}
