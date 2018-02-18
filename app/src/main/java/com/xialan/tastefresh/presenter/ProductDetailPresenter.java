package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.MainBean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.bean.ProductDetailBean;
import com.xialan.tastefresh.commonutil.AESHelper;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.RSAUtil;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.contract.ProductDetailContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/18.
 */

public class ProductDetailPresenter extends BasePresenter implements ProductDetailContract.Presenter {
    private final ProductDetailContract.View mView;

    public ProductDetailPresenter(ProductDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void getProductDetail(final String key, String[] body) {
        String rsAkey = BaseUtils.getRSAkey(key);
        String aesBody = BaseUtils.getAESBody(body, key);
        Log.e("miaodetail上传", key);
        NovateUtil.getInstance().call(NovateUtil.getApiService().getDataForDetail(rsAkey, aesBody), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                ProductDetailBean productDetailBean = ParseUtils.parseDataAES(key, responseBody, ProductDetailBean.class);
                boolean checkdata = ParseUtils.checkdata(productDetailBean.getCode());
                if (checkdata) {
                    mView.onGetDataSuccess(productDetailBean.getData());
                } else {
                    mView.onGetDataFailed(productDetailBean.getErr_msg());
                }
            }
        });


    }
}
