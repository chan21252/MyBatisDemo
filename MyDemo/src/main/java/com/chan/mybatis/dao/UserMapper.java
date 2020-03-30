package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public User selectUserById(int id);

    public User selectUserByIdAndUserName(@Param("id") int id, String username);

    public User selectUserByList(List<Integer> idList);

    public void addUser(User user);

    public List<User> selectUserByUsernameReturnList(String username);

    public Map<String, Object> selectUserByIdReturnMap(int id);

    @MapKey("id")
    public Map<Integer, Object> selectUserByUsernameReturnMap(String username);
}
