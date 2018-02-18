package com.xialan.tastefresh.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.ToBeEvaluatedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ToBeEvaluatedAdapter extends BaseQuickAdapter<ToBeEvaluatedBean,BaseViewHolder>{

    public ToBeEvaluatedAdapter(List<ToBeEvaluatedBean> data) {
        super(R.layout.layout_tobe_evaluated, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ToBeEvaluatedBean toBeEvaluatedBean) {
            baseViewHolder.setImageResource(R.id.iv_product_report_img,R.mipmap.detail_example)
                    .setText(R.id.tv_title,toBeEvaluatedBean.getTitle())
                    .setText(R.id.tv_report_tag,"填写问卷和试用报告后可退押金"+toBeEvaluatedBean.getRefund_price()+"元")
            .addOnClickListener(R.id.btn_commit_report);
    }
}
