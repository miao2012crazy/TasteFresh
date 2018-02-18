package com.xialan.tastefresh.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */

public class MainBean {


    /**
     * code : S
     * data : {"stamp":"1517760000","loop_list":[{"activity_img":"http://changxin.xialan.com/Storage/activity/20180129_01/img_activity_01.png","activity_link":"img_activity_01.png"}],"product_list_week":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565156/img_main.png","product_id":"1923565156","product_name":"测试商品","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565157/img_main.png","product_id":"1923565157","product_name":"测试商品01","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565158/img_main.png","product_id":"1923565158","product_name":"测试商品02","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565161/img_main.png","product_id":"1923565161","product_name":"测试商品05","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565162/img_main.png","product_id":"1923565162","product_name":"本周商品06","recommend_season":"推荐理由 推荐理由","cash_state":"100"}],"product_next_week":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565159/img_next_week.png","product_id":"1923565159"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565160/img_next_week.png","product_id":"1923565160"}]}
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

    public static class DataBean {
        /**
         * stamp : 1517760000
         * loop_list : [{"activity_img":"http://changxin.xialan.com/Storage/activity/20180129_01/img_activity_01.png","activity_link":"img_activity_01.png"}]
         * product_list_week : [{"img_url":"http://changxin.xialan.com/Storage/product/1923565156/img_main.png","product_id":"1923565156","product_name":"测试商品","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565157/img_main.png","product_id":"1923565157","product_name":"测试商品01","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565158/img_main.png","product_id":"1923565158","product_name":"测试商品02","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565161/img_main.png","product_id":"1923565161","product_name":"测试商品05","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565162/img_main.png","product_id":"1923565162","product_name":"本周商品06","recommend_season":"推荐理由 推荐理由","cash_state":"100"}]
         * product_next_week : [{"img_url":"http://changxin.xialan.com/Storage/product/1923565159/img_next_week.png","product_id":"1923565159"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565160/img_next_week.png","product_id":"1923565160"}]
         */

        private String stamp;
        private List<LoopListBean> loop_list;
        private List<ProductListWeekBean> product_list_week;
        private List<ProductNextWeekBean> product_next_week;

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public List<LoopListBean> getLoop_list() {
            return loop_list;
        }

        public void setLoop_list(List<LoopListBean> loop_list) {
            this.loop_list = loop_list;
        }

        public List<ProductListWeekBean> getProduct_list_week() {
            return product_list_week;
        }

        public void setProduct_list_week(List<ProductListWeekBean> product_list_week) {
            this.product_list_week = product_list_week;
        }

        public List<ProductNextWeekBean> getProduct_next_week() {
            return product_next_week;
        }

        public void setProduct_next_week(List<ProductNextWeekBean> product_next_week) {
            this.product_next_week = product_next_week;
        }

        public static class LoopListBean {
            /**
             * activity_img : http://changxin.xialan.com/Storage/activity/20180129_01/img_activity_01.png
             * activity_link : img_activity_01.png
             */

            private String activity_img;
            private String activity_link;

            public String getActivity_img() {
                return activity_img;
            }

            public void setActivity_img(String activity_img) {
                this.activity_img = activity_img;
            }

            public String getActivity_link() {
                return activity_link;
            }

            public void setActivity_link(String activity_link) {
                this.activity_link = activity_link;
            }
        }

        public static class ProductListWeekBean {
            /**
             * img_url : http://changxin.xialan.com/Storage/product/1923565156/img_main.png
             * product_id : 1923565156
             * product_name : 测试商品
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

        public static class ProductNextWeekBean {
            /**
             * img_url : http://changxin.xialan.com/Storage/product/1923565159/img_next_week.png
             * product_id : 1923565159
             */

            private String img_url;
            private String product_id;

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
        }
    }
}
