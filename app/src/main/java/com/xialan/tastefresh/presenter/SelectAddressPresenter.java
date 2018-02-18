package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.SelectAddressContract;

/**
 * Created by Administrator on 2018/2/2.
 */

public class SelectAddressPresenter extends BasePresenter implements SelectAddressContract.Presenter {

    private final SelectAddressContract.View mView;

    public SelectAddressPresenter(SelectAddressContract.View view) {
        this.mView=view;
    }
}
