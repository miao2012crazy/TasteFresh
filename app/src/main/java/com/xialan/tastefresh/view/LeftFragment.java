package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.LeftItemAdapter;
import com.xialan.tastefresh.applaction.MyApplaction;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.LeftBean;
import com.xialan.tastefresh.bean.UserInfoBean;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.LeftContract;
import com.xialan.tastefresh.contract.QuarterProductContract;
import com.xialan.tastefresh.presenter.LeftPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/11.
 */

public class LeftFragment extends BaseFragment implements LeftContract.View {
    @BindView(R.id.iv_user_head_img)
    ImageView ivUserHeadImg;
    @BindView(R.id.tv_user_nick_name)
    TextView tvUserNickName;
    @BindView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    private LeftPresenter leftPresenter;
    private List<LeftBean> mlist;
    private List<LeftBean> list;
    private int[] icon = {R.mipmap.ic_commit_report, R.mipmap.last_week, R.mipmap.day_90, R.mipmap.day_365, R.mipmap.user, R.mipmap.setting};
    private String[] title = {"提交报告", "上周评测区", "季度评测区", "年度评测区", "我的", "设置"};

    @Override
    protected int getContentId() {
        return R.layout.layout_left_list;
    }

    @Override
    protected void loadData() {
        mlist = new ArrayList<>();
        recyclerLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        LeftItemAdapter leftItemAdapter = new LeftItemAdapter(mlist);
        recyclerLeft.setAdapter(leftItemAdapter);
        for (int i = 0; i < icon.length; i++) {
            LeftBean leftBean = new LeftBean(title[i], UIUtils.getDrawable(icon[i]));
            mlist.add(leftBean);
        }
        leftItemAdapter.notifyDataSetChanged();
        //此处rxjava 代码有bug  未知bug  暂无法解决
//        Observable.create(new Observable.OnSubscribe<List<LeftBean>>() {
//            @Override
//            public void call(Subscriber<? super List<LeftBean>> subscriber) {
//                list.clear();
//                for (int i=0;i<icon.length;i++){
//                    LeftBean leftBean = new LeftBean(title[i], UIUtils.getDrawable(icon[i]));
//                    list.add(leftBean);
//                }
//                subscriber.onNext(list);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<LeftBean>>() {
//                    @Override
//                    public void call(List<LeftBean> leftBeans) {
//
//                    }
//                });
        recyclerLeft.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(CommitReportListActvity.class);
                        break;
                    case 1:
                        startActivity(LastWeekProductActivity.class);
                        break;
                    case 2:
                        bundle.clear();
                        bundle.putString("key","2");
                        startActivityWithParamas(QuarterProductActivity.class,bundle);
                        break;
                    case 3:
                        bundle.clear();
                        bundle.putString("key","3");
                        startActivityWithParamas(QuarterProductActivity.class,bundle);
                        break;
                    case 4:
                        startActivity(UserCenterActivity.class);
                        break;
                    case 5:
                        startActivity(SettingActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        leftPresenter = new LeftPresenter(this);
        return leftPresenter;
    }


    @Override
    public void onResume() {
        super.onResume();
        UserInfoBean userBean = MyApplaction.getUserBean();
        if (userBean!=null){
            UserInfoBean.DataBean data = userBean.getData();
            ImageLoaderManager.displayHeadIcon(data.getUser_head_img(),ivUserHeadImg);
            tvUserNickName.setText(data.getUser_nick_name());
        }


    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
