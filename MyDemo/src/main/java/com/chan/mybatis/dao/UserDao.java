package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public User selectUserById(int id);

    public User selectUserByIdAndUserName(@Param("id") int id, String username);

    public User selectUserByList(List<Integer> idList);

    public void addUser(User user);
}
