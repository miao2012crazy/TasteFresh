package com.xialan.tastefresh.view.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.xialan.tastefresh.contract.TwoContract;
import com.xialan.tastefresh.contract.UserOrderContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.TwoPresenter;
import com.xialan.tastefresh.presenter.UserOrderPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/3.
 */

public class TwoFragment extends BaseFragment implements UserOrderContract.View, TwoContract.View {

    private TwoPresenter twoPresenter;
    private List<UserOrderBean.DataBean> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private UserOrderAdapter userOrderAdapter;

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
        userOrderAdapter = new UserOrderAdapter(R.layout.layout_order_two,mList);
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
                    case R.id.tv_get_shipment_info:
                        //查看物流
//                        String user_id = BaseUtils.getJsonData("user_id", userId);
//                        String order_id = BaseUtils.getJsonData("order_id", dataBean.getOrder_id());
//                        String[] strArr = {order_id, user_id};
//                        onePresenter.getDataFromNet("cancel_order", HttpUrl.CANCEL_ORDER, AESOperator.getRandomString(), strArr);
                        break;
                    case R.id.tv_confirm:
                        //立即支付
                        UIUtils.showToast("确认收货");
                        break;



                }
            }
        });


    }

    @Override
    protected BasePresenter createPresenter() {
        twoPresenter = new TwoPresenter(this);
        return twoPresenter;
    }




    @Override
    public void onTwoDataSuccess(List<UserOrderBean.DataBean> dataBean) {
        mList.clear();
        mList.addAll(dataBean);
        userOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        String userId = getUserId();
        String item_type = BaseUtils.getJsonData("item_type", "1");
        String user_id = BaseUtils.getJsonData("user_id", userId);
        String[] strArr = {item_type, user_id};
        twoPresenter.getDataFromNet("search_order", HttpUrl.SEARCH_ORDER, AESOperator.getRandomString(), strArr);

    }
}
