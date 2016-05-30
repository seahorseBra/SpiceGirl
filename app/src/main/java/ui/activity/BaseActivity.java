package ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import presenter.BasePresenter;
import presenter.IPresenter.IBaseView;

/**
 * Created by zchao on 2016/5/30.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initePresenter();
    }

    protected abstract void initePresenter();


    public abstract int getLayout();
}
