package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseView;
import com.xialan.tastefresh.bean.UserInfoBean;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface LoginChildContract {
    interface Model {
    }

    interface View extends BaseView{
        void onLoginSuccessed(UserInfoBean userInfoBean);
        void onLoginFailed(String err_msg);
    }

    interface Presenter {
        void login(String key,String[] body);
    }




}
