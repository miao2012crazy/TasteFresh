package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.UserCenterContract;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UserCenterPresenter extends BasePresenter implements UserCenterContract.Presenter {
    private final UserCenterContract.View mView;

    public UserCenterPresenter(UserCenterContract.View view) {
        this.mView=view;
    }
}
