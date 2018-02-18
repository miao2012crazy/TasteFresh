package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.LoginContract;

/**
 * Created by Administrator on 2018/1/16.
 */

public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {
    private final LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView=view;
    }



}
