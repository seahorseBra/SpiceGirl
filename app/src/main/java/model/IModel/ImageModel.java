package model.IModel;


import model.CallBacks;

/**
 * Created by zchao on 2016/5/30.
 */
public interface ImageModel extends IBaseModel{

    void downImage(String url, CallBacks callback);

}
