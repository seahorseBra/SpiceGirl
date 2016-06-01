package model;

import core.ApiDataReceiveCallback;
import javaBean.PrettyGrilImage;

/**
 * Created by zchao on 2016/6/1.
 */
public interface IPrettyGirlModel extends IBaseModel{
    void getPretyGirl(CallBacks<PrettyGrilImage.ShowapiResBodyBean.PagebeanBean, String> callBacks, String type, String page);
}
