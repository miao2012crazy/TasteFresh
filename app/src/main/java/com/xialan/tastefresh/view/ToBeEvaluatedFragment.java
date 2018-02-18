package com.xialan.tastefresh.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.ToBeEvaluatedAdapter;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.ToBeEvaluatedBean;
import com.xialan.tastefresh.contract.ToBeEvaluatedContract;
import com.xialan.tastefresh.presenter.ToBeEvaluatedPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 */
public class ToBeEvaluatedFragment extends BaseFragment implements ToBeEvaluatedContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private ToBeEvaluatedPresenter toBeEvaluatedPresenter;
    private List<ToBeEvaluatedBean> mlist;

    @Override
    protected int getContentId() {
        return R.layout.fragment_tobe_evaluated;
    }

    @Override
    protected void loadData() {
        mlist = new ArrayList<>();
        for (int i=0;i<10;i++){
            ToBeEvaluatedBean toBeEvaluatedBean = new ToBeEvaluatedBean("img", "未测评" + i, "5"+i);
            mlist.add(toBeEvaluatedBean);
        }

        ToBeEvaluatedAdapter toBeEvaluatedAdapter = new ToBeEvaluatedAdapter(mlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(toBeEvaluatedAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToBeEvaluatedBean toBeEvaluatedBean = mlist.get(position);
                switch (view.getId()){
                    case R.id.btn_commit_report:
                   


                        startActivity(CommitReportActivity.class);
//                        InvestigationActivity
                        break;

                }

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        toBeEvaluatedPresenter = new ToBeEvaluatedPresenter(this);
        return toBeEvaluatedPresenter;
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
