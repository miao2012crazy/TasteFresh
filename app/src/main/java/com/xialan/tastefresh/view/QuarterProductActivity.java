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
import com.xialan.tastefresh.bean.QueryIndexBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.contract.QuarterProductContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.QuarterProductPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class QuarterProductActivity extends BaseActivity implements QuarterProductContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private QuarterProductPresenter quarterProductPresenter;
    private List<QueryIndexBean.DataBean.DateListBean> mList;
    private QuarterProductAdapter quarterProductAdapter;
    private String url="";
    @Override
    protected int setlayoutID() {
        return R.layout.activity_quarter_product;
    }

    @Override
    protected void initData() {
        String key = getIntent().getStringExtra("key");
        switch (key){
            case "2":
                tvTitle.setText("当季测评区");
                url=HttpUrl.QUARTER_PRODUCT_INDEX;
                break;
            case "3":
                tvTitle.setText("往季测评区");
                url=HttpUrl.QUARTER_PAST_PRODUCT_INDEX;
                break;
        }
        rlRight.setVisibility(View.GONE);
        mList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        quarterProductAdapter = new QuarterProductAdapter(mList);
        recyclerView.setAdapter(quarterProductAdapter);
        quarterProductAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                QueryIndexBean.DataBean.DateListBean dateListBean = mList.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("start",dateListBean.getStart_time());
                bundle.putString("end", dateListBean.getEnd_time());
                bundle.putString("month", dateListBean.getMonth());
                startActivityWithParamas(ProductListActivity.class,bundle);
            }
        });


        quarterProductPresenter.getDataFromNet("",url,AESOperator.getRandomString());
    }

    @Override
    protected BasePresenter createPresenter() {
        quarterProductPresenter = new QuarterProductPresenter(this);
        return quarterProductPresenter;
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
    public void getDataSuccessed(List<QueryIndexBean.DataBean.DateListBean> dataBeans) {
        mList.clear();
        mList.addAll(dataBeans);
        quarterProductAdapter.notifyDataSetChanged();
    }
}
