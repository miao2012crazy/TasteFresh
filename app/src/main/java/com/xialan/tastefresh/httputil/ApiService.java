package com.xialan.tastefresh.httputil;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/1.
 * 请求网络的API接口类
 */
public interface ApiService {


    @FormUrlEncoded
    @POST("search_product_list.ashx")
    Observable<ResponseBody> getDataForMain(@Field("key") String key);

    @FormUrlEncoded
    @POST("search_login.ashx")
    Observable<ResponseBody> login(@Field("key") String key, @Field("body") String body);

    @FormUrlEncoded
    @POST("search_product_detail.ashx")
    Observable<ResponseBody> getDataForDetail(@Field("key") String rsAkey, @Field("body") String aesBody);

    @Multipart
    @POST("http://changxin.xialan.com/service/commit_report.ashx")
    Observable<ResponseBody> uploaduserid(@Part() List<MultipartBody.Part> paramas);
    @Multipart
    @POST("http://changxin.xialan.com/service/commit_report_experience.ashx")
    Observable<ResponseBody> uploaduserid_2(@Part() List<MultipartBody.Part> paramas);
}
