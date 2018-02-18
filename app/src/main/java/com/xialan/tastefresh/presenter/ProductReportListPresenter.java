package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.ProductReportListContract;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ProductReportListPresenter extends BasePresenter implements ProductReportListContract.Presenter {
    private final ProductReportListContract.View mView;

    public ProductReportListPresenter(ProductReportListContract.View view) {
        this.mView=view;
    }
}
