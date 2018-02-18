package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ToBeEvaluatedBean {
    private String image;
    private String title;
    private String refund_price;

    public ToBeEvaluatedBean(String image, String title, String refund_price) {
        this.image = image;
        this.title = title;
        this.refund_price = refund_price;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getRefund_price() {
        return refund_price;
    }
}
