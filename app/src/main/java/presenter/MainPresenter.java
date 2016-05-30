package presenter;

import android.app.Activity;

import model.IModel.IBaseModel;
import presenter.IPresenter.IBaseView;

/**
 * Created by zchao on 2016/5/30.
 */
public class MainPresenter extends BasePresenter {

    public MainPresenter(Activity context, IBaseView view, IBaseModel model) {
        super(context, view, model);
    }
}
