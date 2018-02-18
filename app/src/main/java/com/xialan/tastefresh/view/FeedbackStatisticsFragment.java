package com.xialan.tastefresh.view;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.FeedbackStatisticsContract;
import com.xialan.tastefresh.presenter.FeedbackStatisticsPresenter;

/**
 * Created by pc on 2018/2/14.
 */

public class FeedbackStatisticsFragment extends BaseFragment implements FeedbackStatisticsContract.View {

    private FeedbackStatisticsPresenter feedbackStatisticsPresenter;

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_feedback_statistics;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        feedbackStatisticsPresenter = new FeedbackStatisticsPresenter(this);
        return feedbackStatisticsPresenter;
    }
}
