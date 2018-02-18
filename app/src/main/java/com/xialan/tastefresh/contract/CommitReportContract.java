package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseView;
import com.xialan.tastefresh.bean.UpLoadPicBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/21.
 */

public interface CommitReportContract {
    interface Model {
    }

    interface View extends BaseView{
        void  upLodeSuccessed(String report_id);

    }

    interface Presenter {
        void upLodeReport(String userId, String order_id, String value, String score, List<UpLoadPicBean> mList);
    }
}
