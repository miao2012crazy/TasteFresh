package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/31.
 */

public class WeekTimeBean {
    /**
     * img_url : http://changxin.xialan.com/Storage/product/1923565168/img_main.png
     * product_id : 1923565168
     * product_name : 季度商品10
     * recommend_season : 推荐理由 推荐理由
     * cash_state : 100
     */
    private String img_url;
    private String product_id;
    private String product_name;
    private String recommend_season;
    private String cash_state;

    public WeekTimeBean(String img_url, String product_id, String product_name, String recommend_season, String cash_state) {
        this.img_url = img_url;
        this.product_id = product_id;
        this.product_name = product_name;
        this.recommend_season = recommend_season;
        this.cash_state = cash_state;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRecommend_season() {
        return recommend_season;
    }

    public void setRecommend_season(String recommend_season) {
        this.recommend_season = recommend_season;
    }

    public String getCash_state() {
        return cash_state;
    }

    public void setCash_state(String cash_state) {
        this.cash_state = cash_state;
    }

    @Override
    public String toString() {
        return "WeekTimeBean{" +
                "img_url='" + img_url + '\'' +
                ", product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", recommend_season='" + recommend_season + '\'' +
                ", cash_state='" + cash_state + '\'' +
                '}';
    }
}
