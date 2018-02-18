package com.xialan.tastefresh.contract;

import com.xialan.tastefresh.base.BaseView;
import com.xialan.tastefresh.bean.UpLoadPicBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public interface CommitReportExperienceContract {
    interface Model {
    }

    interface View extends BaseView{

        void upLodeSuccessed();
    }

    interface Presenter {
        void upLodeReport(String userId, String report_id, String value,  List<UpLoadPicBean> mList);
    }
}
