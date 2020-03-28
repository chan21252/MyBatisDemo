package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.User;

public interface UserDao {
    public User selectUserById(int id);

    public void addUser(User user);
}
