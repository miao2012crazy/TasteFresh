package com.xialan.tastefresh.adapter;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public class FourAdapter extends BaseQuickAdapter<UserOrderBean.DataBean, BaseViewHolder> {


    private ImageView iv_state;

    public FourAdapter(int layoutResId, List<UserOrderBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserOrderBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_product_name, dataBean.getProduct_name())
                .setText(R.id.tv_commit_date, "提交日期：" + dataBean.getCommit_date());
        ImageView view = (ImageView) baseViewHolder.getView(R.id.iv_product_order);
        iv_state = (ImageView) baseViewHolder.getView(R.id.iv_state);
        TextView tv_state = (TextView) baseViewHolder.getView(R.id.tv_order_state);
        TextView tv_refund = (TextView) baseViewHolder.getView(R.id.tv_refund);
        ImageLoader.getInstance().displayImage(dataBean.getImg_url(), view);
        switch (dataBean.getOrder_state()) {
            case "6":
                setState(tv_state, "审核中", R.mipmap.ic_shz);
                tv_refund.setText("正在审核测评报告是否符合标准。");
                break;
            case "7":
                setState(tv_state, "审核已通过", R.mipmap.ic_sh_tg);
                tv_refund.setText("审核通过！押金核对中，2-6个工作日退回。");
                break;
            case "8":
                setState(tv_state, "押金已退", R.mipmap.ic_sh);
                tv_refund.setText("退款已按照付款渠道原路返回，到账时间因支付方式有差异。" + Html.fromHtml("<font color='#FB4890'>查看退款到账时间说明</font>"));
                break;
            case "9":
                setState(tv_state, "审核失败", R.mipmap.ic_sh_bad);
                tv_refund.setText("测评报告不符合标准！" + Html.fromHtml("<font color='#FB4890'>查看未通过原因</font>"));
                break;
        }


    }

    private void setState(TextView textView, String name, int resId) {
        textView.setText(name);
        iv_state.setImageDrawable(UIUtils.getDrawable(resId));
    }

}
