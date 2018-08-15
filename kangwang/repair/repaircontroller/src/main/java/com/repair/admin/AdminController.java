package com.repair.admin;

import com.repair.model.request.AdminLoginRequest;
import com.repair.model.response.ReturnValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * auther   kangwang
 */
@RequestMapping("admin")
public interface AdminController {

    @PostMapping("login")
    public ReturnValue login(@RequestBody AdminLoginRequest adminLoginRequest) throws Exception;

    @RequestMapping("loginout")
    public ReturnValue loginout();
}
