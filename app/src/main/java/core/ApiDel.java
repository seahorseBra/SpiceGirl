package core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javaBean.CacheObj;
import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;
import model.CallBacks;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mavin on 2016/5/30.
 */
public class ApiDel {
    private static final String TAG = "MainActivity";
    private Context context;
    private DiskCache mDiskCache;
    private Executor mPreloadExecutor;
    private Handler mMianHandler;

    private static ApiDel instance = null;
    private ApiService apiService;

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
        this.context = context;
        mDiskCache = DiskCache.getInstence(context);
        mPreloadExecutor = Executors.newFixedThreadPool(5);
        mMianHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 发送消息到主线程
     * @param receive
     * @param data
     * @param isSuccessful
     * @param e
     * @param <T>
     */
    private <T>void postResultToMainThread(final ApiDataReceiveCallback receive, final T data, final boolean isSuccessful, final Exception e) {
        mMianHandler.post(new Runnable() {
            @Override
            public void run() {
                receive.onDataReceived(data, isSuccessful, e);
            }
        });

    }
    /**
     * 初始化亲求service
     */
    private void inteService() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request re = chain.request()
                        .newBuilder()
                        .addHeader("apikey", "99d5fcb6ca3f46b33431daa2b02dac04")
                        .build();

                return chain.proceed(re);
            }
        };
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SPICE_GIRL_BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    /**
     * 获取所有图片类型
     * @param callBacks
     */
    public  void getAllImageype(final CallBacks<ImageTypeResult.ShowapiResBodyBean, String> callBacks){
        final String IMAGE_TYPE_KEY = "spice_girl_type";
        mPreloadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Type type = new TypeToken<CacheObj<ImageTypeResult.ShowapiResBodyBean>>() {
                }.getType();
                CacheObj<ImageTypeResult.ShowapiResBodyBean> obj = mDiskCache.getCacheObj(IMAGE_TYPE_KEY,type);
                if (obj != null) {
                    if (obj != null && !obj.getT().getList().isEmpty() && !obj.isOutOfDate(CacheTimeManager.IMAGE_TYPE_CACHETIME)) {
                        callBacks.getSuccess(obj.getT());
                        return;
                    }
                }
                apiService.getImageType().enqueue(new Callback<ImageTypeResult>() {
                    @Override
                    public void onResponse(Call<ImageTypeResult> call, retrofit2.Response<ImageTypeResult> response) {
                        if (response.body() != null && !response.body().getShowapi_res_body().getList().isEmpty()) {
                            mDiskCache.storyCacheObj(IMAGE_TYPE_KEY, response.body().getShowapi_res_body());
                            callBacks.getSuccess( response.body().getShowapi_res_body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageTypeResult> call, Throwable t) {
                            callBacks.ShowFailMsg(t.getMessage());
                    }
                });

            }
        });
    }

    public void getPretyGirl(final ApiDataReceiveCallback<PrettyGrilImage.ShowapiResBodyBean.PagebeanBean> callback, final String type, final String page){
        final String PRETY_GIRL_CACHEKEY = "prety_girl";
        mPreloadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Type type1 = new TypeToken<PrettyGrilImage.ShowapiResBodyBean>() {}.getType();
                CacheObj<PrettyGrilImage.ShowapiResBodyBean> Obj = mDiskCache.getCacheObj(PRETY_GIRL_CACHEKEY + type + page, type1);
                if (Obj != null) {
                    if (Obj.getT() != null && !Obj.isOutOfDate(CacheTimeManager.PRETY_GIRL_CACHETIME)) {
                        postResultToMainThread(callback, Obj.getT().getPagebean(), true ,null);
                        return;
                    }
                }
                apiService.getImage(type, page).enqueue(new Callback<PrettyGrilImage>() {
                    @Override
                    public void onResponse(Call<PrettyGrilImage> call, retrofit2.Response<PrettyGrilImage> response) {
                        if (response.body() != null && !response.body().getShowapi_res_body().getPagebean().getContentlist().isEmpty()) {
                            mDiskCache.storyCacheObj(PRETY_GIRL_CACHEKEY + type + page, response.body().getShowapi_res_body());
                            postResultToMainThread(callback, response.body().getShowapi_res_body().getPagebean(), true, null);
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<PrettyGrilImage> call, Throwable t) {
                            postResultToMainThread(callback, null, false, new Exception(t));
                        return;
                    }
                });
            }
        });
    }

}
