package com.xialan.tastefresh.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */
public class AddressBean {

    /**
     * code : S
     * data : [{"address_no":"1","is_default":"1","address_area":"天津市和平区","address_detail":"天津市和平区天津火车站出门直走5000米 大超市","recv_name":"喵喵喵","recv_tel":"18222703922"},{"address_no":"2","is_default":"0","address_area":"天津市和平区","address_detail":"天津市西清区天津席庆初出门直走5000米 大超市","recv_name":"王小二","recv_tel":"18222703922"},{"address_no":"3","is_default":"0","address_area":"天津市西青区","address_detail":"天津市西清区天津席庆初出门直走5000米 大超市","recv_name":"王小二","recv_tel":"18222703922"}]
     */
    private String code;
    private List<DataBean> data;
    private String err_msg;

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * address_no : 1
         * is_default : 1
         * address_area : 天津市和平区
         * address_detail : 天津市和平区天津火车站出门直走5000米 大超市
         * recv_name : 喵喵喵
         * recv_tel : 18222703922
         */

        private String address_no;
        private String is_default;
        private String address_area;
        private String address_detail;
        private String recv_name;
        private String recv_tel;

        public String getAddress_no() {
            return address_no;
        }

        public void setAddress_no(String address_no) {
            this.address_no = address_no;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getAddress_area() {
            return address_area;
        }

        public void setAddress_area(String address_area) {
            this.address_area = address_area;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getRecv_name() {
            return recv_name;
        }

        public void setRecv_name(String recv_name) {
            this.recv_name = recv_name;
        }

        public String getRecv_tel() {
            return recv_tel;
        }

        public void setRecv_tel(String recv_tel) {
            this.recv_tel = recv_tel;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "address_no='" + address_no + '\'' +
                    ", is_default='" + is_default + '\'' +
                    ", address_area='" + address_area + '\'' +
                    ", address_detail='" + address_detail + '\'' +
                    ", recv_name='" + recv_name + '\'' +
                    ", recv_tel='" + recv_tel + '\'' +
                    '}';
        }
    }
}
