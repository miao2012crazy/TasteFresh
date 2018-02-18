package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.ProductListAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.bean.MultipleItemForQuarter;
import com.xialan.tastefresh.bean.ProductDetailBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.contract.ProductListContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.ProductListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */
public class ProductListActivity extends BaseActivity implements ProductListContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ProductListPresenter productListPresenter;
    private String start;
    private String end;
    private String month;
    private List<MultipleItemForQuarter> mList;
    private ProductListAdapter productListAdapter;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void initData() {
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        month = getIntent().getStringExtra("month");

        mList = new ArrayList<>();
        rlRight.setVisibility(View.GONE);
        tvTitle.setText(month+"月");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productListAdapter = new ProductListAdapter(mList);
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MultipleItemForQuarter multipleItemForQuarter = mList.get(i);
                try{
                String product_id = multipleItemForQuarter.getWeekTimeBean().getProduct_id();
                    bundle.clear();
                    bundle.putString("product_id",product_id);
                    startActivityWithParamas(ProductDetailActivity.class,bundle);
                }catch (Exception ex){
                    return;
                }
            }
        });
        String[] str={"\"start_time\":"+start,"\"end_time\":"+end};
        productListPresenter.getDataFromNet("tag", HttpUrl.SEARCH_PRODUCT_LIST,AESOperator.getRandomString(),str);
    }

    @Override
    protected BasePresenter createPresenter() {
        productListPresenter = new ProductListPresenter(this);
        return productListPresenter;
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

    @Override
    public void getDataSuccessed(List<MultipleItemForQuarter> multipleItemForQuarters) {
        mList.clear();
        mList.addAll(multipleItemForQuarters);
        productListAdapter.notifyDataSetChanged();
    }
}
