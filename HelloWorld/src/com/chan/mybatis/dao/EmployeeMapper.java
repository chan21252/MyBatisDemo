package com.chan.mybatis.dao;

import com.chan.mybatis.bean.Employee;

/**
 * Employee数据接口
 *
 * @author Administrator
 */
public interface EmployeeMapper {

    /**
     * 根据id获取Employee对象
     *
     * @param i id
     * @return Employee
     */
    Employee getEmployeeById(int i);
}
