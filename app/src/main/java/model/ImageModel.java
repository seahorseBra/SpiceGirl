package model;


import model.CallBacks;
import model.IBaseModel;

/**
 * Created by zchao on 2016/5/30.
 */
public interface ImageModel extends IBaseModel {

    void downImage(String url, CallBacks callback);

}
