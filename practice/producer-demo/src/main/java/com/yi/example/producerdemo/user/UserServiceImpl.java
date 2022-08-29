package com.yi.example.producerdemo.user;

import org.springframework.stereotype.Service;

/**
 * @author : xiao on 2022/8/26 20:39
 * @version : 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByName(String name) {

        User user = new User();
        user.setName(name);

        return user;
    }
}
