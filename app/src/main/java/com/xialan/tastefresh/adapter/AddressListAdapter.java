package com.xialan.tastefresh.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.AddressBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class AddressListAdapter extends BaseQuickAdapter<AddressBean.DataBean, BaseViewHolder> {

    public AddressListAdapter(List<AddressBean.DataBean> data) {
        super(R.layout.address_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AddressBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_address_name, dataBean.getRecv_name())
                .setText(R.id.tv_address_tel, dataBean.getRecv_tel())
                .setText(R.id.tv_address_detail, dataBean.getAddress_detail())
                .setText(R.id.tv_address_default, dataBean.getIs_default().equals("1")?"默认地址":"")
                .addOnClickListener(R.id.tv_update)
                .addOnClickListener(R.id.tv_delete);
    }

}
