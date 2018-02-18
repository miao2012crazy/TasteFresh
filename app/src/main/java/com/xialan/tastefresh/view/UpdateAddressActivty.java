package com.xialan.tastefresh.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.applaction.MyApplaction;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.AddressBean;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.StringUtil;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.UpdateAddressContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.UpdateAddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UpdateAddressActivty extends BaseActivity implements UpdateAddressContract.View {

    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.et_recive_name)
    EditText etReciveName;
    @BindView(R.id.et_recive_tel)
    EditText etReciveTel;
    @BindView(R.id.ll_recive_area)
    LinearLayout llReciveArea;
    @BindView(R.id.et_recive_address_detail)
    EditText etReciveAddressDetail;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.cb_address_default)
    CheckBox cbAddressDefault;
    private UpdateAddressPresenter updateAddressPresenter;
    private AddressBean.DataBean update_address;
    private CityPickerView mPicker = new CityPickerView();
    private String type = "0";
    private String is_default = "0";

    @Override
    protected int setlayoutID() {
        return R.layout.activity_update_address;
    }

    @Override
    protected void initData() {
        initAddressPicker();
        update_address = (AddressBean.DataBean) getIntent().getSerializableExtra("update_address");
        if (update_address == null) {
            //添加地址
            tvTitle.setText("添加地址");
        } else {
            type = "1";
            //修改地址 回显数据
            tvTitle.setText("编译地址");
            etReciveName.setText(update_address.getRecv_name());
            etReciveTel.setText(update_address.getRecv_tel());
            etReciveAddressDetail.setText(update_address.getAddress_detail());
            tvArea.setText(update_address.getAddress_area());
            cbAddressDefault.setChecked(update_address.getIs_default().equals("1"));
        }
        rlRight.setVisibility(View.GONE);
    }

    private void initAddressPicker() {
        //初始化全部地区数据
        mPicker.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder()
                .setCustomItemLayout(R.layout.layout_picker_item)
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setLineColor("#dcdcdc")
                .confirmTextSize(17)//确认按钮文字大小
                .cancelTextColor("#666666")//取消按钮文字颜色
                .confirTextColor("#666666")//确认按钮文字颜色
                .build();
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份
                if (province == null) {
                    return;
                }
                //城市
                if (city == null) {
                    return;
                }
                //地区
                if (district == null) {
                    return;
                }
                tvArea.setText(province.getName() + city.getName() + district.getName());
                etReciveAddressDetail.setText(tvArea.getText().toString());
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        updateAddressPresenter = new UpdateAddressPresenter(this);
        return updateAddressPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.tv_area, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.tv_area:
                //区域选择
                mPicker.showCityPicker();
                break;
            case R.id.btn_save:
                String[] strings = initSendData();
                if (strings == null) {
                    return;
                }
                updateAddressPresenter.getDataFromNet("update", HttpUrl.ADDRESS_MANAGE, AESOperator.getRandomString(), strings);
                break;
        }
    }

    private String[] initSendData() {
        String[] str = null;

        //保存
        String recv_name = etReciveName.getText().toString();
        String recv_tel = etReciveTel.getText().toString();
        String address_area = tvArea.getText().toString();
        String address_detail = etReciveAddressDetail.getText().toString();
        if (StringUtil.isEmpty(recv_name) || StringUtil.isEmpty(recv_tel) || StringUtil.isEmpty(address_area) || StringUtil.isEmpty(address_detail)) {
            UIUtils.showToast("当前地址信息不完整");
            return null;
        }
        if (address_area.equals(address_detail)) {
            UIUtils.showToast("详细地址不完整");
            return null;
        }
        is_default=cbAddressDefault.isChecked()?"1":"0";
        String addressArea = BaseUtils.getJsonData("address_area", address_area);
        String addressDetail = BaseUtils.getJsonData("address_detail", address_detail);
        String recvTel = BaseUtils.getJsonData("recv_tel", recv_tel);
        String recvName = BaseUtils.getJsonData("recv_name", recv_name);
        String type_item = BaseUtils.getJsonData("type", type);
        String user_id = BaseUtils.getJsonData("user_id", MyApplaction.getUserBean().getData().getUser_id());
        String isDefault = BaseUtils.getJsonData("is_default", is_default);
        if (!type.equals("0")) {
            String address_no = BaseUtils.getJsonData("address_id", update_address.getAddress_no());
            str = new String[]{address_no, isDefault, addressArea, addressDetail, recvTel, recvName, type_item, user_id};
            return str;
        } else {
            str = new String[]{isDefault, addressArea, addressDetail, recvTel, recvName, type_item, user_id};
            return str;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    public void onUpdateSuccessed() {
        if (type.equals("0")) {
            UIUtils.showToast("添加成功!");
        } else {
            UIUtils.showToast("地址修改成功!");
        }
        finish();
    }


}
