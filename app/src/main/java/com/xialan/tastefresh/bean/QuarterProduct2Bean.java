package com.xialan.tastefresh.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public class QuarterProduct2Bean {

    /**
     * code : S
     * data : [{"product_periods":"1","week_time":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565168/img_main.png","product_id":"1923565168","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565169/img_main.png","product_id":"1923565169","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565170/img_main.png","product_id":"1923565170","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565171/img_main.png","product_id":"1923565171","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"}]},{"product_periods":"2","week_time":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565165/img_main.png","product_id":"1923565165","product_name":"季度商品09","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565166/img_main.png","product_id":"1923565166","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565167/img_main.png","product_id":"1923565167","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"}]},{"product_periods":"3","week_time":[{"img_url":"http://changxin.xialan.com/Storage/product/1923565164/img_main.png","product_id":"1923565164","product_name":"季度商品08","recommend_season":"推荐理由 推荐理由","cash_state":"100"}]}]
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
         * product_periods : 1
         * week_time : [{"img_url":"http://changxin.xialan.com/Storage/product/1923565168/img_main.png","product_id":"1923565168","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565169/img_main.png","product_id":"1923565169","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565170/img_main.png","product_id":"1923565170","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"},{"img_url":"http://changxin.xialan.com/Storage/product/1923565171/img_main.png","product_id":"1923565171","product_name":"季度商品10","recommend_season":"推荐理由 推荐理由","cash_state":"100"}]
         */
        private String periods_start;
        private String product_periods;
        private List<WeekTimeBean> week_time;

        public String getPeriods_start() {
            return periods_start;
        }

        public String getProduct_periods() {
            return product_periods;
        }

        public void setProduct_periods(String product_periods) {
            this.product_periods = product_periods;
        }

        public List<WeekTimeBean> getWeek_time() {
            return week_time;
        }

        public void setWeek_time(List<WeekTimeBean> week_time) {
            this.week_time = week_time;
        }

        public static class WeekTimeBean {
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
    }
}
