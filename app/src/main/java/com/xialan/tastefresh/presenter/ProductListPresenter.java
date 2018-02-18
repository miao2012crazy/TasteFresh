package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.Datebean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.bean.MultipleItemForQuarter;
import com.xialan.tastefresh.bean.QuarterProduct2Bean;
import com.xialan.tastefresh.bean.WeekTimeBean;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.contract.ProductListContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ProductListPresenter extends BasePresenter implements ProductListContract.Presenter {
    private final ProductListContract.View mView;
    private List<MultipleItemForQuarter> multipleItemForQuarters = new ArrayList<>();
    public ProductListPresenter(ProductListContract.View view) {
        this.mView = view;
    }

    @Override
    public void getDataFromNet(String tag, String url, final String key, String[] body) {
        String rsAkey = BaseUtils.getRSAkey(key);
        String aesBody = BaseUtils.getAESBody(body, key);
        map.clear();
        map.put("key", rsAkey);
        map.put("body", aesBody);
        NovateUtil.getInstance().post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                QuarterProduct2Bean quarterProduct2Bean = ParseUtils.parseDataAES(key, responseBody, QuarterProduct2Bean.class);
                initParseData(quarterProduct2Bean);
            }
        });
    }

    private void initParseData(final QuarterProduct2Bean quarterProduct2Bean) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<QuarterProduct2Bean.DataBean> data = quarterProduct2Bean.getData();
                for (int i = 0; i < data.size(); i++) {
                    String product_periods = data.get(i).getProduct_periods();
                    Datebean datebean = new Datebean(product_periods,data.get(i).getPeriods_start());
                    MultipleItemForQuarter multipleItemForQuarter = new MultipleItemForQuarter(MultipleItem.MAIN_01, datebean);
                    multipleItemForQuarters.add(multipleItemForQuarter);
                    Log.e("miao 数据"+i,"添加了一条"+datebean.toString()+":::"+multipleItemForQuarters);
                    List<QuarterProduct2Bean.DataBean.WeekTimeBean> week_time = data.get(i).getWeek_time();
                    for (int j = 0; j < week_time.size(); j++) {
                        QuarterProduct2Bean.DataBean.WeekTimeBean weekTimeBean = week_time.get(j);
                        WeekTimeBean weekTimeBean1 = new WeekTimeBean(weekTimeBean.getImg_url(), weekTimeBean.getProduct_id(), weekTimeBean.getProduct_name(), weekTimeBean.getRecommend_season(), weekTimeBean.getCash_state());
                        MultipleItemForQuarter multipleItemForQuarter2 = new MultipleItemForQuarter(MultipleItem.MAIN_02,weekTimeBean1);
                        Log.e("miao 数据"+j,String.valueOf(multipleItemForQuarter2.getWeekTimeBean()));
                        multipleItemForQuarters.add(multipleItemForQuarter2);
                    }
                }
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        for(MultipleItemForQuarter item:multipleItemForQuarters){
                            Log.e("miao111", String.valueOf(item.getWeekTimeBean()));
                        }
                        mView.getDataSuccessed(multipleItemForQuarters);
                    }
                });
            }
        }).start();
    }
}
