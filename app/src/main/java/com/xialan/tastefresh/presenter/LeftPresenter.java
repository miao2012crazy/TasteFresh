package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.LeftContract;

/**
 * Created by Administrator on 2018/1/11.
 */

public class LeftPresenter extends BasePresenter implements LeftContract.Presenter {
    private final LeftContract.View mView;

    public LeftPresenter(LeftContract.View view) {
        this.mView=view;
    }
}
