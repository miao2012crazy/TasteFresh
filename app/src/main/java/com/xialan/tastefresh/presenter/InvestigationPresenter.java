package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.InvestigationContract;

/**
 * Created by Administrator on 2018/1/22.
 */

public class InvestigationPresenter extends BasePresenter implements InvestigationContract.Presenter {
    private final InvestigationContract.View mView;

    public InvestigationPresenter(InvestigationContract.View view) {
        this.mView=view;
    }
}
