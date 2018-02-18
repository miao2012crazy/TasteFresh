package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterface;
import com.xialan.tastefresh.bean.QueryIndexBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface QuarterProductContract {
    interface Model {
    }

    interface View {
        void getDataSuccessed(List<QueryIndexBean.DataBean.DateListBean> dataBeans);
    }

    interface Presenter extends BaseRequestInterface{

    }
}
