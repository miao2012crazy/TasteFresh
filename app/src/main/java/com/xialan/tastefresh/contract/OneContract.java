package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.UserOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public interface OneContract {
    interface Model {
    }

    interface View {
        void onOneDataSuccess(List<UserOrderBean.DataBean> dataBean);
        void onCancelDataSuccess();

    }

    interface Presenter extends BaseRequestInterTwoParama {
    }
}
