package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/30.
 */

public class UserInfoBean {

    /**
     * code : S
     * data : {"user_id":"admin1","user_nick_name":"喵喵喵","wechat_union_id":"","mobile":"0","gender":"1","real_name":"测试真","user_head_img":"http://changxin.xialan.com/Storage/user/18222703922_20180129.png"}
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
         * user_id : admin1
         * user_nick_name : 喵喵喵
         * wechat_union_id :
         * mobile : 0
         * gender : 1
         * real_name : 测试真
         * user_head_img : http://changxin.xialan.com/Storage/user/18222703922_20180129.png
         */

        private String user_id;
        private String user_nick_name;
        private String wechat_union_id;
        private String mobile;
        private String gender;
        private String real_name;
        private String user_head_img;
        private String date_birth;

        public String getDate_birth() {
            return date_birth;
        }

        public void setDate_birth(String date_birth) {
            this.date_birth = date_birth;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_nick_name() {
            return user_nick_name;
        }

        public void setUser_nick_name(String user_nick_name) {
            this.user_nick_name = user_nick_name;
        }

        public String getWechat_union_id() {
            return wechat_union_id;
        }

        public void setWechat_union_id(String wechat_union_id) {
            this.wechat_union_id = wechat_union_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getUser_head_img() {
            return user_head_img;
        }

        public void setUser_head_img(String user_head_img) {
            this.user_head_img = user_head_img;
        }
    }
}
