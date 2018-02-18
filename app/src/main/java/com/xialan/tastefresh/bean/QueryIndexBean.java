package com.xialan.tastefresh.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public class QueryIndexBean {

    /**
     * code : S
     * data : {"date_list":[{"month":"2018年1","img_url":"","start_time":"201801","end_time":"201803"},{"month":"2017年12","img_url":"","start_time":"201749","end_time":"201753"},{"month":"2017年11","img_url":"","start_time":"201745","end_time":"201749"}]}
     */

    private String code;
    private DataBean data;

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
        private List<DateListBean> date_list;

        public List<DateListBean> getDate_list() {
            return date_list;
        }

        public void setDate_list(List<DateListBean> date_list) {
            this.date_list = date_list;
        }

        public static class DateListBean {
            /**
             * month : 2018年1
             * img_url :
             * start_time : 201801
             * end_time : 201803
             */

            private String month;
            private String img_url;
            private String start_time;
            private String end_time;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }
        }
    }
}
