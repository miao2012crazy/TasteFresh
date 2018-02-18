package com.xialan.tastefresh.view;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xialan.tastefresh.MainActivity;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.MainAdapter;
import com.xialan.tastefresh.base.BaseFragment;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.HomeBeanCon;
import com.xialan.tastefresh.bean.MainBean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.commonutil.AESHelper;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.MainContract;
import com.xialan.tastefresh.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018/1/11.
 */
public class MainFragment extends BaseFragment implements MainContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;
    private MainPresenter mainPresenter;
    private List<MultipleItem> mlist;
    private MainActivity activity;
    private MainAdapter mainAdapter;
    private Thread thread;

    @Override
    protected int getContentId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void loadData() {
        tvTitle.setText("首页");
        activity = (MainActivity) getActivity();
        mlist = new ArrayList<>();
        mainAdapter = new MainAdapter(mlist);
        recyclerMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMain.setAdapter(mainAdapter);
        recyclerMain.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int nextProductStartPosition = mainPresenter.getNextProductStartPosition();
//                int loopActivityPosition = mainPresenter.getLoopActivityPosition();
                if (i == 0) {
//                    List<MainBean.DataBean.LoopListBean> data = baseQuickAdapter.getData();
                    UIUtils.showToast(i + "");
                    return;
                }
                if (i == 1 || i == nextProductStartPosition + 1) {
                    return;
                }

                if (i > nextProductStartPosition) {
                    Dialog dialog = new Dialog(getActivity());
                    View inflate = UIUtils.inflate(R.layout.layout_next_product);
                    dialog.setContentView(inflate);

                    Window window = dialog.getWindow();
                    window.setBackgroundDrawableResource(android.R.color.transparent);
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    window.setGravity(Gravity.CENTER);
//                    attributes.width=UIUtils.dip2px(300);
                    attributes.height = UIUtils.dip2px(275);
                    dialog.show();
                } else {
                    MultipleItem multipleItem = mlist.get(i);
                    HomeBeanCon dataBean = multipleItem.getDataBean();
                    bundle.clear();
                    bundle.putString("product_id",dataBean.getProduct_id());
                    startActivityWithParamas(ProductDetailActivity.class,bundle);
                }
            }
        });
        try {
//            String key = AESHelper.generateKeyString();
//            String randomString = AESOperator.getRandomString();
            String randomString = "1234567890123456";
            mainPresenter.getDataForMain(randomString);
        } catch (Exception ex) {

        }

    }

    @Override
    protected BasePresenter createPresenter() {
        mainPresenter = new MainPresenter(this);
        return mainPresenter;
    }


    @OnClick({R.id.rl_left, R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                activity.openLeftLayout();
                break;
            case R.id.rl_right:
                activity.openRightLayout();
                break;
        }
    }


    @Override
    public void OnGetDataForMainSuccess(List<MultipleItem> multipleItem) {
        mlist.clear();
        mlist.addAll(multipleItem);
        mainAdapter.notifyDataSetChanged();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    UIUtils.runInMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mainAdapter.notifyItemChanged(mainPresenter.getRefreshPosition());
                            mainAdapter.notifyItemChanged(1);
                        }
                    });
                }
            }
        });
        thread.start();
    }


    @Override
    public void onGetDataForMainFailed(String err_msg) {

    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
