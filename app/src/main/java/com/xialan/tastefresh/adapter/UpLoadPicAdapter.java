package com.xialan.tastefresh.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.UpLoadPicBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class UpLoadPicAdapter extends BaseQuickAdapter<UpLoadPicBean, BaseViewHolder> {
    public UpLoadPicAdapter(List<UpLoadPicBean> data) {
        super(R.layout.grid_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UpLoadPicBean upLoadPicBean) {
            switch (upLoadPicBean.getTag()) {
                case "1":
                    baseViewHolder.setImageResource(R.id.iv_special, R.mipmap.ic_camera)
                            .setVisible(R.id.fl_special, true)
                            .setText(R.id.tv_special,"传照片")
                            .setVisible(R.id.iv_delete, false)
                            .setVisible(R.id.pic_iv, false);
                    break;
                case "2":
                    baseViewHolder.setImageResource(R.id.iv_special, R.mipmap.ic_video)
                            .setVisible(R.id.fl_special, true)
                            .setText(R.id.tv_special,"传视频")
                            .setVisible(R.id.iv_delete, false)
                            .setVisible(R.id.pic_iv, false);
                    break;
                case "4":
                    ImageView ivBf = (ImageView) baseViewHolder.getView(R.id.iv_bf);
                    ivBf.setVisibility(View.VISIBLE);
                case "3":
                    baseViewHolder.addOnClickListener(R.id.iv_delete);
                    ImageView view = (ImageView) baseViewHolder.getView(R.id.pic_iv);
                    ImageLoader.getInstance().displayImage("file://"+upLoadPicBean.getUrl(),view);
                    break;
                default:
                    break;
            }
    }
}
