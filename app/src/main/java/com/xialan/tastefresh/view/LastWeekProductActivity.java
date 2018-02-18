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
import com.xialan.tastefresh.adapter.LastWeekProductAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.LastWeekProductBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.LastWeekProductContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.LastWeekProductPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */
public class LastWeekProductActivity extends BaseActivity implements LastWeekProductContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private LastWeekProductPresenter lastWeekProductPresenter;
    private List<LastWeekProductBean.DataBean.ProductListWeekBean> mList;
    private LastWeekProductAdapter lastWeekProductAdapter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_last_week_product;
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        tvTitle.setText("上周测评区");
        rlRight.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lastWeekProductAdapter = new LastWeekProductAdapter(mList);
        recyclerView.setAdapter(lastWeekProductAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                String product_id = mList.get(i).getProduct_id();
                bundle.clear();
                bundle.putString("product_id",product_id);
                startActivityWithParamas(ProductDetailActivity.class,bundle);
            }
        });
        lastWeekProductPresenter.getDataFromNet("tag",HttpUrl.LAST_WEEK_PRODUCT, AESOperator.getRandomString());
    }

    @Override
    protected BasePresenter createPresenter() {
        lastWeekProductPresenter = new LastWeekProductPresenter(this);
        return lastWeekProductPresenter;
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
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void getDataSuccess(List<LastWeekProductBean.DataBean.ProductListWeekBean> productListWeekBeans) {
        mList.clear();
        mList.addAll(productListWeekBeans);
        lastWeekProductAdapter.notifyDataSetChanged();
    }
}
