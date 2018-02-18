package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.TryReportContract;

/**
 * Created by pc on 2018/2/14.
 */

public class TryReportPresenter extends BasePresenter implements TryReportContract.Presenter {
    private final TryReportContract.View mView;

    public TryReportPresenter(TryReportContract.View view) {
        this.mView=view;
    }
}
