package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.QueryIndexBean;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.RSAUtil;
import com.xialan.tastefresh.contract.QuarterProductContract;
import com.xialan.tastefresh.httputil.HttpUrl;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/23.
 */

public class QuarterProductPresenter extends BasePresenter implements QuarterProductContract.Presenter {
    private final QuarterProductContract.View mView;

    public QuarterProductPresenter(QuarterProductContract.View view) {
        this.mView=view;
    }
    @Override
    public void getDataFromNet(String tag, String url, final String key) {
        String rsAkey = BaseUtils.getRSAkey(key);
        map.clear();
        map.put("key",rsAkey);
        Novate instance = NovateUtil.getInstance();
        instance.post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                Log.e("miao","请求失败");
                e.printStackTrace();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                    QueryIndexBean queryIndexBean = ParseUtils.parseDataAES(key, responseBody, QueryIndexBean.class);
                    mView.getDataSuccessed(queryIndexBean.getData().getDate_list());
            }
        });
    }
}
