package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.StringUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.UpdatePsdContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.UpdatePsdPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23. 修改密码
 */

public class UpdatePsdActivity extends BaseActivity implements UpdatePsdContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.et_old_psd)
    EditText etOldPsd;
    @BindView(R.id.et_new_psd)
    EditText etNewPsd;
    @BindView(R.id.et_confirm_psd)
    EditText etConfirmPsd;
    @BindView(R.id.btn_save_psd)
    Button btnSavePsd;
    private UpdatePsdPresenter updatePsdPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_update_psd;
    }

    @Override
    protected void initData() {
        tvTitle.setText("修改密码");
        rlRight.setVisibility(View.GONE);
    }

    @Override
    protected BasePresenter createPresenter() {
        updatePsdPresenter = new UpdatePsdPresenter(this);
        return updatePsdPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.btn_save_psd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.btn_save_psd:
                String oldPsd = etOldPsd.getText().toString();
                String confirmPsd = etConfirmPsd.getText().toString();
                String newPsd = etNewPsd.getText().toString();
                if (StringUtil.isEmpty(oldPsd)) {
                    UIUtils.showToast("原密码不能为空!");
                    return;

                }

                if (StringUtil.isEmpty(newPsd)) {
                    UIUtils.showToast("新密码不能为空!");
                    return;
                }

                if (!confirmPsd.equals(newPsd)) {
                    UIUtils.showToast("请确认新密码");
                    return;
                }
                if (newPsd.length() < 8) {
                    UIUtils.showToast("密码长度不能少于8位");
                    return;
                }
                String user_id = BaseUtils.getJsonData("user_id", getUserId());
                String old_pwd = BaseUtils.getJsonData("old_pwd", oldPsd);
                String new_pwd = BaseUtils.getJsonData("new_pwd", newPsd);
                String[] str = new String[]{user_id, old_pwd, new_pwd};


                updatePsdPresenter.getDataFromNet("update_psd", HttpUrl.UPDATE_USER_PSD, AESOperator.getRandomString(), str);
                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    public void onUpdatePsdSuccessed() {
        UIUtils.showToast("密码修改成功");
        finish();
    }
}
