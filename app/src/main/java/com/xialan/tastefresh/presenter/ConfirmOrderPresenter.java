package com.xialan.tastefresh.presenter;

import com.xialan.tastefresh.base.BaseHttpUtil;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.AddressBean;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.AddressContract;
import com.xialan.tastefresh.contract.ConfirmOrderContract;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ConfirmOrderPresenter extends BasePresenter implements ConfirmOrderContract.Presenter {
    private final ConfirmOrderContract.View mView;

    public ConfirmOrderPresenter(ConfirmOrderContract.View view) {
        this.mView=view;
    }

    @Override
    public void getDataFromNet(String tag, String url, String key, String[] body) {
        switch (tag){
            case "creat_order":
                BaseHttpUtil.sendHttp(url, key, body,AddressBean.class, new BaseHttpUtil.OnRequestSuccessedListener<AddressBean>() {
                    @Override
                    public void onSuccessed(AddressBean addressBean) {
                        String code = addressBean.getCode();
                        boolean checkdata = ParseUtils.checkdata(code);
                        if (checkdata){
                            mView.creatOrderSuccessed();
                        }else{
                            UIUtils.showToast(ParseUtils.showErrMsg(addressBean.getErr_msg()));
                        }
                    }
                });
                break;
            case "query_address":
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
