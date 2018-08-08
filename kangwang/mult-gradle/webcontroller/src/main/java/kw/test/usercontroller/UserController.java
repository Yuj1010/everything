package kw.test.usercontroller;

import kw.test.entrity.User;
import kw.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auther   kangwang
 */
@RestController
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping("/user")
    public User name(){
        User user = userService.getUserInfo() ;
        return user;
    }
}
