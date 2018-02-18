package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.UserOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */

public interface FourContract {
    interface Model {
    }

    interface View {
        void onFourDataSuccess(List<UserOrderBean.DataBean> dataBean);
    }

    interface Presenter extends BaseRequestInterTwoParama{
    }
}
