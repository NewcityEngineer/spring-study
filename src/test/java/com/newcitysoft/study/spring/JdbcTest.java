package com.newcitysoft.study.spring;

import com.newcitysoft.study.spring.jdbc.operation.BatchInsertDept;
import com.newcitysoft.study.spring.jdbc.operation.Dept;
import com.newcitysoft.study.spring.jdbc.operation.DeptSqlAdd;
import com.newcitysoft.study.spring.jdbc.operation.DeptSqlUpdate;
import com.newcitysoft.study.spring.jdbc.operation.ImageLobDataAdd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/11 10:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void deptAddTest() {
        DeptSqlAdd add = new DeptSqlAdd(dataSource);

        add.add("caiwu", "财务部");
    }

    @Test
    public void deptUpdateTest() {
        DeptSqlUpdate update = new DeptSqlUpdate(dataSource);

        update.updateById("cw", "财务部", 1);
    }

    @Test
    public void deptBatchInsertTest() {
        List<Dept> deptList = new ArrayList<>();

        deptList.add(Dept.builder().code("1").name("123").build());
        deptList.add(Dept.builder().code("2").name("455").build());
        deptList.add(Dept.builder().code("3").name("787").build());
        deptList.add(Dept.builder().code("4").name("asdasda").build());
        deptList.add(Dept.builder().code("5").name("qweqwe").build());
        deptList.add(Dept.builder().code("5").name("qegqweq").build());

        BatchInsertDept batchInsertDept = new BatchInsertDept(dataSource);

        batchInsertDept.batchInsert(deptList);
    }

    @Test
    public void imageAddTest() {
        ImageLobDataAdd add = new ImageLobDataAdd(dataSource);

        String fileName = "2.png";
        File file = new File("D:\\data\\2.png");

        add.add(fileName, file);
    }
}
