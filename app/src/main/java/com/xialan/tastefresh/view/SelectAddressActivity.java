package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.SelectAddressContract;
import com.xialan.tastefresh.presenter.SelectAddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/2.
 */

public class SelectAddressActivity extends BaseActivity implements SelectAddressContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SelectAddressPresenter selectAddressPresenter;

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    protected int setlayoutID() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        selectAddressPresenter = new SelectAddressPresenter(this);
        return selectAddressPresenter;
    }
}
