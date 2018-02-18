package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.xialan.tastefresh.base.BaseHttpUtil;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.OneContract;

/**
 * Created by Administrator on 2018/2/3.
 */

public class OnePresenter extends BasePresenter implements OneContract.Presenter {
    private final OneContract.View mView;

    public OnePresenter(OneContract.View view) {
        this.mView = view;
    }

    @Override
    public void getDataFromNet(String tag, String url, String key, String[] body) {
        switch (tag) {
            case "search_order":
                BaseHttpUtil.sendHttp(url, key, body, UserOrderBean.class, new BaseHttpUtil.OnRequestSuccessedListener<UserOrderBean>() {
                    @Override
                    public void onSuccessed(UserOrderBean userOrderBean) {
                        Log.e("miao", userOrderBean.toString());
                        if (ParseUtils.checkdata(userOrderBean.getCode())) {
                            mView.onOneDataSuccess(userOrderBean.getData());
                        }
                    }
                });
                break;
            case "cancel_order":
                BaseHttpUtil.sendHttp(url, key, body, CommonBean.class, new BaseHttpUtil.OnRequestSuccessedListener<CommonBean>() {
                    @Override
                    public void onSuccessed(CommonBean commonBean) {
                        Log.e("miao", commonBean.toString());
                        if (ParseUtils.checkdata(commonBean.getCode())) {
                                mView.onCancelDataSuccess();
                        }else{
                            UIUtils.showToast(ParseUtils.showErrMsg(commonBean.getErr_msg()));
                        }
                    }
                });
                break;


        }



    }
}
