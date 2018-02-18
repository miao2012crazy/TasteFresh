package com.xialan.tastefresh.adapter;

import android.text.Html;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.LastWeekProductBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class LastWeekProductAdapter extends BaseQuickAdapter<LastWeekProductBean.DataBean.ProductListWeekBean,BaseViewHolder>{

    public LastWeekProductAdapter(List<LastWeekProductBean.DataBean.ProductListWeekBean> data) {
        super(R.layout.layout_mian_02, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LastWeekProductBean.DataBean.ProductListWeekBean productListWeekBean) {
        baseViewHolder
                .setText(R.id.tv_product_state, Html.fromHtml("<font color='#FF0000'>【押金全退】</font>"+productListWeekBean.getProduct_name()))
                .setText(R.id.tv_reason, productListWeekBean.getRecommend_season());
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_product_image);
        ImageLoader.getInstance().displayImage(productListWeekBean.getImg_url(),imageView);



    }

}
