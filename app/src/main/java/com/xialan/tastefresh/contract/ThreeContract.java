package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.UserOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public interface ThreeContract {
    interface Model {
    }

    interface View {
        void onThreeDataSuccess(List<UserOrderBean.DataBean> dataBeans);
    }

    interface Presenter extends BaseRequestInterTwoParama{
    }
}
