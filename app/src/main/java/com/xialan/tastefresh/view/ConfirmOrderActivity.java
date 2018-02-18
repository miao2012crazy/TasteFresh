package com.xialan.tastefresh.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.AddressBean;
import com.xialan.tastefresh.bean.ProductDetailBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.ConfirmOrderContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.ConfirmOrderPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ConfirmOrderActivity extends BaseActivity implements ConfirmOrderContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_tel)
    TextView tvAddressTel;
    @BindView(R.id.tv_address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.rg_group_pay)
    RadioGroup rgGroupPay;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.iv_product_img)
    ImageView ivProductImg;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_deposit)
    TextView tvDeposit;
    private AddressBean.DataBean defaultAddressBean=null;
    private ConfirmOrderPresenter confirmOrderPresenter;
    private ProductDetailBean.DataBean product_info;
    private String[] user_id_arr = {BaseUtils.getJsonData("user_id", getUserId())};

    @Override
    protected int setlayoutID() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initData() {
        tvTitle.setText("结算");
        product_info = (ProductDetailBean.DataBean) getIntent().getSerializableExtra("product_info");
        reSumeData();
        initAddress();
        if (user_id_arr==null){
            return;
        }
        confirmOrderPresenter.getDataFromNet("query_address", HttpUrl.SEARCH_USER_ADDRESS, AESOperator.getRandomString(), user_id_arr);
    }

    /**
     * 初始化地址
     */
    private void initAddress() {


    }

    private void reSumeData() {
        ImageLoader.getInstance().displayImage(product_info.getImg_url(), ivProductImg);
        tvProductName.setText(product_info.getProduct_name());
        tvDeposit.setText(product_info.getProduct_eval_price());
    }


    @Override
    protected BasePresenter createPresenter() {
        confirmOrderPresenter = new ConfirmOrderPresenter(this);
        return confirmOrderPresenter;
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

    @OnClick({R.id.rl_left, R.id.ll_address, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.ll_address:
                bundle.clear();
                bundle.putString("confirm_order","1");
                startActivityWithParamas(AddressActivity.class,bundle,0);
                break;
            case R.id.btn_pay:
                String[] str=null;
                //生成订单 成功 启动支付 失败 提示
                String msg = etMsg.getText().toString();
                String user_id = BaseUtils.getJsonData("user_id", getUserId());
                String product_id = BaseUtils.getJsonData("product_id", product_info.getProduct_id());
                String recv_address_id = BaseUtils.getJsonData("recv_address_id", defaultAddressBean.getAddress_no());
                if (!StringUtil.isEmpty(msg)){
                    String order_message = BaseUtils.getJsonData("order_message", msg);
                    str=new String[]{user_id,product_id,recv_address_id,order_message};
                }else{
                    str= new String[]{user_id, product_id, recv_address_id};
                }
                confirmOrderPresenter.getDataFromNet("creat_order",HttpUrl.CREAT_ORDER,AESOperator.getRandomString(),str);
                break;
        }
    }

    @Override
    public void getDataSuccessed(List<AddressBean.DataBean> dataBeans) {
        for (AddressBean.DataBean item:dataBeans){
            if (item.getIs_default().equals("1")){
                defaultAddressBean= item;
                setAddressInfo();
                return;
            }
        }
    }

    @Override
    public void creatOrderSuccessed() {
        //创建订单成功 选择支付方式
        int checkedRadioButtonId = rgGroupPay.getCheckedRadioButtonId();
        switch (checkedRadioButtonId){
            case R.id.rb_ali_pay:
                //支付宝支付
                UIUtils.showToast("支付宝支付 成功(虚拟)");
                break;
            case R.id.rb_wechat_pay:
                //微信支付
                UIUtils.showToast("微信支付 成功(虚拟)");
                break;
            case R.id.rb_bank_pay:
                //银联支付
                UIUtils.showToast("银联支付 成功(虚拟)");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==0){
            defaultAddressBean = (AddressBean.DataBean) data.getSerializableExtra("address_select");
            setAddressInfo();
        }
    }
    private void setAddressInfo(){
        tvAddressName.setText(defaultAddressBean.getRecv_name());
        tvAddressTel.setText(defaultAddressBean.getRecv_tel());
        tvAddressDetail.setText(defaultAddressBean.getAddress_detail());
    }
}
