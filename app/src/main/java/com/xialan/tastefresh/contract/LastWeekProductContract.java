package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterface;
import com.xialan.tastefresh.bean.LastWeekProductBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface LastWeekProductContract {
    interface Model {

    }

    interface View {
        void getDataSuccess(List<LastWeekProductBean.DataBean.ProductListWeekBean> productListWeekBeans);
    }

    interface Presenter extends BaseRequestInterface{
    }
}
