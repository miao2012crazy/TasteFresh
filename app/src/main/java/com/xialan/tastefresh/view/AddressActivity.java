package com.xialan.tastefresh.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.adapter.AddressListAdapter;
import com.xialan.tastefresh.adapter.ProductListAdapter;
import com.xialan.tastefresh.applaction.MyApplaction;
import com.xialan.tastefresh.base.BaseActivity;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.AddressBean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.AddressContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.presenter.AddressPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class AddressActivity extends BaseActivity implements AddressContract.View {
    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_add_new_address)
    Button btnAddNewAddress;
    private AddressPresenter addressPresenter;
    private List<AddressBean.DataBean> mList = new ArrayList<>();
    private AddressListAdapter addressListAdapter;
    private boolean isSelectAddress=false;

    @Override
    protected int setlayoutID() {
        return R.layout.activity_address;
    }

    @Override
    protected void initData() {
        String confirm_orders = getIntent().getStringExtra("confirm_order");
        if (confirm_orders!=null&&confirm_orders.equals("1")){
            isSelectAddress=true;
        }
        rlRight.setVisibility(View.GONE);
        tvTitle.setText("地址管理");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressListAdapter = new AddressListAdapter(mList);
        recyclerView.setAdapter(addressListAdapter);
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemClick(adapter, view, position);
                if (!isSelectAddress){
                    return;
                }
                AddressBean.DataBean dataBean = mList.get(position);
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("address_select",dataBean);
                intent.putExtras(bundle);
                setResult(0,intent);
                finish();
            }

            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AddressBean.DataBean dataBean = mList.get(i);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        initDeleteDialog(dataBean);
                        break;
                    case R.id.tv_update:
                        bundle.clear();
                        bundle.putSerializable("update_address", dataBean);
                        startActivityWithParamas(UpdateAddressActivty.class, bundle);
                        break;
                }
            }
        });

    }

    /**
     * 自定义dialog
     *
     * @param dataBean
     */
    private void initDeleteDialog(final AddressBean.DataBean dataBean) {
        final Dialog dialog = new Dialog(this);
        View inflate = UIUtils.inflate(R.layout.layout_delete_address);
        Button btn_confirm = (Button) inflate.findViewById(R.id.btn_confirm);
        Button btn_cancle = (Button) inflate.findViewById(R.id.btn_cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = getUserId();
                if (userId.equals("")) {
                    return;
                }
                String user_id = BaseUtils.getJsonData("user_id", userId);
                String address_id = BaseUtils.getJsonData("address_id", dataBean.getAddress_no());
                String type = BaseUtils.getJsonData("type", "2");
                String[] str = {user_id, address_id, type};
                addressPresenter.getDataFromNet("delete", HttpUrl.ADDRESS_MANAGE, AESOperator.getRandomString(), str);
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        attributes.width = UIUtils.dip2px(300);
        attributes.height = UIUtils.dip2px(117);
        dialog.show();


    }

    @Override
    protected BasePresenter createPresenter() {
        addressPresenter = new AddressPresenter(this);
        return addressPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_left, R.id.btn_add_new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.btn_add_new_address:
                startActivity(UpdateAddressActivty.class);
                break;
        }
    }

    @Override
    public void getDataFailed(String err_msg) {

    }

    @Override
    public void getDataSuccessed(List<AddressBean.DataBean> dataBeans) {
        mList.clear();
        mList.addAll(dataBeans);
        addressListAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteAddressSuccessed() {
        UIUtils.showToast("地址删除成功");
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        String[] str = {BaseUtils.getJsonData("user_id",getUserId())};
        addressPresenter.getDataFromNet("query", HttpUrl.SEARCH_USER_ADDRESS, AESOperator.getRandomString(), str);
    }


}
