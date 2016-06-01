package model;

import android.os.Handler;
import android.util.Log;

import core.ApiDataReceiveCallback;
import core.ApiDel;
import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;

/**
 * Created by zchao on 2016/6/1.
 */
public class MainModel implements IBaseModel {

    public void requestImageType(CallBacks<ImageTypeResult.ShowapiResBodyBean, String> callBacks) {
        ApiDel.newInstance().getAllImageype(callBacks);
    }


    public void getPretyGirl(final CallBacks<PrettyGrilImage.ShowapiResBodyBean.PagebeanBean,String> callBacks, String type, String page) {
        ApiDataReceiveCallback<PrettyGrilImage.ShowapiResBodyBean.PagebeanBean> callback = new ApiDataReceiveCallback<PrettyGrilImage.ShowapiResBodyBean.PagebeanBean>() {
            @Override
            public void onDataReceived(PrettyGrilImage.ShowapiResBodyBean.PagebeanBean pagebeanBean, boolean isSuccessful, Exception e) {
                if (isSuccessful) {
                    callBacks.getSuccess(pagebeanBean);
                } else {
                    callBacks.ShowFailMsg(e.toString());
                }
            }
        };
        ApiDel.newInstance().getPretyGirl(callback, type, page);

    }
}
