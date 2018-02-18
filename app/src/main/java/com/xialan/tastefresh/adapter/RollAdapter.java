package com.xialan.tastefresh.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.MainBean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */

public class RollAdapter extends StaticPagerAdapter {
    private final List<MainBean.DataBean.LoopListBean> list;

    public RollAdapter(List<MainBean.DataBean.LoopListBean> mlist) {
        this.list = mlist;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        ImageLoader.getInstance().displayImage(list.get(position).getActivity_img(), view);
        view.setImageResource(R.drawable.example_01);
        view.setTag(list.get(position).getActivity_link());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
