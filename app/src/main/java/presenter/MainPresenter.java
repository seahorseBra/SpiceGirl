package presenter;

import android.app.Activity;
import android.util.Log;


import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;
import model.CallBacks;
import model.MainModel;
import model.IBaseModel;
import view.IBaseView;
import view.IMainView;

/**
 * Created by zchao on 2016/5/30.
 */
public class MainPresenter extends BasePresenter<IMainView, MainModel> {

    private static final String TAG = "MainActivity";

    public MainPresenter(Activity context, IMainView view) {
        super(context, view, new MainModel());
    }


    public void getAllImageType() {
        mModel.requestImageType(new CallBacks<ImageTypeResult.ShowapiResBodyBean, String>() {
            @Override
            public void getSuccess(ImageTypeResult.ShowapiResBodyBean body) {
                mView.addAllImageType(body.getList().get(3));
            }

            @Override
            public void ShowFailMsg(String s) {
                mView.failMassage(s);
            }
        });
    }

    public void getPretyGirl(String type, String page) {
        mModel.getPretyGirl(new CallBacks<PrettyGrilImage.ShowapiResBodyBean.PagebeanBean, String>() {
            @Override
            public void getSuccess(PrettyGrilImage.ShowapiResBodyBean.PagebeanBean pagebeanBean) {
                mView.addPrettyGirlDate(pagebeanBean);
            }

            @Override
            public void ShowFailMsg(String s) {
                Log.d(TAG, "ShowFailMsg() called with: " + "s = [" + s + "]");
            }
        }, type, page);
    }
}
