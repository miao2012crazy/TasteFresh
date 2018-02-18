package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.AddressBean;
import com.xialan.tastefresh.presenter.AddressPresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public interface ConfirmOrderContract {
    interface Model {
    }

    interface View {
        void getDataSuccessed(List<AddressBean.DataBean> dataBeans);
        void creatOrderSuccessed();
    }

    interface Presenter extends BaseRequestInterTwoParama{
    }
}
