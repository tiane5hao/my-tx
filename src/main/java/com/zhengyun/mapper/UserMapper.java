package com.zhengyun.mapper;

import org.springframework.stereotype.Repository;

/**
 * Created by 听风 on 2018/2/9.
 */
@Repository
public interface UserMapper {

    void addUser(String name);

    void addUserRecord(String name);
}
