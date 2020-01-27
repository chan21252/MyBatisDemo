package com.chan.mybatis.test;

import com.chan.mybatis.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis测试类
 *
 * @author Administrator
 */
public class TestMyBatis {
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";

        //加载MyBatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        //查询
        Employee employee = session.selectOne("com.chan.mybatis.EmployeeMapper.selectEmployee", 1);
        System.out.println(employee);

        //销毁SqlSession
        session.close();
    }
}
