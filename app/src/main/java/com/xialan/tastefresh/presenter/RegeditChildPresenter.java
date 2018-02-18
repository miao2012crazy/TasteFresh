package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.RegeditChildContract;

/**
 * Created by Administrator on 2018/1/18.
 */

public class RegeditChildPresenter extends BasePresenter implements RegeditChildContract.Presenter {
    private final RegeditChildContract.View mView;

    public RegeditChildPresenter(RegeditChildContract.View view) {
        this.mView=view;
    }
}
