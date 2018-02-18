package com.xialan.tastefresh.view.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.FourAdapter;
import com.xialan.tastefresh.adapter.UserOrderAdapter;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.contract.FourContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.FourPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/2/3.
 */

public class FourFragment extends BaseFragment implements FourContract.View {

    private FourPresenter  fourPresenter;
    private List<UserOrderBean.DataBean> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FourAdapter userOrderAdapter;

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
        userOrderAdapter = new FourAdapter(R.layout.layout_order_four,mList);
        recyclerView.setAdapter(userOrderAdapter);
    }

    @Override
    protected BasePresenter createPresenter() {
        fourPresenter = new FourPresenter(this);
        return fourPresenter;
    }

    @Override
    public void onFourDataSuccess(List<UserOrderBean.DataBean> dataBean) {
        mList.clear();
        mList.addAll(dataBean);
        userOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        String userId = getUserId();
        String item_type = BaseUtils.getJsonData("item_type", "6");
        String user_id = BaseUtils.getJsonData("user_id", userId);
        String[] strArr = {item_type, user_id};
        fourPresenter.getDataFromNet("search_order", HttpUrl.SEARCH_ORDER, AESOperator.getRandomString(), strArr);
    }
}
