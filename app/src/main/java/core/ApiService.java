package core;

import android.content.pm.PackageInfo;

import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by mavin on 2016/5/30.
 */
public interface ApiService {


    @GET("pic/pic_type")
    Call<ImageTypeResult> getImageType();

    @GET("pic/pic_search?")
    Call<PrettyGrilImage> getImage(@Query("type") String type, @Query("page") String page);




}
