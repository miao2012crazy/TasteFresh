package com.xialan.tastefresh.base;

/**
 * Created by Administrator on 2017/11/10.
 */

public interface BaseView {

    void showCustomProgressBar(String msg);

    void hideCustomProgressBar();

    void getDataFailed(String err_msg);
}
