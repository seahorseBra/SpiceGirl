package model;

import javaBean.ImageTypeResult;

/**
 * Created by mavin on 2016/5/30.
 */
public interface ImageModelIterface {
    void requestImageType(CallBacks<ImageTypeResult.ShowapiResBodyBean, String> callBacks);
}
