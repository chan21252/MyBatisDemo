package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.Author;

public interface AuthorMapper {
    public Author selectAuthorById(Integer id);
}
