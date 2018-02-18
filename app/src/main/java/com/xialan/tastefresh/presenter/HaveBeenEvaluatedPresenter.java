package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.HaveBeenEvaluatedContract;

/**
 * Created by Administrator on 2018/1/19.
 */


public class HaveBeenEvaluatedPresenter extends BasePresenter implements HaveBeenEvaluatedContract.Presenter {
    private final HaveBeenEvaluatedContract.View mView;

    public HaveBeenEvaluatedPresenter(HaveBeenEvaluatedContract.View view) {
        this.mView=view;
    }
}
