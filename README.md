# spring-study
spring揭秘的学习项目
代码参考自《Spring揭秘》

## 说明
项目中的测试代码，全部在test文件夹下。
### jdbc的使用方式
#### 1.pom引入依赖
    
    <!-- 添加MySQL依赖 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <!-- 添加JDBC依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <!-- 添加Druid依赖 -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.0.29</version>
    </dependency>
    
#### 2.项目配置文件制定数据源

    spring:
      application:
        name: spring-study
      datasource:
        url: jdbc:mysql://localhost:3306/springboot_demo?autoReconnect=true&characterEncoding=utf8
        username: root
        password: root
        driver-class-name: com.mysql.jdbc.Driver
        
#### 3.创建DataSource的Bean对象，供其他Dao使用
    
    import com.alibaba.druid.pool.DruidDataSource;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    
    import javax.sql.DataSource;
    
    /**
     * @author lixin.tian@renren-inc.com
     * @date 2018/9/11 9:45
     */
    
    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource")
    public class DataSourceConfig {
        private String url;
        private String username;
        private String password;
    
        @Bean
        public DataSource getDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        }
    
        public String getUrl() {
            return url;
        }
    
        public void setUrl(String url) {
            this.url = url;
        }
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    }

#### 4.使用
##### 4.1 基于操作对象的jdbc使用方式
###### 4.1.1 基于操作对象的更新

* 创建数据库表


        CREATE TABLE `dept` (
          `id` int(10) NOT NULL AUTO_INCREMENT,
          `code` varchar(255) DEFAULT NULL,
          `name` varchar(255) DEFAULT NULL,
          PRIMARY KEY (`id`)
        );    

* 创建对象

    
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    /**
     * @author lixin.tian@renren-inc.com
     * @date 2018/9/11 10:02
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class Dept {
        private String code;
        private String name;
    }


* 更新操作

    
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

* 增加操作
    

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
    
    
* 批量更新操作


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

测试用例如下：
    
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.junit4.SpringRunner;
    
    import javax.sql.DataSource;
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
    }