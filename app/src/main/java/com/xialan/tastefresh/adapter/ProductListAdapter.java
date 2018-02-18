package com.xialan.tastefresh.adapter;

import android.text.Html;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.Datebean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.bean.MultipleItemForQuarter;
import com.xialan.tastefresh.bean.WeekTimeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ProductListAdapter extends BaseMultiItemQuickAdapter<MultipleItemForQuarter,BaseViewHolder> {
    public ProductListAdapter(List<MultipleItemForQuarter> data) {
        super(data);
        addItemType(MultipleItemForQuarter.MAIN_01, R.layout.layout_mulit_quarter);
        addItemType(MultipleItemForQuarter.MAIN_02, R.layout.layout_mian_02);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultipleItemForQuarter multipleItemForQuarter) {
        switch (baseViewHolder.getItemViewType()) {
            case MultipleItem.MAIN_01:
                //首显示标题
                Datebean datebean = multipleItemForQuarter.getmDatebean();
                baseViewHolder.setText(R.id.tv_main_item_title,"第"+datebean.getDate()+"期")
                        .setText(R.id.tv_time_start,datebean.getTime_start());
                break;
            case MultipleItem.MAIN_02:
                WeekTimeBean weekTimeBean = multipleItemForQuarter.getWeekTimeBean();
                baseViewHolder
                        .setText(R.id.tv_product_state, Html.fromHtml("<font color='#FF0000'>【押金全退】</font>")+weekTimeBean.getProduct_name())
                        .setText(R.id.tv_reason, weekTimeBean.getRecommend_season());
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_product_image);
                ImageLoader.getInstance().displayImage(weekTimeBean.getImg_url(),imageView);
                break;
        }



    }
}
