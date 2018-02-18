package com.xialan.tastefresh.view;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.ReportProductBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ReportlistAdapter extends BaseQuickAdapter<ReportProductBean,BaseViewHolder> {


    public ReportlistAdapter(List<ReportProductBean> data) {
        super(R.layout.report_product_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ReportProductBean reportProductBean) {
        baseViewHolder.setText(R.id.tv_report_product_name,reportProductBean.getProduct_name())
                .setText(R.id.tv_report_comment,reportProductBean.getComment_num());
    }
}
