package com.newcitysoft.study.spring.jdbc.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/11 9:53
 */
public class DeptSqlAdd extends SqlUpdate {

    private static final String SQL_ADD = "insert into dept(code,name) values(?,?)";

    public DeptSqlAdd(DataSource dataSource) {
        super(dataSource, SQL_ADD);
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    public int add(String code, String name) {
        return update(code, name);
    }
}
