package com.xialan.tastefresh.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.ProductDetailBean;
import com.xialan.tastefresh.bean.ProductNextBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class ProductnextAdapter extends BaseQuickAdapter<ProductDetailBean.DataBean.ProductNextWeekBean,BaseViewHolder>{
    public ProductnextAdapter(List<ProductDetailBean.DataBean.ProductNextWeekBean> productNextBeanList) {
        super(R.layout.layout_next_product,productNextBeanList);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProductDetailBean.DataBean.ProductNextWeekBean productNextWeekBean) {
        baseViewHolder.setImageResource(R.id.iv_next_product_img,R.mipmap.nextproduct)
                .setText(R.id.tv_product_title,productNextWeekBean.getProduct_name())
                .setText(R.id.tv_next_num,"限量："+productNextWeekBean.getProduct_total_num()+"份");
    }
}
