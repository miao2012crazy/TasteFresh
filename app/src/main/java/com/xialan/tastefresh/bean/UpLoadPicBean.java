package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class UpLoadPicBean {
    private String url;
    private String tag;

    public UpLoadPicBean(String url, String tag) {
        this.url = url;
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public String getTag() {
        return tag;
    }
}
