package core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mavin.spicegirl.R;

import core.ApiDel;

/**
 * Created by zchao on 2016/5/31.
 */
public class CApp extends Application {
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.AppTheme1);
        mContext = getApplicationContext();
        initeSharedPrefrence();
        ApiDel.newInstance().initeApiDel(mContext);

    }

    private void initeSharedPrefrence() {
        AppSetting.newInstance(mContext);
    }
}
