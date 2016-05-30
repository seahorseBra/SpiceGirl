package ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mavin.spicegirl.R;

import model.ImageModel;
import presenter.MainPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initePresenter() {
        mPresenter = new MainPresenter(this, this, new ImageModel());
    }

    @Override
    public int getLayout() {
        return 0;
    }
}
