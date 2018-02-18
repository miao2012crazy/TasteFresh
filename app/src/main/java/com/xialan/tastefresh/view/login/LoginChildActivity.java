package com.xialan.tastefresh.view.login;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.MainActivity;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.applaction.MyApplaction;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserInfoBean;
import com.xialan.tastefresh.commonutil.AESHelper;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.RSAUtil;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.LoginChildContract;
import com.xialan.tastefresh.presenter.LoginChildPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/18.
 */

public class LoginChildActivity extends BaseActivity implements LoginChildContract.View {

    @BindView(R.id.rl_return)
    RelativeLayout rlReturn;
    @BindView(R.id.et_user_id)
    EditText etUserId;
    @BindView(R.id.et_psd)
    EditText etPsd;
    @BindView(R.id.tv_forget_psd)
    TextView tvForgetPsd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginChildPresenter loginChildPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_login_child;
    }

    @Override
    protected void initData() {
        tvForgetPsd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected BasePresenter createPresenter() {
        loginChildPresenter = new LoginChildPresenter(this);
        return loginChildPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_return, R.id.tv_forget_psd, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_return:
                finish();
                break;
            case R.id.tv_forget_psd:
                break;
            case R.id.btn_login:
                String userid = etUserId.getText().toString();
                boolean empty = StringUtil.isEmpty(userid);
                if (empty){
                    UIUtils.showToast("用户ID 不能为空");
                    return;
                }
                String userpsd = etPsd.getText().toString();
                boolean empty1 = StringUtil.isEmpty(userid);
                if (empty1){
                    UIUtils.showToast("用户密码不能为空");
                    return;
                }
                String s = "\"user_id\":\"" + userid+"\"";
                String s1 = "\"user_psd\":\"" + userpsd+"\"";
                String[] str = {s, s1};
                loginChildPresenter.login(AESOperator.getRandomString(),str);
                break;
        }
    }

    @Override
    public void onLoginSuccessed(UserInfoBean userInfoBean) {
        MyApplaction.setUserBean(userInfoBean);
        startActivity(MainActivity.class);
    }

    @Override
    public void onLoginFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
