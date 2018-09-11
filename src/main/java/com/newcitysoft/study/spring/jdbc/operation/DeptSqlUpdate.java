package com.newcitysoft.study.spring.jdbc.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/11 9:49
 */
public class DeptSqlUpdate extends SqlUpdate {

    private static final String SQL_UPDATE = "update dept set code=?,name=? where id=?";

    public DeptSqlUpdate(DataSource dataSource) {
        super(dataSource, SQL_UPDATE);
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }

    public int updateById(String code, String name, Integer id) {
        return update(code, name, id);
    }
}
