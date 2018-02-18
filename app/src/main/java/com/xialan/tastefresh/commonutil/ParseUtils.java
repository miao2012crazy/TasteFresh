package com.xialan.tastefresh.commonutil;

import android.util.Log;

import com.google.gson.Gson;
import com.xialan.tastefresh.contranst.NetMsgContranst;


import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/10.
 */

public class ParseUtils {
    /**
     * gson解析封装
     *
     * @param responseBody
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseData(ResponseBody responseBody, Class<T> tClass) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(responseBody.string(), tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    /**
     * gson解析封装
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseDataAES(String aeskey, ResponseBody responseBody, Class<T> tClass) {
        String decrypt = "";
        //解密
        try {
            Log.e("miaodetail解密开始", aeskey);
            String string = responseBody.string();
            Log.e("miao密文", string);
//            decrypt = AESHelper.decrypt(string, aeskey);
            decrypt=   AESOperator.decrypt(string,aeskey);

            Log.e("miao解密结果", decrypt);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("miao","解密失败");
        }
        Gson gson = new Gson();
        T t = gson.fromJson(decrypt, tClass);
        return t;
    }

    /**
     * gson解析封装
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseDataAES(String aeskey, String string, Class<T> tClass) {
        String decrypt = "";
        //解密
        try {
            Log.e("miaodetail解密开始", aeskey);
            Log.e("miao密文", string);
            decrypt=   AESOperator.decrypt(string,aeskey);
            Log.e("miao解密结果", decrypt);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("miao","解密失败");
        }
        Gson gson = new Gson();
        T t = gson.fromJson(decrypt, tClass);
        return t;
    }

    /**
     * 校验数据
     *
     * @param code
     * @return
     */
    public static boolean checkdata(String code) {
        switch (code) {
            case NetMsgContranst.DATA_SUCCESS:
                return true;
            case NetMsgContranst.DATA_FALSE:
                return false;
            case NetMsgContranst.DATA_TIME_OUT:
                return false;
            default:
                return false;
        }
    }

    public static String showErrMsg(String err_code) {
        switch (err_code) {
            case "60050":
                return "不存在的用户,还没有注册!";
            case "60051":
                return "密码错误";
            case "60052":
                return "账号状态异常,请联系管理员";
            case "60056":
                return "没有更多数据了!";
            case "60057":
                return "服务器内部错误!";
            case "60058":
                return "未登录!";
            case "60059":
                return "请求参数错误!";
            case "60060":
                return "图片大小为0!";
            case "60061":
                return "删除失败!";
            case "60062":
                return "未查询到用户信息!";
            case "60063":
                return "原密码错误!";
            case "60064":
                return "订单取消失败(请勿重复提交)";
            case "60065":
                return "已提交取消申请!";
            case "60066":
                return "订单未处于可取消状态!";
            case "60067":
                return "未查询到地址!";
            case "60068":
                return "当前订单地址不可修改!";
            case "60069":
                return "当前用户微信认证不通过 需要通过线下设备";
            case "60070":
                return "还没有进行实名认证!";
            case "60071":
                return "没找到图片!";
            case "60072":
                return "获取预支付id失败!";
            case "60073":
                return "未查询到版本信息!";
            case "60074":
                return "未查询到支付订单信息!";
            case "60075":
                return "连接支付宝服务器失败,请联系管理员!";
            case "60076":
                return "微信支付不可使用 请使用支付宝支付";
            case "60077":
                return "未找到商品 或商品已下架";
            case "60078":
                return "非法请求";
            case "60079":
                return "此账户已被锁定 请联系管理员";
            case "60080":
                return "添加新地址失败";
            case "60081":
                return "删除地址失败 稍后再试";
            case "60082":
                return "默认地址不可删除";
            case "60083":
                return "库存不足,请选择其他商品";
            case "60084":
                return "下单失败 联系客服";
            default:
                return "";
        }
    }


}
