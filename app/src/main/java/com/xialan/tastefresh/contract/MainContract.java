package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.bean.MainBean;
import com.xialan.tastefresh.bean.MultipleItem;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface MainContract {
    interface Model {

    }

    interface View {
       void OnGetDataForMainSuccess( List<MultipleItem>  multipleItem);
       void onGetDataForMainFailed(String err_msg);
    }

    interface Presenter {
        void getDataForMain(String key);
        int getRefreshPosition();
        int getNextProductStartPosition();
        int getLoopActivityPosition();

    }

}
