package view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.mavin.spicegirl.R;

import presenter.BasePresenter;

/**
 * Created by zchao on 2016/5/30.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;
    protected Toolbar mToolBar;
    private FrameLayout mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.AppTheme1);
        useImmerseStatuBar(false);
        initePresenter();
        super.setContentView(R.layout.base_activity_layout);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mContent = (FrameLayout) findViewById(R.id.content);
        setTitle("");
        setSupportActionBar(mToolBar);

    }

    protected abstract void initePresenter();

    protected void useImmerseStatuBar(boolean useImmerseStatuBar) {
        if (useImmerseStatuBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    @Override
    public void setContentView(int resID) {
      getLayoutInflater().inflate(resID, mContent);
    }

    protected  void setTitle(String title){
        mToolBar.setTitle(title);
    }
}
