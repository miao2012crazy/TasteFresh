package com.xialan.tastefresh.view.login;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.RegeditChildContract;
import com.xialan.tastefresh.presenter.RegeditChildPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/18.
 */

public class RegeditChildActivity extends BaseActivity implements RegeditChildContract.View {

    @BindView(R.id.rl_return)
    RelativeLayout rlReturn;
    @BindView(R.id.et_user_tel)
    EditText etUserTel;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_get_verify_code)
    TextView tvGetVerifyCode;
    @BindView(R.id.et_psd)
    EditText etPsd;
    @BindView(R.id.et_confirm_psd)
    EditText etConfirmPsd;
    @BindView(R.id.btn_regedit)
    Button btnRegedit;
    @BindView(R.id.tv_argument)
    TextView tvArgument;
    private RegeditChildPresenter regeditChildPresenter;

    @Override
    protected int setlayoutID() {

        return R.layout.activity_regedit;
    }

    @Override
    protected void initData() {
//        tvGetVerifyCode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvArgument.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected BasePresenter createPresenter() {
        regeditChildPresenter = new RegeditChildPresenter(this);
        return regeditChildPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_return, R.id.tv_get_verify_code, R.id.btn_regedit, R.id.tv_argument})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_return:
                break;
            case R.id.tv_get_verify_code:
                break;
            case R.id.btn_regedit:
                break;
            case R.id.tv_argument:
                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
