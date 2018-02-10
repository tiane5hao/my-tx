package com.zhengyun.service.impl;

import com.zhengyun.mapper.UserMapper;
import com.zhengyun.mytx.Business;
import com.zhengyun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 听风 on 2018/2/9.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Business
    public void addUser(String name){

        userMapper.addUser(name);
        /*if(true){
            throw new IllegalArgumentException();
        }*/
        userMapper.addUserRecord(name);
    }
}
