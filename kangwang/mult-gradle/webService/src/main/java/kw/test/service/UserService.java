package kw.test.service;

import kw.test.entrity.User;
import org.springframework.stereotype.Service;

/**
 * auther   kangwang
 */
@Service
public class UserService {
    public User getUserInfo(){
        User user = new User();
        user.setName("kangwang");
        user.setPassword("kangwang123");
        return user;
    }
}
