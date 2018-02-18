package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.AddressBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface AddressContract {
    interface Model {
    }

    interface View {
        void getDataSuccessed(List<AddressBean.DataBean> dataBeans);
        void deleteAddressSuccessed();
    }

    interface Presenter extends BaseRequestInterTwoParama{



    }
}
