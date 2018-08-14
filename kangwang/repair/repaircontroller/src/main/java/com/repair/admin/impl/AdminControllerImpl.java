package com.repair.admin.impl;

import com.repair.admin.AdminController;
import com.repair.model.request.AdminLoginRequest;
import com.repair.model.response.ReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auther   kangwang
 */
@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public ReturnValue login(AdminLoginRequest adminLoginRequest) throws Exception {

        return null;
    }

    @Override
    public ReturnValue loginout() {
        return null;
    }
}
