package com.chan.mybatis.util;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Chan
 * @since 2020/3/28
 */
public class MyDatabaseIdProvider implements DatabaseIdProvider {
    public void setProperties(Properties properties) {

    }

    public String getDatabaseId(DataSource dataSource) throws SQLException {
        if (dataSource == null) {
            throw new NullPointerException("dataSource cannot be null");
        } else {
            try {
                return this.getDatabaseName(dataSource);
            } catch (Exception e) {
                System.out.println("Could not get a databaseId from dataSource:" + e.getMessage());
                return null;
            }
        }
    }

    private String getDatabaseName(DataSource dataSource) throws SQLException {
        Connection conn = null;
        String databaseName;
        try {
            conn = dataSource.getConnection();
            databaseName = conn.getMetaData().getDatabaseProductName();
            System.out.println("数据库厂商：" + databaseName);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return databaseName;
    }
}
