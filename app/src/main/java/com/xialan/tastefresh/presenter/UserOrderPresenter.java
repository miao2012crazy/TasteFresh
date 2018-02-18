package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.UserOrderContract;

/**
 * Created by Administrator on 2018/2/3.
 */

public class UserOrderPresenter extends BasePresenter implements UserOrderContract.Presenter {
    private final UserOrderContract.View mView;

    public UserOrderPresenter(UserOrderContract.View view) {
        this.mView=view;
    }
}
