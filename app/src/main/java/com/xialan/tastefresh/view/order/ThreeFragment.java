package com.xialan.tastefresh.view.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.ThreeAdapter;
import com.xialan.tastefresh.adapter.UserOrderAdapter;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.contract.ThreeContract;
import com.xialan.tastefresh.contract.UserOrderContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.ThreePresenter;
import com.xialan.tastefresh.presenter.UserOrderPresenter;
import com.xialan.tastefresh.view.CommitReportActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/3.
 */

public class ThreeFragment extends BaseFragment implements ThreeContract.View {

    private ThreePresenter threePresenter;
    private List<UserOrderBean.DataBean> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ThreeAdapter userOrderAdapter;

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_tobe_evaluated;
    }

    @Override
    protected void loadData() {
        mList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userOrderAdapter = new ThreeAdapter(R.layout.layout_order_three,mList);
        recyclerView.setAdapter(userOrderAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UserOrderBean.DataBean dataBean = mList.get(i);
                bundle.clear();
                bundle.putString("order_id",dataBean.getOrder_id());
                bundle.putString("img_url",dataBean.getImg_url());
                //退款金额
                bundle.putString("deposit",dataBean.getPrivate_deposit());
                bundle.putString("product_name",dataBean.getProduct_name());
                startActivityWithParamas(CommitReportActivity.class,bundle);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        threePresenter = new ThreePresenter(this);
        return threePresenter;
    }



    @Override
    public void onThreeDataSuccess(List<UserOrderBean.DataBean> dataBean) {
        mList.clear();
        mList.addAll(dataBean);
        userOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        String userId = getUserId();
        String item_type = BaseUtils.getJsonData("item_type", "3");
        String user_id = BaseUtils.getJsonData("user_id", userId);
        String[] strArr = {item_type, user_id};
        threePresenter.getDataFromNet("search_order", HttpUrl.SEARCH_ORDER, AESOperator.getRandomString(), strArr);
    }
}
