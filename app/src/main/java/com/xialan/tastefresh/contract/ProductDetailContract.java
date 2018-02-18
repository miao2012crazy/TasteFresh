package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseView;
import com.xialan.tastefresh.bean.ProductDetailBean;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface ProductDetailContract {
    interface Model {
    }

    interface View extends BaseView{
       void onGetDataSuccess(ProductDetailBean.DataBean dataBean);
        void onGetDataFailed(String err_msg);
    }

    interface Presenter {
        void getProductDetail(String key,String[] body);

    }
}
