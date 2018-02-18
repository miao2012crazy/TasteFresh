package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BaseHttpUtil;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.base.BaseRequestInterTwoParama;
import com.xialan.tastefresh.bean.AddressBean;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.bean.QueryIndexBean;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.AddressContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/23.
 */
public class AddressPresenter extends BasePresenter implements AddressContract.Presenter {
    private final AddressContract.View mView;

    public AddressPresenter(AddressContract.View view) {
        this.mView=view;
    }

    @Override
    public void getDataFromNet(String tag, String url,String key, String[] body) {
        switch (tag){
            case "delete":
                BaseHttpUtil.sendHttp(url, key, body,CommonBean.class, new BaseHttpUtil.OnRequestSuccessedListener<CommonBean>() {
                    @Override
                    public void onSuccessed(CommonBean commonBean) {
                        String code = commonBean.getCode();
                        boolean checkdata = ParseUtils.checkdata(code);
                        if (checkdata){
                          mView.deleteAddressSuccessed();
                        }else{
                        UIUtils.showToast(ParseUtils.showErrMsg(commonBean.getErr_msg()));
                        }
                    }
                });
                break;
            case "query":
                BaseHttpUtil.sendHttp(url, key, body,AddressBean.class, new BaseHttpUtil.OnRequestSuccessedListener<AddressBean>() {
                    @Override
                    public void onSuccessed(AddressBean addressBean) {
                        String code = addressBean.getCode();
                        boolean checkdata = ParseUtils.checkdata(code);
                        if (checkdata){
                            mView.getDataSuccessed(addressBean.getData());
                        }else{
                            UIUtils.showToast(ParseUtils.showErrMsg(addressBean.getErr_msg()));
                        }
                    }
                });
                break;
        }
    }
}
