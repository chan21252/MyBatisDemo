package com.chan.mybatis.dao;

import com.chan.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * EmployeeSQL映射接口，SQL注解
 */
public interface EmployeeMapperAnnotation {
    @Select("SELECT id, last_name, gender, email FROM tb_employee WHERE id = #{id}")
    Employee getEmployeeById(int id);
}
