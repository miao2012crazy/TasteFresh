package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/29.
 */

public class CommonBean {
    private String code;
    private String msg;
    private String err_msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getErr_msg() {
        return err_msg;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", err_msg='" + err_msg + '\'' +
                '}';
    }
}
