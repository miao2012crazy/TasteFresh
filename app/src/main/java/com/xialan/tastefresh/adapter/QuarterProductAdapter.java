package com.xialan.tastefresh.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.QueryIndexBean;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class QuarterProductAdapter extends BaseQuickAdapter<QueryIndexBean.DataBean.DateListBean,BaseViewHolder>{
    public QuarterProductAdapter(List<QueryIndexBean.DataBean.DateListBean> data) {
        super(R.layout.quarter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, QueryIndexBean.DataBean.DateListBean dateListBean) {
//        ImageView view = (ImageView)baseViewHolder.getView(R.id.iv_image);
//        ImageLoaderManager.getImageLoader().displayImage(dateListBean.getImg_url(),view);
        baseViewHolder.setText(R.id.tv_text,dateListBean.getMonth()+"月 >>")
        .setImageResource(R.id.iv_image,R.mipmap.detail_example);
    }
}
