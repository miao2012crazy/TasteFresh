package com.xialan.tastefresh.base;

/**
 * key 和body 两个参数的网络请求
 */
public interface BaseRequestInterTwoParama {
    void getDataFromNet(String tag,String url,String key,String[] body);
}
