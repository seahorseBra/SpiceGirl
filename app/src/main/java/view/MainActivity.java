package view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import com.mavin.spicegirl.R;

import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;
import model.MainModel;
import presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private static final String TAG = "MainActivity";
    private static int type = 4002;
    private static int page = 2;
    private ScrollView mScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useImmerseStatuBar(true);
        setTitle("wge");
        mToolBar.setLogo(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_main);
        mScroll = (ScrollView) findViewById(R.id.scroll_view);


    }

    @Override
    protected void initePresenter() {
        mPresenter = new MainPresenter(this, this);
    }


    @Override
    public void addAllImageType(ImageTypeResult.ShowapiResBodyBean.ListBean listBean) {

    }

    @Override
    public void failMassage(String msg) {

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
        for (int i = 0; i < pagebeanBean.contentlist.size(); i++) {
            Log.d(TAG, "addPrettyGirlDate() called with: " + "pagebeanBean = [" + pagebeanBean.contentlist.get(i).list.get(0).big + "]");
        }
    }
}
