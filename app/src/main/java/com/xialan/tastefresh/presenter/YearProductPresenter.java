package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.YearProductContract;

/**
 * Created by Administrator on 2018/1/23.
 */

public class YearProductPresenter extends BasePresenter implements YearProductContract.Presenter {
    private final YearProductContract.View mView;

    public YearProductPresenter(YearProductContract.View view) {
        this.mView=view;
    }



}
