package com.xialan.tastefresh.presenter;

import android.util.Log;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.base.BasePresenter;
import com.xialan.tastefresh.bean.CommitReportBean;
import com.xialan.tastefresh.bean.CommonBean;
import com.xialan.tastefresh.bean.UpLoadPicBean;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.contract.CommitReportExperienceContract;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/22.
 */

public class CommitReportExperiencePresenter extends BasePresenter implements CommitReportExperienceContract.Presenter {
    private final CommitReportExperienceContract.View mView;

    public CommitReportExperiencePresenter(CommitReportExperienceContract.View view) {
        this.mView=view;
    }

    @Override
    public void upLodeReport(String userId, String report_id, String value, List<UpLoadPicBean> mList) {
        List<MultipartBody.Part> paramas=new ArrayList<>();
        MultipartBody.Part no0 =MultipartBody.Part.createFormData("user_id",userId);
        MultipartBody.Part no1 =MultipartBody.Part.createFormData("report_id",report_id);
        MultipartBody.Part no2 =MultipartBody.Part.createFormData("feedback_experience",value);
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
            }
        }
        paramas.add(no0);
        paramas.add(no1);
        paramas.add(no2);
        NovateUtil.getInstance().call(NovateUtil.getApiService().uploaduserid_2(paramas), new BaseSubscriber<ResponseBody>() {

            @Override
            public void onError(Throwable e) {
//                mView.hideCustomProgressBar();
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                CommonBean commonBean = ParseUtils.parseData(responseBody, CommonBean.class);
                assert commonBean != null;
                boolean checkdata = ParseUtils.checkdata(commonBean.getCode());
                if (checkdata){
                    mView.upLodeSuccessed();
                }else {
                    mView.getDataFailed(commonBean.getErr_msg());
                }

            }
        });
    }
}
