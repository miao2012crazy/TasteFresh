package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.luck.picture.lib.entity.LocalMedia;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.CommitReportBean;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.bean.UpLoadPicBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.contract.CommitReportContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/21.
 */

public class CommitReportPresenter extends BasePresenter implements CommitReportContract.Presenter {
    private final CommitReportContract.View mView;

    public CommitReportPresenter(CommitReportContract.View view) {
        this.mView=view;
    }

    @Override
    public void upLodeReport(String userId, String order_id, String value, String score, List<UpLoadPicBean> mList) {
        List<MultipartBody.Part> paramas=new ArrayList<>();
        MultipartBody.Part no0 =MultipartBody.Part.createFormData("user_id",userId);
        MultipartBody.Part no1 =MultipartBody.Part.createFormData("order_id",order_id);
        MultipartBody.Part no2 =MultipartBody.Part.createFormData("process",value);
        MultipartBody.Part no3 =MultipartBody.Part.createFormData("score",score);
        for (int i=0;i<mList.size();i++){
            UpLoadPicBean upLoadPicBean = mList.get(i);
            File file = new File(upLoadPicBean.getUrl());
            Log.e("miao",file.length()+"");
            switch (upLoadPicBean.getTag()){
                case "3":
                    RequestBody requestBody =RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"), file);
                    //参数1 数组名，参数2 文件名。
                    MultipartBody.Part photo1part = MultipartBody.Part.createFormData("image"+i, file.getName(), requestBody);
                    paramas.add(photo1part);
                    break;
                case "4":
                    RequestBody requestBody1 =RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    //参数1 数组名，参数2 文件名。
                    MultipartBody.Part photo1part1 = MultipartBody.Part.createFormData("video", file.getName(), requestBody1);
                    paramas.add(photo1part1);
                    break;
            }
        }
        paramas.add(no0);
        paramas.add(no1);
        paramas.add(no2);
        paramas.add(no3);
        NovateUtil.getInstance().call(NovateUtil.getApiService().uploaduserid(paramas), new BaseSubscriber<ResponseBody>() {

            @Override
            public void onError(Throwable e) {
//                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                CommitReportBean commonBean = ParseUtils.parseData(responseBody, CommitReportBean.class);
                assert commonBean != null;
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.upLodeSuccessed(commonBean.getData().getReport_id());
                }else {
                    mView.getDataFailed(commonBean.getErr_msg());
                }

            }
        });
    }
}
