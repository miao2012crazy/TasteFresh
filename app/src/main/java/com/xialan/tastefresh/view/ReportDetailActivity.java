package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.CustomFragmentPagerAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.ReportDetailContract;
import com.xialan.tastefresh.presenter.ReportDetailPresenter;
import com.xialan.tastefresh.view.order.FourFragment;
import com.xialan.tastefresh.view.order.ThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ReportDetailActivity extends BaseActivity implements ReportDetailContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.tab_report)
    TabLayout tabReport;
    @BindView(R.id.view_pager_report)
    ViewPager viewPagerReport;
    private ReportDetailPresenter reportDetailPresenter;
    private String[] titles={"待测评","已测评"};
    @Override
    protected int setlayoutID() {
        return R.layout.activity_report;
    }

    @Override
    protected void initData() {
        tvTitle.setText("测评报告");
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FeedbackStatisticsFragment());
        fragmentList.add(new TryReportFragment());
        CustomFragmentPagerAdapter customPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPagerReport.setAdapter(customPagerAdapter);
        rlRight.setVisibility(View.GONE);
        tabReport.setupWithViewPager(viewPagerReport);
        tabReport.getTabAt(0).setText("反馈统计");
        tabReport.getTabAt(1).setText("试用报告");
    }

    @Override
    protected BasePresenter createPresenter() {
        reportDetailPresenter = new ReportDetailPresenter(this);
        return reportDetailPresenter;
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
