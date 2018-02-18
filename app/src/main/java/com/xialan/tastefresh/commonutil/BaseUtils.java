package com.xialan.tastefresh.commonutil;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/26.
 */

public class BaseUtils {
    private static StringBuilder str=new StringBuilder("utf-8");
    /**
     *
     * @param key AES随机key
     * @return  RSA公钥加密后的key
     */
    public static String getRSAkey(String key){
        String key1="";
        try {
            byte[] encryptedDataByteArray = RSAUtil.encryptByPublicKey(key.getBytes(), RSAUtil.PUBLIC_KEY);
            key1 = RSAUtil.encryptBASE64(encryptedDataByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            key1="";
        }
        return key1;
    }

    /**
     *
      * @param strArr 请求参数数组 不含key
     * @param key AES 未加密KEY
     * @return AES 加密json字符串
     */
    public static String getAESBody(String[] strArr, String key){
        str.delete(0,str.length());
        str.append("{");
        for (int i=0;i<strArr.length;i++){
            str.append(strArr[i]);
            if (i!=strArr.length-1){
                str.append(",");
            }
        }
        str.append("}#");
        String encrypt = null;
        try {
            encrypt = AESHelper.encrypt(str.toString(), key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("miao",str.toString());
        return encrypt;
    }

    //===============================拼接标准json==============================
    public static String getJsonData(String key,String value){
        return "\""+key+"\""+":"+"\""+value+"\"";
    }

    /*================================持久化登录相关处理=======================*/




}
