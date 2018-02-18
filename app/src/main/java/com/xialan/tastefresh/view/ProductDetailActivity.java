package com.xialan.tastefresh.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.PecialuserAdapter;
import com.xialan.tastefresh.adapter.ProductnextAdapter;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.PecialUserBean;
import com.xialan.tastefresh.bean.ProductDetailBean;
import com.xialan.tastefresh.bean.ProductNextBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.ProductDetailContract;
import com.xialan.tastefresh.presenter.ProductDetailPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/18.
 */

public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.iv_product_img)
    ImageView ivProductImg;
    @BindView(R.id.iv_look_img)
    ImageView ivLookImg;
    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;
    @BindView(R.id.tv_product_desc)
    TextView tvProductDesc;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_deposit)
    TextView tvDeposit;
    @BindView(R.id.tv_product_origin_price)
    TextView tvProductOriginPrice;
    @BindView(R.id.tv_refund)
    TextView tvRefund;
    @BindView(R.id.btn_tryout)
    Button btnTryout;
    @BindView(R.id.recycler_pecial_user)
    RecyclerView recyclerPecialUser;
    @BindView(R.id.recycler_next_product)
    RecyclerViewPager recyclerNextProduct;
    private ProductDetailPresenter productDetailPresenter;
    private ProductnextAdapter productnextAdapter;
    private PecialuserAdapter pecialuserAdapter;
    private List<ProductDetailBean.DataBean.SpecialUserBean> specialUserBeans = new ArrayList<>();
    private List<ProductDetailBean.DataBean.ProductNextWeekBean> productNextWeekBeans = new ArrayList<>();
    private Thread thread;
    private ProductDetailBean.DataBean mDataBean=null;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initData() {
        String product_id = getIntent().getStringExtra("product_id");

        tvTitle.setText("产品详情");
        String[] str = {"product_id:" + product_id};
        initRecyclerNextProduct();
        initRecyclerPecialuser();
        productDetailPresenter.getProductDetail(AESOperator.getRandomString(), str);
    }

    /**
     * 初始化下周产品
     */
    private void initRecyclerNextProduct() {
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerNextProduct.setLayoutManager(layout);//setLayoutManager
        productnextAdapter = new ProductnextAdapter(productNextWeekBeans);
        recyclerNextProduct.setAdapter(productnextAdapter);
    }

    /**
     * 初始化特邀测评员
     */
    private void initRecyclerPecialuser() {
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerPecialUser.setLayoutManager(layout);
        pecialuserAdapter = new PecialuserAdapter(specialUserBeans);
        recyclerPecialUser.setAdapter(pecialuserAdapter);
        pecialuserAdapter.notifyDataSetChanged();
    }

    @Override
    protected BasePresenter createPresenter() {
        productDetailPresenter = new ProductDetailPresenter(this);
        return productDetailPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.rl_right, R.id.iv_look_img, R.id.btn_tryout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.rl_right:
                break;
            case R.id.iv_look_img:
                break;
            case R.id.btn_tryout:
                if (mDataBean!=null){
                    //立即试用
                    bundle.clear();
                    bundle.putSerializable("product_info", mDataBean);
                    startActivityWithParamas(ConfirmOrderActivity.class, bundle);
                }

                break;
        }
    }

    @Override
    public void onGetDataSuccess(ProductDetailBean.DataBean dataBean) {
        this.mDataBean = dataBean;
        specialUserBeans.clear();
        productNextWeekBeans.clear();
        specialUserBeans.addAll(dataBean.getSpecial_user());
        pecialuserAdapter.notifyDataSetChanged();
        productNextWeekBeans.addAll(dataBean.getProduct_next_week());
        productnextAdapter.notifyDataSetChanged();
        initProductDetail(dataBean);
    }

    private void initProductDetail(final ProductDetailBean.DataBean dataBean) {
        ImageLoader.getInstance().displayImage(dataBean.getImg_url(), ivProductImg);
        tvProductTitle.setText(dataBean.getProduct_name());
        tvProductDesc.setText(dataBean.getProduct_desc());
        tvDeposit.setText(dataBean.getProduct_eval_price());
        tvProductOriginPrice.setText("原价：" + dataBean.getProduct_orign_price() + "元");
        tvProductNum.setText("限量" + dataBean.getProduct_total_num() + "件,已抢" + dataBean.getProduct_buy_num() + "件");
        tvRefund.setText("自签收后14个自然日内填写测评报告,可退" + dataBean.getProduct_eval_price() + "元");
        progressBar.setMax(Integer.parseInt(dataBean.getProduct_total_num()));
        progressBar.setProgress(Integer.parseInt(dataBean.getProduct_buy_num()));


//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//
//                    UIUtils.runInMainThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String timeForSurplus = UIUtils.getTimeForSurplus(dataBean.getStamp());
//                            String[] split = timeForSurplus.split(":");
//                            tvHour.setText(split[0]);
//                            tvMinute.setText(split[1]);
//                            tvSecond.setText(split[2]);
//                        }
//                    });
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.start();


    }

    @Override
    public void onGetDataFailed(String err_msg) {
        UIUtils.showToast(ParseUtils.showErrMsg(err_msg));
    }

    @Override
    public void getDataFailed(String err_msg) {

    }
}
