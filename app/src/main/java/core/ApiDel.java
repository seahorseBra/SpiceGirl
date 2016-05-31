package core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javaBean.CacheObj;
import javaBean.ImageTypeResult;
import model.ApiService;
import model.CallBacks;
import model.IModel.ApiManager;
import okhttp3.Interceptor;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mavin on 2016/5/30.
 */
public class ApiDel {
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

    private void inteService() {
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                okhttp3.Request re = request.newBuilder().addHeader("apikey", "99d5fcb6ca3f46b33431daa2b02dac04").build();

                return chain.proceed(re);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SPICE_GIRL_BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    public  void getAllImageype(final CallBacks<ImageTypeResult.ShowapiResBodyBean.ListBean, String> callBacks){
        final String IMAGE_TYPE_KEY = "spice_girl_type";
        mPreloadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Type type = new TypeToken<CacheObj<ImageTypeResult.ShowapiResBodyBean.ListBean>>() {
                }.getType();
                CacheObj<ImageTypeResult.ShowapiResBodyBean.ListBean> obj = mDiskCache.getCacheObj(IMAGE_TYPE_KEY,type);
                if (obj != null && !obj.getT().getList().isEmpty() && !obj.isOutOfDate(CacheTimeManager.IMAGE_TYPE_CACHETIME)) {
                    callBacks.getSuccess(obj.getT());
                    return;
                }
                try {
                    retrofit2.Response<ImageTypeResult> execute = apiService.getImageType().execute();
                    if (execute.body() != null && !execute.body().getShowapi_res_body().getList().isEmpty()) {
                        mDiskCache.storyCacheObj(IMAGE_TYPE_KEY, execute.body().getShowapi_res_body().getList());
                        callBacks.getSuccess((ImageTypeResult.ShowapiResBodyBean.ListBean) execute.body().getShowapi_res_body().getList());
                    }
                } catch (IOException e) {
                    callBacks.ShowFailMsg("请求错误");
                }

            }
        });
    }
}
