package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.FeedbackStatisticsContract;

/**
 * Created by pc on 2018/2/14.
 */

public class FeedbackStatisticsPresenter extends BasePresenter implements FeedbackStatisticsContract.Presenter {
    private final FeedbackStatisticsContract.View mView;

    public FeedbackStatisticsPresenter(FeedbackStatisticsContract.View view) {
        this.mView=view;
    }
}
