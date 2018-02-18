package com.xialan.tastefresh.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2018/1/11.
 */

public class LeftBean {
    private String title;
    private Drawable drawable;

    public LeftBean(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public LeftBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
