package view;

import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.util.Log;

import com.mavin.spicegirl.R;

import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;
import model.MainModel;
import presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private static final String TAG = "MainActivity";
    private static int page = 1;
    private static int type = 4002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mPresenter.getAllImageType();
        reFreshData();
    }

    @Override
    protected void initePresenter() {
        mPresenter = new MainPresenter(this, this, new MainModel());
    }


    @Override
    public void addAllImageType(ImageTypeResult.ShowapiResBodyBean.ListBean listBean) {
        if (listBean == null) {
            return;
        }
        for (int i = 0; i < listBean.getList().size(); i++) {

            Log.d(TAG, "addAllImageType() called with: " + "listBean = [" + listBean.getList().get(i).toString() + "]");
        }
    }

    @Override
    public void failMassage(String msg) {
        Log.d(TAG, "failMassage() called with: " + "msg = [" + msg + "]");
    }

    @Override
    public void getMoreData() {
        mPresenter.getPretyGirl(String.valueOf(type), String.valueOf(++page));
    }

    @Override
    public void reFreshData() {
        mPresenter.getPretyGirl(String.valueOf(type), String.valueOf(page = 1));
    }

    @Override
    public void addPrettyGirlDate(PrettyGrilImage.ShowapiResBodyBean.PagebeanBean pagebeanBean) {
        for (int i = 0; i < pagebeanBean.getContentlist().size(); i++) {
            Log.d(TAG, "addPrettyGirlDate() called with: " + "pagebeanBean = [" + pagebeanBean.getContentlist().get(i).getTitle() + "]");
        }
    }
}
