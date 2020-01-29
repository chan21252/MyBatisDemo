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

    /**
     * 添加Employee
     *
     * @param employee Employee
     * @return boolean
     */
    boolean addEmployee(Employee employee);

    /**
     * 更新Employee
     *
     * @param employee Employee
     * @return boolean
     */
    boolean updateEmployee(Employee employee);

    /**
     * 根据ID删除Employee
     *
     * @param id id
     * @return boolean
     */
    boolean deleteEmployee(int id);
}
