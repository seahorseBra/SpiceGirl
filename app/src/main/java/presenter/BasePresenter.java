package presenter;

import android.app.Activity;

import model.IModel.IBaseModel;
import presenter.IPresenter.IBaseView;

/**
 * Created by zchao on 2016/5/30.
 */
public class BasePresenter<G extends IBaseView , M extends IBaseModel> {
    protected G mView;

    protected Activity mContext;

    protected IBaseModel mModel;

    public BasePresenter(Activity context, G view, M model) {
        mContext = context;
        mView = view;
        mModel = model;
    }
}
