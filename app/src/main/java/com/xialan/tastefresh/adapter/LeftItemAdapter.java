package com.xialan.tastefresh.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.LeftBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class LeftItemAdapter extends BaseQuickAdapter<LeftBean,BaseViewHolder>{

    public LeftItemAdapter(List<LeftBean> data) {
        super(R.layout.layout_left_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LeftBean leftBean) {
        baseViewHolder.setText(R.id.tv_item_title,leftBean.getTitle())
                .setImageDrawable(R.id.iv_item_icon,leftBean.getDrawable());

    }
}
