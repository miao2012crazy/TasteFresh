package com.xialan.tastefresh.adapter;

import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.tastefresh.R;
import com.xialan.tastefresh.bean.MainBean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;
import com.xialan.tastefresh.commonutil.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MainAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    public MainAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.MAIN_01, R.layout.layout_mian_01);
        addItemType(MultipleItem.MAIN_02, R.layout.layout_mian_02);
        addItemType(MultipleItem.MAIN_03, R.layout.layout_mian_03);
        addItemType(MultipleItem.MAIN_04, R.layout.layout_mian_04);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultipleItem multipleItem) {
        switch (baseViewHolder.getItemViewType()) {
            case MultipleItem.MAIN_01:
                //首显示标题
                MultipleItem.DataBean_1 dataBean1 = multipleItem.getDataBean1();
                String type_time = dataBean1.getType_time();
                String timeForSurplus = UIUtils.getTimeForSurplus(type_time);
                String[] split = timeForSurplus.split(":");
                baseViewHolder.setText(R.id.tv_main_item_title, multipleItem.getDataBean1().getType_title())
                        .setText(R.id.tv_hour, split[0])
                        .setText(R.id.tv_minute, split[1])
                        .setText(R.id.tv_second, split[2]);
                break;
            case MultipleItem.MAIN_02:
                baseViewHolder
                        .setText(R.id.tv_product_state, Html.fromHtml("<font color='#FF0000'>【押金全退】</font>这里是商品标题"))
                        .setText(R.id.tv_reason, "11111111这里是推荐理由理由理由这里是推荐理由理由理由这里是推荐理由理由理由");
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_product_image);
                ImageLoader.getInstance().displayImage(multipleItem.getDataBean().getImg_url(),imageView);

                break;
            case MultipleItem.MAIN_03:
                //下周商品
                ImageView imageView1 = (ImageView) baseViewHolder.getView(R.id.iv_product);
                ImageLoader.getInstance().displayImage(multipleItem.getDataBean().getImg_url(),imageView1);
                break;
            case MultipleItem.MAIN_04:
                //轮播图
                List<MainBean.DataBean.LoopListBean> loop_list = multipleItem.getDataBean().getLoop_list();
                RollPagerView rollViewPager = baseViewHolder.getView(R.id.roll_view_pager);
                initloop(rollViewPager,loop_list);
                break;
        }
    }


    /**
     * 初始化轮播图
     *
     * @param loop_list
     */
    private void initloop(RollPagerView rollViewPager,List<MainBean.DataBean.LoopListBean> loop_list) {
        //设置播放时间间隔
        rollViewPager.setPlayDelay(3000);
        //设置透明度
        rollViewPager.setAnimationDurtion(500);
        RollAdapter  adapter = new RollAdapter(loop_list);
        rollViewPager.setAdapter(adapter);
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
//        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));
        rollViewPager.setHintView(new TextHintView(UIUtils.getContext()));
        //mRollViewPager.setHintView(null);
        rollViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }


}
