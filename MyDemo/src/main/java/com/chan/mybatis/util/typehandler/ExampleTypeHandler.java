package com.chan.mybatis.util.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
public class ExampleTypeHandler implements TypeHandler<String> {

    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, s);
    }

    public String getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public String getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
