package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.xialan.tastefresh.base.BaseHttpUtil;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.UpdatePsdContract;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UpdatePsdPresenter extends BasePresenter implements UpdatePsdContract.Presenter {
    private final UpdatePsdContract.View mView;

    public UpdatePsdPresenter(UpdatePsdContract.View view) {
        this.mView=view;
    }

    @Override
    public void getDataFromNet(String tag, String url, String key, String[] body) {
        BaseHttpUtil.sendHttp(url, key, body, CommonBean.class, new BaseHttpUtil.OnRequestSuccessedListener<CommonBean>() {
            @Override
            public void onSuccessed(CommonBean commonBean) {
                Log.e("miao",commonBean.toString());
                if (ParseUtils.checkdata(commonBean.getCode())){
                    mView.onUpdatePsdSuccessed();
                }else{
                    UIUtils.showToast(ParseUtils.showErrMsg(commonBean.getErr_msg()));
                }
            }
        });




    }
}
