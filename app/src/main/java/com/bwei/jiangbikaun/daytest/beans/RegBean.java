package com.bwei.jiangbikaun.daytest.beans;

import org.json.JSONException;
import org.json.JSONObject;

public class RegBean {

    /**
     * msg : 注册成功
     * code : 0
     */

    private String msg;
    private String code;

    public static RegBean objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, RegBean.class);
    }

    public static RegBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new com.google.gson.Gson().fromJson(jsonObject.getString(str), RegBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
