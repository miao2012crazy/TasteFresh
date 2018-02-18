package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.QuarterProductAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.YearProductContract;
import com.xialan.tastefresh.presenter.YearProductPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class YearProductActivity extends BaseActivity implements YearProductContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private YearProductPresenter yearProductPresenter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_year_product;
    }

    @Override
    protected void initData() {

//        List<QuarterProductBean> mList = new ArrayList<>();
//        QuarterProductBean quarterProductBean0 = new QuarterProductBean("2017年9月>>", R.mipmap.quarter_01);
//        QuarterProductBean quarterProductBean1 = new QuarterProductBean("2017年10月>>", R.mipmap.quarter_01);
//        QuarterProductBean quarterProductBean2 = new QuarterProductBean("2017年11月>>", R.mipmap.quarter_01);
//        QuarterProductBean quarterProductBean3 = new QuarterProductBean("2017年12月>>", R.mipmap.quarter_01);
//        QuarterProductBean quarterProductBean4 = new QuarterProductBean("2018年1月>>", R.mipmap.quarter_02);
//        QuarterProductBean quarterProductBean5 = new QuarterProductBean("2018年2月>>", R.mipmap.quarter_03);
//        mList.add(quarterProductBean0);
//        mList.add(quarterProductBean1);
//        mList.add(quarterProductBean2);
//        mList.add(quarterProductBean3);
//        mList.add(quarterProductBean4);
//        mList.add(quarterProductBean5);
//        rlRight.setVisibility(View.GONE);
//        tvTitle.setText("年度测评区");
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        QuarterProductAdapter quarterProductAdapter = new QuarterProductAdapter(mList);
//        recyclerView.setAdapter(quarterProductAdapter);
//        quarterProductAdapter.notifyDataSetChanged();
//        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                startActivity(ProductListActivity.class);
//            }
//        });
    }

    @Override
    protected BasePresenter createPresenter() {
        yearProductPresenter = new YearProductPresenter(this);

        return yearProductPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
