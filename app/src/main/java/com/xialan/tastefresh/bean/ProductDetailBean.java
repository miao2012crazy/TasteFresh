package com.xialan.tastefresh.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public class ProductDetailBean implements Serializable{


    /**
     * code : S
     * data : {"product_id":"1923565156","product_name":"测试商品","product_desc":"推荐理由 推荐理由","product_total_num":"1000","product_buy_num":"0","product_eval_price":"200","product_orign_price":"100","img_url":"http://changxin.xialan.com/Storage/product/1923565156/img_main.png","special_user":[{"user_head_img":"http://changxin.xialan.com/Storage/user/18222703922_20180129.png","user_tag":"这个人比较吊!真的!!!","user_nick_name":"喵喵喵","user_id":"18222703922"}],"product_next_week":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565159/img_next_week.png","product_total_num":"1000","product_name":"测试商品03","product_id":"1923565159"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565160/img_next_week.png","product_total_num":"1000","product_name":"测试商品04","product_id":"1923565160"}]}
     */

    private String code;
    private DataBean data;
    private String err_msg;

    public String getErr_msg() {
        return err_msg;
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

    public static class DataBean implements Serializable {
        /**
         * product_id : 1923565156
         * product_name : 测试商品
         * product_desc : 推荐理由 推荐理由
         * product_total_num : 1000
         * product_buy_num : 0
         * product_eval_price : 200
         * product_orign_price : 100
         * img_url : http://changxin.xialan.com/Storage/product/1923565156/img_main.png
         * special_user : [{"user_head_img":"http://changxin.xialan.com/Storage/user/18222703922_20180129.png","user_tag":"这个人比较吊!真的!!!","user_nick_name":"喵喵喵","user_id":"18222703922"}]
         * product_next_week : [{"img_url":"http://changxin.xialan.com/Storage/product/1923565159/img_next_week.png","product_total_num":"1000","product_name":"测试商品03","product_id":"1923565159"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565160/img_next_week.png","product_total_num":"1000","product_name":"测试商品04","product_id":"1923565160"}]
         */
        private String stamp;
        private String product_id;
        private String product_name;
        private String product_desc;
        private String product_total_num;
        private String product_buy_num;
        private String product_eval_price;
        private String product_orign_price;
        private String img_url;
        private List<SpecialUserBean> special_user;
        private List<ProductNextWeekBean> product_next_week;

        public String getStamp() {
            return stamp;
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

        public String getProduct_desc() {
            return product_desc;
        }

        public void setProduct_desc(String product_desc) {
            this.product_desc = product_desc;
        }

        public String getProduct_total_num() {
            return product_total_num;
        }

        public void setProduct_total_num(String product_total_num) {
            this.product_total_num = product_total_num;
        }

        public String getProduct_buy_num() {
            return product_buy_num;
        }

        public void setProduct_buy_num(String product_buy_num) {
            this.product_buy_num = product_buy_num;
        }

        public String getProduct_eval_price() {
            return product_eval_price;
        }

        public void setProduct_eval_price(String product_eval_price) {
            this.product_eval_price = product_eval_price;
        }

        public String getProduct_orign_price() {
            return product_orign_price;
        }

        public void setProduct_orign_price(String product_orign_price) {
            this.product_orign_price = product_orign_price;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public List<SpecialUserBean> getSpecial_user() {
            return special_user;
        }

        public void setSpecial_user(List<SpecialUserBean> special_user) {
            this.special_user = special_user;
        }

        public List<ProductNextWeekBean> getProduct_next_week() {
            return product_next_week;
        }

        public void setProduct_next_week(List<ProductNextWeekBean> product_next_week) {
            this.product_next_week = product_next_week;
        }

        public static class SpecialUserBean implements Serializable{
            /**
             * user_head_img : http://changxin.xialan.com/Storage/user/18222703922_20180129.png
             * user_tag : 这个人比较吊!真的!!!
             * user_nick_name : 喵喵喵
             * user_id : 18222703922
             */

            private String user_head_img;
            private String user_tag;
            private String user_nick_name;
            private String user_id;

            public String getUser_head_img() {
                return user_head_img;
            }

            public void setUser_head_img(String user_head_img) {
                this.user_head_img = user_head_img;
            }

            public String getUser_tag() {
                return user_tag;
            }

            public void setUser_tag(String user_tag) {
                this.user_tag = user_tag;
            }

            public String getUser_nick_name() {
                return user_nick_name;
            }

            public void setUser_nick_name(String user_nick_name) {
                this.user_nick_name = user_nick_name;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }

        public static class ProductNextWeekBean implements Serializable{
            /**
             * img_url : http://changxin.xialan.com/Storage/product/1923565159/img_next_week.png
             * product_total_num : 1000
             * product_name : 测试商品03
             * product_id : 1923565159
             */

            private String img_url;
            private String product_total_num;
            private String product_name;
            private String product_id;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getProduct_total_num() {
                return product_total_num;
            }

            public void setProduct_total_num(String product_total_num) {
                this.product_total_num = product_total_num;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
        }
    }
}
