package com.xialan.tastefresh.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * recycler view  框架 多布局
 * Created by Administrator on 2018/1/11.
 */
public class MultipleItem implements MultiItemEntity {
    public static final int MAIN_01 = 1;
    public static final int MAIN_02 = 2;
    public static final int MAIN_03 = 3;
    public static final int MAIN_04 = 4;
    private int mItemType;
    private DataBean_1 dataBean1;
    private HomeBeanCon dataBean;
    private List<MainBean.DataBean.ProductListWeekBean> dataBean2;
    private DataBean_4 dataBean4;

    public MultipleItem(int mItemType, HomeBeanCon dataBean) {
        this.mItemType = mItemType;
        this.dataBean = dataBean;
    }

    public HomeBeanCon getDataBean() {
        return dataBean;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public DataBean_1 getDataBean1() {
        return dataBean1;
    }


    public DataBean_4 getDataBean4() {
        return dataBean4;
    }

    public static class DataBean_1 {
        private String type_title;
        private String type_time;

        public DataBean_1(String type_title, String type_time) {
            this.type_title = type_title;
            this.type_time = type_time;
        }

        public String getType_title() {
            return type_title;
        }

        public String getType_time() {
            return type_time;
        }
    }

   public static class DataBean_4 {
        private List<LoopListBean> loop_list;

        public DataBean_4(List<LoopListBean> loop_list) {
            this.loop_list = loop_list;
        }

        public List<LoopListBean> getLoop_list() {
            return loop_list;
        }

        public static class LoopListBean {
            /**
             * url : http://61.181.111.115:80/Storage/mall/appbanner/MAIN_BANNER01.jpg
             * product_id : 35
             */
            private String activity_img;
            private String activity_link;

            public String getActivity_img() {
                return activity_img;
            }

            public String getActivity_link() {
                return activity_link;
            }
        }
    }


    //-=====================下面是构造方法=================
    /**
     * 轮播图
     *
     * @param mItemType
     * @param dataBean4
     */
    public MultipleItem(int mItemType, DataBean_4 dataBean4) {
        this.mItemType = mItemType;
        this.dataBean4 = dataBean4;
    }



    /**
     * 标题
     *
     * @param mItemType
     * @param dataBean1
     */
    public MultipleItem(int mItemType, DataBean_1 dataBean1) {
        this.mItemType = mItemType;
        this.dataBean1 = dataBean1;
    }

    public MultipleItem(int itemType) {
        this.mItemType = itemType;
    }


}
