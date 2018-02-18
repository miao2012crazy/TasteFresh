package com.xialan.tastefresh.view;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.HaveBeenEvaluatedContract;
import com.xialan.tastefresh.presenter.HaveBeenEvaluatedPresenter;

/**
 * Created by Administrator on 2018/1/19.
 */

public class HaveBeenEvaluatedFragment extends BaseFragment implements HaveBeenEvaluatedContract.View {

    private HaveBeenEvaluatedPresenter haveBeenEvaluatedPresenter;

    @Override
    protected int getContentId() {

        return R.layout.fragment_tobe_evaluated;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        haveBeenEvaluatedPresenter = new HaveBeenEvaluatedPresenter(this);
        return haveBeenEvaluatedPresenter;
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
