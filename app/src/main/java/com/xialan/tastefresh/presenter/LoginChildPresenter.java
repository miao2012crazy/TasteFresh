package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.bean.UserInfoBean;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.SharePreUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.LoginChildContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/18.
 */

public class LoginChildPresenter extends BasePresenter implements LoginChildContract.Presenter {
    private final LoginChildContract.View mView;

    public LoginChildPresenter(LoginChildContract.View view) {
        this.mView = view;
    }


    @Override
    public void login(final String key, String[] body) {
        String rsAkey = BaseUtils.getRSAkey(key);
        String aesBody = BaseUtils.getAESBody(body, key);
        NovateUtil.getInstance().call(NovateUtil.getApiService().login(rsAkey.trim(), aesBody.trim()), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = "";
                try {
                    string = responseBody.string();
                } catch (Exception ex) {
                }
                UserInfoBean userInfoBean = ParseUtils.parseDataAES(key, string, UserInfoBean.class);
                boolean checkdata = ParseUtils.checkdata(userInfoBean.getCode());
                if (checkdata) {
                    //登录信息本地化保存
                    SharePreUtils.put(UIUtils.getContext(), "time", UIUtils.getTime());
                    SharePreUtils.put(UIUtils.getContext(), "login_key", key);
                    SharePreUtils.put(UIUtils.getContext(), "user_login", string);

                    mView.onLoginSuccessed(userInfoBean);
                } else {
                    mView.onLoginFailed(userInfoBean.getErr_msg());
                }
            }
        });

    }
}
