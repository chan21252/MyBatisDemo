package com.chan.mybatis.test;

import com.chan.mybatis.bean.Employee;
import com.chan.mybatis.dao.EmployeeMapper;
import com.chan.mybatis.dao.EmployeeMapperAnnotation;
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
    private static final String CONFIG_FILENAME = "mybatis-config.xml";
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";

        //加载MyBatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "product");

        //获取SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        //查询
        //Employee employee = session.selectOne("com.chan.mybatis.EmployeeMapper.selectEmployee", 1);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);

        //销毁SqlSession
        session.close();
    }

    @Test
    public void test2() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is, "product");
            SqlSession session = sqlSessionFactory.openSession();

            EmployeeMapperAnnotation employeeMapperAnnotation = session.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = employeeMapperAnnotation.getEmployeeById(1);
            System.out.println(employee);

            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试增删改查
     *
     * @throws IOException IOException
     */
    @Test
    public void testCURD() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(CONFIG_FILENAME);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "development");
        SqlSession session = sqlSessionFactory.openSession();

        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        boolean result;
        //测试添加
        Employee employee = new Employee(null, "jerry", '1', "jerry@mybatis.com");
        result = employeeMapper.addEmployee(employee);
        System.out.println(result);
        System.out.println("自增主键值为" + employee.getId());

        //测试更新
        Employee employee2 = new Employee(1, "jerry", '1', "jerry@mybatis.com");
        result = employeeMapper.updateEmployee(employee2);
        System.out.println(result);

        //测试删除
        result = employeeMapper.deleteEmployee(2);
        System.out.println(result);

        //手动提交
        session.commit();

        //释放资源
        session.close();
        inputStream.close();
    }
}
