package core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zchao on 2016/5/31.
 */
public class AppSetting {

    public static String SP_SETTING = "setting_sp";

    private Context context;
    private SharedPreferences sp;

    private static AppSetting instance;

    public static AppSetting newInstance(Context context){
        if (instance == null) {
            instance = new AppSetting(context);
        }
        return instance;
    }
    public AppSetting(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SP_SETTING, Context.MODE_PRIVATE);
    }
}
