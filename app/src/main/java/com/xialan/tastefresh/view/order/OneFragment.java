package com.xialan.tastefresh.view.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.UserOrderAdapter;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.OneContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.OnePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/3.
 */

public class OneFragment extends BaseFragment implements OneContract.View {

    private OnePresenter onePresenter;
    private List<UserOrderBean.DataBean> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private UserOrderAdapter userOrderAdapter;
    private String userId;

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
        userOrderAdapter = new UserOrderAdapter(R.layout.layout_order_one,mList);
        recyclerView.setAdapter(userOrderAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onItemChildClick(adapter, view, position);
                UserOrderBean.DataBean dataBean = mList.get(position);
                switch (view.getId()){
                    case R.id.tv_cancel:
                        //取消订单
                        String user_id = BaseUtils.getJsonData("user_id", userId);
                        String order_id = BaseUtils.getJsonData("order_id", dataBean.getOrder_id());
                        String[] strArr = {order_id, user_id};
                        onePresenter.getDataFromNet("cancel_order", HttpUrl.CANCEL_ORDER, AESOperator.getRandomString(), strArr);
                        break;
                    case R.id.tv_confirm:
                        //立即支付
                        UIUtils.showToast("立即支付");
                        break;



                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        onePresenter = new OnePresenter(this);
        return onePresenter;
    }


    @Override
    public void onOneDataSuccess(List<UserOrderBean.DataBean> dataBean) {
        mList.clear();
        mList.addAll(dataBean);
        userOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelDataSuccess() {
        UIUtils.showToast("订单取消成功!");
        //订单取消成功
        refreshOrder();
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshOrder();
    }

    private void refreshOrder(){
        userId = getUserId();
        String item_type = BaseUtils.getJsonData("item_type", "0");
        String user_id = BaseUtils.getJsonData("user_id", userId);
        String[] strArr = {item_type, user_id};
        onePresenter.getDataFromNet("search_order", HttpUrl.SEARCH_ORDER, AESOperator.getRandomString(), strArr);
    }


}
