package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/18.
 */

public class ProductNextBean {
    private String product_img;
    private String title;
    private String maxNum;

    public ProductNextBean(String product_img, String title, String maxNum) {
        this.product_img = product_img;
        this.title = title;
        this.maxNum = maxNum;
    }

    public String getProduct_img() {
        return product_img;
    }

    public String getTitle() {
        return title;
    }

    public String getMaxNum() {
        return maxNum;
    }
}
