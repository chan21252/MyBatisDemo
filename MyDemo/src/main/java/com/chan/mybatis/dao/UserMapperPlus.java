package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.User;

public interface UserMapperPlus {
    public User selectUserById(int id);
}
