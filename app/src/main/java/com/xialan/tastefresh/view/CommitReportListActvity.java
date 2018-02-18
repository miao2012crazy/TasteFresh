package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.CustomFragmentPagerAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.ProductReportListContract;
import com.xialan.tastefresh.fragmentfactory.MainFragmentFactory;
import com.xialan.tastefresh.presenter.ProductReportListPresenter;
import com.xialan.tastefresh.view.order.FourFragment;
import com.xialan.tastefresh.view.order.OneFragment;
import com.xialan.tastefresh.view.order.ThreeFragment;
import com.xialan.tastefresh.view.order.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CommitReportListActvity extends BaseActivity implements ProductReportListContract.View {


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
    private ProductReportListPresenter productReportListPresenter;
    private String[] titles={"待测评","已测评"};
    @Override
    protected int setlayoutID() {
        return R.layout.activity_report;
    }

    @Override
    protected void initData() {
        tvTitle.setText("提交试用报告");
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());
        CustomFragmentPagerAdapter customPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPagerReport.setAdapter(customPagerAdapter);
        rlRight.setVisibility(View.GONE);
        tabReport.setupWithViewPager(viewPagerReport);
        tabReport.getTabAt(0).setText("待测评");
        tabReport.getTabAt(1).setText("已测评");
    }

    @Override
    protected BasePresenter createPresenter() {
        productReportListPresenter = new ProductReportListPresenter(this);
        return productReportListPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.tab_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.tab_report:
                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

}
