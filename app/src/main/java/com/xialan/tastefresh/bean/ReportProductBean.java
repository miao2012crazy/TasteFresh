package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ReportProductBean {
    private String image_url;
    private String product_name;
    private  String comment_num;

    public ReportProductBean(String image_url, String product_name, String comment_num) {
        this.image_url = image_url;
        this.product_name = product_name;
        this.comment_num = comment_num;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getComment_num() {
        return comment_num;
    }
}
