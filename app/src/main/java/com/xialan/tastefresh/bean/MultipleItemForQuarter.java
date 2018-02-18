package com.xialan.tastefresh.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/1/31.
 */

public class MultipleItemForQuarter implements MultiItemEntity {
    public static final int MAIN_01 = 1;
    public static final int MAIN_02 = 2;
    private  Datebean mDatebean;
    private int mItemType;
    private  WeekTimeBean weekTimeBean;

    @Override
    public int getItemType() {
        return mItemType;
    }

    public MultipleItemForQuarter(int mItemType, WeekTimeBean weekTimeBean) {
        this.mItemType = mItemType;
        this.weekTimeBean = weekTimeBean;
    }

    public MultipleItemForQuarter(int mItemType,Datebean datebean) {
        this.mItemType = mItemType;
        this.mDatebean = datebean;
    }
    /**
     * 获取日期
     * @return
     */
    public  Datebean getmDatebean() {
        return mDatebean;
    }

    public  WeekTimeBean getWeekTimeBean() {
        return weekTimeBean;
    }


    @Override
    public String toString() {
        return "MultipleItemForQuarter{" +
                "mItemType=" + mItemType +
                '}';
    }


}

