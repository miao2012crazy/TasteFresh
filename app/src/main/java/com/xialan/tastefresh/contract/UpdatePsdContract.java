package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface UpdatePsdContract {
    interface Model {
    }

    interface View {
       void onUpdatePsdSuccessed();
    }

    interface Presenter extends BaseRequestInterTwoParama {
    }
}
