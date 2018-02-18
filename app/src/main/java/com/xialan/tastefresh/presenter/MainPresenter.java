package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.HomeBeanCon;
import com.xialan.tastefresh.bean.MainBean;
import com.xialan.tastefresh.bean.MultipleItem;
import com.xialan.tastefresh.commonutil.AESHelper;
import com.xialan.tastefresh.commonutil.AESOperator;
import com.xialan.tastefresh.commonutil.RSAUtil;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.contract.MainContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private List<MultipleItem> mlist = new ArrayList<>();
    private MainBean.DataBean mData;

    public MainPresenter(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void getDataForMain(final String key) {
        String key1 = "";
        try {
            Log.e("miao", key);
            byte[] encryptedDataByteArray = RSAUtil.encryptByPublicKey(key.getBytes(), RSAUtil.PUBLIC_KEY);
            key1 = RSAUtil.encryptBASE64(encryptedDataByteArray);
        } catch (Exception ex) {

        }
        NovateUtil.getInstance().call(NovateUtil.getApiService().getDataForMain(key1), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                MainBean mainBean = ParseUtils.parseDataAES(key, responseBody, MainBean.class);
                boolean checkdata = ParseUtils.checkdata(mainBean.getCode());
                if (checkdata) {
                    List<MultipleItem> multipleItems = prepareData(mainBean.getData());
                    mView.OnGetDataForMainSuccess(multipleItems);
                } else {

                }
            }
        });
    }

    @Override
    public int getRefreshPosition() {
        return mlist.size() - mData.getProduct_next_week().size() - 1;
    }

    @Override
    public int getNextProductStartPosition() {
        int nextposition = mlist.size() - mData.getProduct_next_week().size()-2;
        return nextposition;
    }

    @Override
    public int getLoopActivityPosition() {
        int loopposition = mData.getLoop_list().size()-1;
        return loopposition;
    }


    private List<MultipleItem> prepareData(MainBean.DataBean data) {
        this.mData = data;
        mlist.clear();
        //添加轮播图
        HomeBeanCon homeBeanCon0 = new HomeBeanCon();
        homeBeanCon0.setLoop_list(data.getLoop_list());
        MultipleItem multipleItemloop = new MultipleItem(MultipleItem.MAIN_04, homeBeanCon0);
        mlist.add(multipleItemloop);

        MultipleItem.DataBean_1 dataBean_1 = new MultipleItem.DataBean_1("本周测评产品", data.getStamp());
        MultipleItem.DataBean_1 dataBean_1_1 = new MultipleItem.DataBean_1("下周测评产品预告", data.getStamp());
        MultipleItem multipleItem0 = new MultipleItem(MultipleItem.MAIN_01, dataBean_1);
        MultipleItem multipleItem3 = new MultipleItem(MultipleItem.MAIN_01, dataBean_1_1);
        mlist.add(multipleItem0);
        for (MainBean.DataBean.ProductListWeekBean item : data.getProduct_list_week()) {
            HomeBeanCon homeBeanCon = new HomeBeanCon();
            homeBeanCon.setImg_url(item.getImg_url());
            homeBeanCon.setProduct_id(item.getProduct_id());
            homeBeanCon.setProduct_name(item.getProduct_name());
            homeBeanCon.setRecommend_season(item.getRecommend_season());
            homeBeanCon.setCash_state(item.getCash_state());
            MultipleItem multipleItem = new MultipleItem(MultipleItem.MAIN_02, homeBeanCon);
            mlist.add(multipleItem);
        }
        mlist.add(multipleItem3);
        for (MainBean.DataBean.ProductNextWeekBean item : data.getProduct_next_week()) {
            HomeBeanCon homeBeanCon = new HomeBeanCon();
            homeBeanCon.setImg_url(item.getImg_url());
            homeBeanCon.setProduct_id(item.getProduct_id());
            MultipleItem multipleItem = new MultipleItem(MultipleItem.MAIN_03, homeBeanCon);
            mlist.add(multipleItem);
        }
        return mlist;
    }


}
