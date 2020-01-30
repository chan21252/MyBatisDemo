package com.chan.mybatis.dao;

import com.chan.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 测试多个参数
     *
     * @param id       ID
     * @param lastName LastName
     * @return Employee
     */
    Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    /**
     * 根据Map里的ID和LastName查询
     *
     * @param empMap Map
     * @return Employee
     */
    Employee getEmployeeByMap(@Param("map") Map<String, Object> empMap);

    /**
     * 根据List第一个元素获取Employee
     *
     * @param ids List
     * @return Employee
     */
    Employee getEmployeeByList(List<Integer> ids);
}
