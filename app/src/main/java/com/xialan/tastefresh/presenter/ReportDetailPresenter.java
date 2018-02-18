package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.ReportDetailContract;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ReportDetailPresenter extends BasePresenter implements ReportDetailContract.Presenter {
    private final ReportDetailContract.View mView;

    public ReportDetailPresenter(ReportDetailContract.View view) {
        this.mView=view;
    }
}
