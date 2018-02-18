package com.xialan.tastefresh.presenter;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.LastWeekProductBean;
import com.xialan.tastefresh.bean.QuarterProduct2Bean;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.contract.LastWeekProductContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/23.
 */

public class LastWeekProductPresenter extends BasePresenter implements LastWeekProductContract.Presenter {
    private final LastWeekProductContract.View mView;

    public LastWeekProductPresenter(LastWeekProductContract.View view) {
        this.mView = view;
    }

    @Override
    public void getDataFromNet(String tag, String url, final String key) {
        String rsAkey = BaseUtils.getRSAkey(key);
        map.clear();
        map.put("key", rsAkey);
        NovateUtil.getInstance().post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                LastWeekProductBean lastWeekProductBean = ParseUtils.parseDataAES(key, responseBody, LastWeekProductBean.class);
                mView.getDataSuccess(lastWeekProductBean.getData().getProduct_list_week());
            }
        });
    }
}
