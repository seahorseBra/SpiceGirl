package core;

import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mavin on 2016/5/30.
 */
public interface ApiService {


    @GET("pic/pic_type")
    Call<ImageTypeResult> getImageType();

    @GET("/pic/pic_search/{type}/{page}")
    Call<PrettyGrilImage> getImage(@Path("type") String type, @Path("page") String page);

    @GET("/pic/pic_search/{type}/{page}")
    Call<String> getImage1(@Path("type") String type, @Path("page") String page);

}
