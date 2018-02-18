package com.xialan.tastefresh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.LoginContract;
import com.xialan.tastefresh.presenter.LoginPresenter;
import com.xialan.tastefresh.view.login.LoginChildActivity;
import com.xialan.tastefresh.view.login.RegeditChildActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/16.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regedit)
    Button btnRegedit;
    @BindView(R.id.btn_gyg)
    Button btnGyg;
    private LoginPresenter loginPresenter;

    @Override
    public void showCustomProgressBar(String msg) {

    }

    @Override
    public void hideCustomProgressBar() {

    }

    @Override
    public void getDataFailed(String err_msg) {

    }


    @Override
    protected int setlayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        loginPresenter = new LoginPresenter(this);
        return loginPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_regedit, R.id.btn_gyg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(LoginChildActivity.class);
                break;
            case R.id.btn_regedit:
                startActivity(RegeditChildActivity.class);
                break;
            case R.id.btn_gyg:
                break;
        }
    }
}
