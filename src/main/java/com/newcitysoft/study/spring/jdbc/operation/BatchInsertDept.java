package com.newcitysoft.study.spring.jdbc.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/11 9:59
 */
public class BatchInsertDept extends BatchSqlUpdate {

    private static final String SQL = "insert into dept(code,name) values(?,?)";

    public BatchInsertDept(DataSource dataSource) {
        super(dataSource, SQL);
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    public void batchInsert(List<Dept> deptList) {
        deptList.forEach(this::singleUpdate);
        flush();
    }

    private void singleUpdate(Dept dept) {
        update(dept.getCode(), dept.getName());
    }
}
