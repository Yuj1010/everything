package com.repair.model.response;

/**
 * auther   kangwang
 *
 * 公共的返回
 */

public class ReturnValue {
    private String Msg;
    private int code;
    private Object data;

    public ReturnValue(String msg, int code, Object data) {
        Msg = msg;
        this.code = code;
        this.data = data;
    }

    public ReturnValue() {
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
