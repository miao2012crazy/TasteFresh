package com.xialan.tastefresh.base;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.xialan.tastefresh.commonutil.BaseUtils;
import com.xialan.tastefresh.commonutil.ParseUtils;
import com.xialan.tastefresh.commonutil.UIUtils;
import com.xialan.tastefresh.httputil.NovateUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/2/1.
 */

public class BaseHttpUtil {
    private static Map<String, Object> map = new HashMap<>();

    public static <T> T sendHttp(String url, final String key, final Class<T> clazz, final OnRequestSuccessedListener<T> onRequestSuccessedListener) {
        String rsAkey = BaseUtils.getRSAkey(key);
        map.clear();
        map.put("key", rsAkey);
        Novate instance = NovateUtil.getInstance();
        instance.post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                UIUtils.showToast("网络连接失败");
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                T t = ParseUtils.parseDataAES(key, responseBody, clazz);
                onRequestSuccessedListener.onSuccessed(t);
            }
        });
        return null;
    }


    public static <T> T sendHttp(String url, final String key,final String[] body, final Class<T> clazz, final OnRequestSuccessedListener<T> onRequestSuccessedListener) {
        String rsAkey = BaseUtils.getRSAkey(key);
        String aesBody = BaseUtils.getAESBody(body, key);
        map.clear();
        map.put("key", rsAkey);
        map.put("body", aesBody);

        Novate instance = NovateUtil.getInstance();
        instance.post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                UIUtils.showToast("网络连接失败");
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                T t = ParseUtils.parseDataAES(key, responseBody, clazz);
                onRequestSuccessedListener.onSuccessed(t);
            }
        });
        return null;
    }




    public interface OnRequestSuccessedListener<T> {
        void onSuccessed(T t);
    }
}
