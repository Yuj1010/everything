package com.repair.admin;

import com.repair.model.request.AdminLoginRequest;
import com.repair.model.response.ReturnValue;

/**
 * auther   kangwang
 */
public interface AdminService {
    public void login(AdminLoginRequest adminLoginRequest);

    public void loginout();
}
