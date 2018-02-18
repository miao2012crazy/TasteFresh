package com.xialan.tastefresh.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class LastWeekProductBean {


    /**
     * code : S
     * data : {"product_list_week":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565163/img_main.png","product_id":"1923565163","product_name":"上周商品07","recommend_season":"推荐理由 推荐理由","cash_state":"100"}]}
     */

    private String code;
    private DataBean data;
    private String err_msg;

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ProductListWeekBean> product_list_week;

        public List<ProductListWeekBean> getProduct_list_week() {
            return product_list_week;
        }

        public void setProduct_list_week(List<ProductListWeekBean> product_list_week) {
            this.product_list_week = product_list_week;
        }

        public static class ProductListWeekBean {
            /**
             * img_url : http://changxin.xialan.com/Storage/product/1923565163/img_main.png
             * product_id : 1923565163
             * product_name : 上周商品07
             * recommend_season : 推荐理由 推荐理由
             * cash_state : 100
             */

            private String img_url;
            private String product_id;
            private String product_name;
            private String recommend_season;
            private String cash_state;

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
        }
    }
}
