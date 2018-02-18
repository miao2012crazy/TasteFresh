package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.CustomFragmentPagerAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.contract.UserOrderContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.UserOrderPresenter;
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
 * Created by Administrator on 2018/2/3.
 */

public class UserOrderActivity extends BaseActivity implements UserOrderContract.View {


    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.tab_order)
    TabLayout tabOrder;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private UserOrderPresenter userOrderPresenter;
    private List<UserOrderBean.DataBean> mList;

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    protected int setlayoutID() {
        return R.layout.activity_order;
    }

    @Override
    protected void initData() {

        initViewPager();
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());

        mList = new ArrayList<>();
        tabOrder.addTab(tabOrder.newTab());
        tabOrder.addTab(tabOrder.newTab());
        tabOrder.addTab(tabOrder.newTab());
        tabOrder.addTab(tabOrder.newTab());
        CustomFragmentPagerAdapter customPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(customPagerAdapter);
        // 使用 TabLayout 和 ViewPager 相关联
        tabOrder.setupWithViewPager(viewPager);
        // TabLayout指示器添加文本
        tabOrder.getTabAt(0).setText("待付款");
        tabOrder.getTabAt(1).setText("待收货");
        tabOrder.getTabAt(2).setText("待测评");
        tabOrder.getTabAt(3).setText("已测评");
    }

    @Override
    protected BasePresenter createPresenter() {
        userOrderPresenter = new UserOrderPresenter(this);
        return userOrderPresenter;
    }
    @OnClick(R.id.rl_left)
    public void onViewClicked() {
        finish();
    }

}
