package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.contract.TryReportContract;
import com.xialan.tastefresh.presenter.TryReportPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by pc on 2018/2/14.
 */

public class TryReportFragment extends BaseFragment implements TryReportContract.View {
    @BindView(R.id.iv_product_img)
    ImageView ivProductImg;
    @BindView(R.id.iv_look_img)
    ImageView ivLookImg;
    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;
    @BindView(R.id.tv_product_desc)
    TextView tvProductDesc;
    Unbinder unbinder;

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_try_report;
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected BasePresenter createPresenter() {
        TryReportPresenter tryReportPresenter = new TryReportPresenter(this);
        return tryReportPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
