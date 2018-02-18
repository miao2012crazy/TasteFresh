package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.xialan.tastefresh.base.BaseHttpUtil;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.UserOrderBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.contract.TwoContract;

/**
 * Created by Administrator on 2018/2/3.
 */

public class TwoPresenter  extends BasePresenter implements TwoContract.Presenter {
    private final TwoContract.View mView;

    public TwoPresenter(TwoContract.View view) {
        this.mView=view;
    }

    @Override
    public void getDataFromNet(String tag, String url, String key, String[] body) {
        BaseHttpUtil.sendHttp(url, key, body, UserOrderBean.class, new BaseHttpUtil.OnRequestSuccessedListener<UserOrderBean>() {
            @Override
            public void onSuccessed(UserOrderBean userOrderBean) {
                Log.e("miao",userOrderBean.toString());
                if (ParseUtils.checkdata(userOrderBean.getCode())){
                    mView.onTwoDataSuccess(userOrderBean.getData());
                }
            }
        });
    }
}
