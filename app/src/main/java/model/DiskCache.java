package model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javaBean.CacheObj;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by mavin on 2016/5/30.
 */
public class DiskCache {

    private File baseFile;
    private String appVar;
    private final Gson gson;
    private Executor mDiskExecutor;
    private static DiskCache insance = null;
    private Context mContext;

    public static DiskCache getInstence(Context context) {
        if (insance == null) {
            File cacheFile = null;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheFile = new File(Environment.getExternalStorageDirectory(), "Android/data/" + context.getPackageName() + "/cache");
            } else {
                context.getCacheDir();
            }

            String appVar = "";
            PackageInfo info = null;

            try {
                info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_GIDS);
                if (info != null) {
                    appVar = info.versionName;
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            insance = new DiskCache(cacheFile, appVar);
        }
        return insance;
    }

    public DiskCache(File cacheFile, String appVar) {
        mDiskExecutor = Executors.newSingleThreadExecutor();
        this.baseFile = cacheFile;
        this.appVar = appVar;
        gson = new Gson();


    }

    public File getCacheFile(String key) {
        return getCacheFile("json", key);

    }

    public File getCacheFile(String pre, String key) {
        File file = new File(baseFile, pre + "/" + strToMD5(key));
        if (file != null || !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }


    /**
     * 获取MD5加密
     * @param str
     * @return
     */
    public static String strToMD5(String str) {
        byte[] md5s;
        try {
            md5s =  MessageDigest.getInstance("MD5").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder SB = new StringBuilder();
        for (byte b: md5s) {
            SB.append(b);
        }
        return SB.toString();
    }

    public void storyCacheObj(final String key, final Object object){
        mDiskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                CacheObj<Object> cacheObj = new CacheObj<>(object, System.currentTimeMillis(), appVar);
                String jsonStr = gson.toJson(cacheObj);
                File mCacheFile = getCacheFile(key);
                if (mCacheFile != null) {
                    try {

                        Okio.buffer(Okio.sink(mCacheFile)).writeUtf8(jsonStr).close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public synchronized <T> CacheObj<T> getCacheObj(String key, Type type) {
        File file = getCacheFile(key);
        if (file == null || !file.exists()) {
            return null;
        }

        BufferedSource cacheSource = null;
        try {
            cacheSource = Okio.buffer(Okio.source(file));
            String cStr = cacheSource.readUtf8();

            return gson.fromJson(cStr, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cacheSource != null)
                try {
                    cacheSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
