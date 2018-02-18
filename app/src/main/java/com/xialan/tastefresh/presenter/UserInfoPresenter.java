package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.UserInfoContract;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UserInfoPresenter extends BasePresenter implements UserInfoContract.Presenter {
    private final UserInfoContract.View mView;

    public UserInfoPresenter(UserInfoContract.View view) {
        this.mView=view;
    }
}
