package com.chan.mybatis.util.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "select",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class SelectPlugin implements Interceptor {
    private Properties properties = new Properties();

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("查询执行前");
        Object object = invocation.proceed();
        System.out.println("查询执行后");
        return object;
    }

    public Object plugin(Object o) {
        System.out.println("插件...");
        return o;
    }

    public void setProperties(Properties properties) {
        System.out.println("设置properties");
        this.properties = properties;
    }
}
