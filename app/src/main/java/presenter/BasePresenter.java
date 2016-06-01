package presenter;

import android.app.Activity;


import model.IBaseModel;
import view.IBaseView;

/**
 * Created by zchao on 2016/5/30.
 */
public class BasePresenter<G extends IBaseView, M extends IBaseModel> implements IBasePresenter{
    protected G mView;

    protected Activity mContext;

    protected M mModel;

    public BasePresenter(Activity context, G view, M model) {
        mContext = context;
        mView = view;
        mModel = model;
    }
}
