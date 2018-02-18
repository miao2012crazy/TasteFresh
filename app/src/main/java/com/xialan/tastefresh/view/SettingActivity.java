package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.applaction.MyApplaction;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.commonutil.SharePreUtils;
import com.xialan.tastefresh.contract.SettingContract;
import com.xialan.tastefresh.presenter.SettingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class SettingActivity extends BaseActivity implements SettingContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.btn_cancel_login)
    Button btnCancelLogin;
    private SettingPresenter settingPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        rlRight.setVisibility(View.GONE);
        tvTitle.setText("设置");

    }

    @Override
    protected BasePresenter createPresenter() {
        settingPresenter = new SettingPresenter(this);
        return settingPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void getDataFailed(String err_msg) {

    }

    @OnClick({R.id.rl_left, R.id.btn_cancel_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.btn_cancel_login:
                SharePreUtils.remove(this, "user_login");
                SharePreUtils.remove(this, "login_key");
                SharePreUtils.remove(this, "time");
                MyApplaction.setUserBean(null);
                break;
        }
    }
}
