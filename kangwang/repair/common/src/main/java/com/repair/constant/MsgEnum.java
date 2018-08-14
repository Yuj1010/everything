package com.repair.constant;

import com.repair.model.response.ReturnValue;

public enum MsgEnum {
    IS_NULL("参数为空！")
    ;
    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    MsgEnum(String msg) {
        Msg = msg;
    }
}
