package com.xialan.tastefresh.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.UserOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public class ThreeAdapter extends BaseQuickAdapter<UserOrderBean.DataBean,BaseViewHolder> {


    public ThreeAdapter(int layoutResId, List<UserOrderBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserOrderBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_product_name,dataBean.getProduct_name())
                .setText(R.id.tv_refund,"填写问卷和试用报告后可退押金"+dataBean.getPrivate_deposit()+"元");
        ImageView view = (ImageView) baseViewHolder.getView(R.id.iv_product_order);
        ImageLoader.getInstance().displayImage(dataBean.getImg_url(),view);

    }
}