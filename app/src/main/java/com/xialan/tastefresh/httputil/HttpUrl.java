package com.xialan.tastefresh.httputil;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpUrl {
    /**
     * url 选择器
     */
    private static int url_debug = 0;

    /**
     * 获取url 根路径
     *
     * @return
     */
    public static String getBaseUrl() {
        switch (url_debug) {
            case 0:
                return "http://tryto.dawoonad.com/service/";
            case 1:
                return "http://changxin.xialan.com/service/";
            case 2:
                return "http://beauty.dawoonad.com/";
            default:
                return "";
        }
    }


    //当季
    public static final String QUARTER_PRODUCT_INDEX = "search_product_index.ashx";
    //往季
    public static final String QUARTER_PAST_PRODUCT_INDEX = "search_past_product_index.ashx";
    //上周
    public static final String LAST_WEEK_PRODUCT = "search_last_week_product.ashx";

    //获取地址
    public static final String SEARCH_USER_ADDRESS = "search_user_address.ashx";

    //获取产品列表
    public static final String SEARCH_PRODUCT_LIST = "search_product_quarter.ashx";
    //地址管理
    public static final String ADDRESS_MANAGE = "add_user_address.ashx";

    //创建订单
    public static final String CREAT_ORDER = "creat_user_order.ashx";
    //查询订单
    public static final String SEARCH_ORDER = "search_user_order.ashx";
    //取消订单
    public static final String CANCEL_ORDER = "cancel_user_order.ashx";
    //修改密码
    public static final String UPDATE_USER_PSD = "update_user_pwd.ashx";
    //搜索测评报告
    public static final String SEARCH_REPORT = "search_report.ashx";
    //上传client_id 到服务器
    public static final String UPLOAD_CLIENT_ID = "upload_client_id.ashx";


}
