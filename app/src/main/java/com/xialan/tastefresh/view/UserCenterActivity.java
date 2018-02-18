package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.UserCenterContract;
import com.xialan.tastefresh.presenter.UserCenterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UserCenterActivity extends BaseActivity implements UserCenterContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.rl_user_address)
    RelativeLayout rlUserAddress;
    @BindView(R.id.rl_real_name)
    RelativeLayout rlRealName;
    @BindView(R.id.rl_update_psd)
    RelativeLayout rlUpdatePsd;
    @BindView(R.id.rl_user_wallet)
    RelativeLayout rlUserWallet;
    @BindView(R.id.rl_user_subscribe)
    RelativeLayout rlUserSubscribe;
    @BindView(R.id.rl_user_order)
    RelativeLayout rlUserOrder;
    private UserCenterPresenter userCenterPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_user_center;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        userCenterPresenter = new UserCenterPresenter(this);
        return userCenterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.rl_message, R.id.rl_user_info, R.id.rl_user_address, R.id.rl_real_name, R.id.rl_update_psd,R.id.rl_user_wallet, R.id.rl_user_subscribe, R.id.rl_user_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.rl_message:

                break;
            case R.id.rl_user_info:
                startActivity(UserInfoActivity.class);
                break;
            case R.id.rl_user_address:
                startActivity(AddressActivity.class);
                break;
            case R.id.rl_real_name:

                break;
            case R.id.rl_update_psd:
                startActivity(UpdatePsdActivity.class);
                break;
            case R.id.rl_user_wallet:

                break;
            case R.id.rl_user_subscribe:
                break;
            case R.id.rl_user_order:
                startActivity(UserOrderActivity.class);
                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

}
