package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.SettingContract;

/**
 * Created by Administrator on 2018/1/23.
 */

public class SettingPresenter extends BasePresenter implements SettingContract.Presenter {
    private final SettingContract.View mView;

    public SettingPresenter(SettingContract.View view) {
        this.mView=view;
    }
}
