package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/18.
 */

public class PecialUserBean {
    private String nick_name;
    private String user_head_img;
    private  String user_tag;

    public PecialUserBean(String nick_name, String user_head_img, String user_tag) {
        this.nick_name = nick_name;
        this.user_head_img = user_head_img;
        this.user_tag = user_tag;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getUser_head_img() {
        return user_head_img;
    }

    public String getUser_tag() {
        return user_tag;
    }

}
