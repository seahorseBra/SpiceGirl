package model;

import model.model_interface.IBaseModel;

/**
 * Created by mavin on 2016/5/30.
 */
public interface ImageModelIterface extends IBaseModel {
    void requestImage(String type, String page);
}
