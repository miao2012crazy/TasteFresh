package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface UpdateAddressContract {
    interface Model {
    }

    interface View {
        void onUpdateSuccessed();
    }

    interface Presenter extends BaseRequestInterTwoParama{
    }
}
