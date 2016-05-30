package model;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;

import java.util.concurrent.Executor;

/**
 * Created by mavin on 2016/5/30.
 */
public class ApiDel {
    private Context context;
    private DiskCache mDiskCache;
    private Executor mPreloadExecutor;
    private Handler mMianHandler;

    private static ApiDel instance = null;
    public static ApiDel newInstance() {
        if (instance ==null) {
            instance = new ApiDel();
        }
        return instance;
    }

    public ApiDel() {
        inteService();
    }

    public void initeApiDel(Context context) {



    }

    private void inteService() {
        Gson gson = new Gson();

        RestAdapter
    }
}
