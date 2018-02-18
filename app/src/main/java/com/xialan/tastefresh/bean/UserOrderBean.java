package com.xialan.tastefresh.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public class UserOrderBean {

    /**
     * code : S
     * data : [{"bad_reason":"","order_state":"0","order_id":"192356515620180203110311","img_url":"http://changxin.xialan.com/Storage/product/1923565156/img_main.png","product_id":"1923565156","product_name":"测试商品","private_deposit":"200","cash_state":"100","pay_date":""},{"bad_reason":"","order_state":"0","order_id":"192356515620180203110319","img_url":"http://changxin.xialan.com/Storage/product/1923565156/img_main.png","product_id":"1923565156","product_name":"测试商品","private_deposit":"200","cash_state":"100","pay_date":""}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bad_reason :
         * order_state : 0
         * order_id : 192356515620180203110311
         * img_url : http://changxin.xialan.com/Storage/product/1923565156/img_main.png
         * product_id : 1923565156
         * product_name : 测试商品
         * private_deposit : 200
         * cash_state : 100
         * pay_date :
         */
        private String commit_date;
        private String bad_reason;
        private String order_state;
        private String order_id;
        private String img_url;
        private String product_id;
        private String product_name;
        private String private_deposit;
        private String cash_state;
        private String pay_date;

        public String getCommit_date() {
            return commit_date;
        }

        public void setCommit_date(String commit_date) {
            this.commit_date = commit_date;
        }

        public String getBad_reason() {
            return bad_reason;
        }

        public void setBad_reason(String bad_reason) {
            this.bad_reason = bad_reason;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getPrivate_deposit() {
            return private_deposit;
        }

        public void setPrivate_deposit(String private_deposit) {
            this.private_deposit = private_deposit;
        }

        public String getCash_state() {
            return cash_state;
        }

        public void setCash_state(String cash_state) {
            this.cash_state = cash_state;
        }

        public String getPay_date() {
            return pay_date;
        }

        public void setPay_date(String pay_date) {
            this.pay_date = pay_date;
        }
    }
}
