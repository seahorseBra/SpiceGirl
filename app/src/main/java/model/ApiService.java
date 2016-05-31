package model;

import javaBean.ImageTypeResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mavin on 2016/5/30.
 */
public interface ApiService {


    @GET("/pic/pic_type")
    Call<ImageTypeResult> getImageType();

//    @GET("/pic/pic_search/{type}/{page}")
//    Call<> getImage(@Path("type") String type, @Path("page") String page, Callback<> callback);

}
