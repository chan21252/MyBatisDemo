package com.chan.mybatis.dao;

import com.chan.mybatis.pojo.Blog;

public interface BlogMapper {
    public Blog selectBlogByIdReturnResultMap(int id);
}
