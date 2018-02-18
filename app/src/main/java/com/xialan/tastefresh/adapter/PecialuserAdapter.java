package com.xialan.tastefresh.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.PecialUserBean;
import com.xialan.tastefresh.bean.ProductDetailBean;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class PecialuserAdapter extends BaseQuickAdapter<ProductDetailBean.DataBean.SpecialUserBean, BaseViewHolder> {
    public PecialuserAdapter(List<ProductDetailBean.DataBean.SpecialUserBean> mListPecial) {
        super(R.layout.layout_pecial_user, mListPecial);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProductDetailBean.DataBean.SpecialUserBean specialUserBean) {
        baseViewHolder.setTag(R.id.ll_tag, specialUserBean.getUser_id())
                .setText(R.id.tv_nick_name, specialUserBean.getUser_nick_name())
                .setText(R.id.tv_user_tag, specialUserBean.getUser_tag());
        ImageView view = (ImageView) baseViewHolder.getView(R.id.iv_image_head);
        ImageLoaderManager.displayHeadIcon(specialUserBean.getUser_head_img(), view);
    }

}
