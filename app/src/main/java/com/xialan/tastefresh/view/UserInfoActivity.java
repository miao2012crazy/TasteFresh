package com.xialan.tastefresh.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.LoginActivity;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserInfoBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.SharePreUtils;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.UserInfoContract;
import com.xialan.tastefresh.presenter.UserInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UserInfoActivity extends BaseActivity implements UserInfoContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.rl_user_head_img)
    RelativeLayout rlUserHeadImg;
    @BindView(R.id.rl_user_name)
    RelativeLayout rlUserName;
    @BindView(R.id.rl_user_sex)
    RelativeLayout rlUserSex;
    @BindView(R.id.rl_user_date)
    RelativeLayout rlUserDate;
    @BindView(R.id.rl_real_name)
    RelativeLayout rlRealName;
    @BindView(R.id.iv_user_head_img)
    ImageView ivUserHeadImg;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_user_tel)
    TextView tvUserTel;
    private UserInfoPresenter userInfoPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initData() {
        tvTitle.setText("个人信息");
        rlRight.setVisibility(View.GONE);
        String s = tvUserTel.getText().toString();
        if (!StringUtil.isEmpty(s)) {
            s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        try {
            String login_data = (String) SharePreUtils.get(this, "user_login", "");
            String login_key = (String) SharePreUtils.get(UIUtils.getContext(), "login_key", "");
            UserInfoBean userInfoBean = ParseUtils.parseDataAES(login_key, login_data, UserInfoBean.class);
            tvUserName.setText(userInfoBean.getData().getReal_name());
            tvSex.setText(userInfoBean.getData().getGender().equals("0") ? "男" : "女");
            tvDate.setText(userInfoBean.getData().getDate_birth());
            tvUserTel.setText(userInfoBean.getData().getMobile());
            ImageLoader.getInstance().displayImage(userInfoBean.getData().getUser_head_img(), ivUserHeadImg);
        } catch (Exception ex) {
            startActivity(LoginActivity.class);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        userInfoPresenter = new UserInfoPresenter(this);
        return userInfoPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.rl_user_head_img, R.id.rl_user_name, R.id.rl_user_sex, R.id.rl_user_date, R.id.rl_real_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.rl_user_head_img:

                break;
            case R.id.rl_user_name:

                break;
            case R.id.rl_user_sex:

                break;
            case R.id.rl_user_date:

                break;
            case R.id.rl_real_name:

                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
