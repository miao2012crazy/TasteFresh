package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.ToBeEvaluatedContract;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ToBeEvaluatedPresenter extends BasePresenter implements ToBeEvaluatedContract.Presenter {
    private final ToBeEvaluatedContract.View mView;

    public ToBeEvaluatedPresenter(ToBeEvaluatedContract.View view) {
        this.mView=view;
    }
}
