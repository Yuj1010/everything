package com.repair.admin.impl;

import com.repair.admin.AdminService;
import com.repair.constant.MsgEnum;
import com.repair.exception.ArgumentExceptionCheck;
import com.repair.model.request.AdminLoginRequest;
import com.repair.model.response.ReturnValue;

/**
 * auther   kangwang
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public void login(AdminLoginRequest adminLoginRequest) {

        ReturnValue returnValue = new ReturnValue();
        try {
            ArgumentExceptionCheck.check(adminLoginRequest);
        } catch (Exception e) {
            returnValue.setMsg(MsgEnum.IS_NULL.getMsg());
        }



    }

    @Override
    public void loginout() {
      
    }
}
