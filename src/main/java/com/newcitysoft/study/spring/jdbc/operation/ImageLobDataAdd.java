package com.newcitysoft.study.spring.jdbc.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/11 11:31
 */
public class ImageLobDataAdd extends SqlUpdate {

    private static final String SQL = "insert into images(filename,entity) values(?,?)";

    private LobHandler lobHandler = new DefaultLobHandler();

    public ImageLobDataAdd(DataSource dataSource) {
        super(dataSource, SQL);
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.BLOB));
        compile();
    }

    public int add(String fileName, File file) {
        InputStream ins = null;

        try{
            ins = new FileInputStream(file);
            SqlLobValue lobValue = new SqlLobValue(ins, (int) file.length(), getLobHandler());

            return update(fileName, lobValue);
        } catch (Exception e) {
            try {
                if(ins != null) {
                    ins.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return 0;
    }

    public LobHandler getLobHandler() {
        return lobHandler;
    }

    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }
}
