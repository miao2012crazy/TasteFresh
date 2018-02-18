package com.xialan.tastefresh.view;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.InvestigationContract;
import com.xialan.tastefresh.presenter.InvestigationPresenter;

/**
 * Created by Administrator on 2018/1/22.
 */

public class InvestigationActivity extends BaseActivity implements InvestigationContract.View {

    private InvestigationPresenter investigationPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_investigation;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        investigationPresenter = new InvestigationPresenter(this);
        return investigationPresenter;
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
