package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.ReportProductBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.contract.RightContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.RightPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/23.
 */

public class RightFragment extends BaseFragment implements RightContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.search_value)
    SearchView searchValue;
    private RightPresenter rightPresenter;

    @Override
    protected int getContentId() {
        return R.layout.fragment_right;
    }

    @Override
    protected void loadData() {
        List<ReportProductBean> mlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ReportProductBean reportProductBean = new ReportProductBean("img", "我是产品标题" + i, "500" + i);
            mlist.add(reportProductBean);
        }
        ReportlistAdapter reportlistAdapter = new ReportlistAdapter(mlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(reportlistAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                startActivity(ReportDetailActivity.class);
            }
        });
        String s = searchValue.getQuery().toString();
        String search_value = BaseUtils.getJsonData("search_value", "雪花");
        String[] str = new String[]{search_value};
        rightPresenter.getDataFromNet("search_report", HttpUrl.SEARCH_REPORT, AESOperator.getRandomString(), str);
    }

    @Override
    protected BasePresenter createPresenter() {
        rightPresenter = new RightPresenter(this);
        return rightPresenter;
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

    @Override
    public void getDataFailed(String err_msg) {

    }
}
