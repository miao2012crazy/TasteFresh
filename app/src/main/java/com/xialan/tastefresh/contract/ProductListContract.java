package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.MultipleItemForQuarter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface ProductListContract {
    interface Model {
    }

    interface View {
        void getDataSuccessed(List<MultipleItemForQuarter> multipleItemForQuarters);
    }

    interface Presenter extends BaseRequestInterTwoParama{
    }
}
