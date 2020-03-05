package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User selectUserById(int id);

    public void insertUser(User user);

    public void insertUserByMap(Map<String, Object> userMap);

    public void updatePasswordById(User user);

    public void deleteUserById(int id);

    public void insertUserList(List<User> userList);
}