package cn.ltpcloud.day04;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/16:13
 * @Description:
 */
/*
 * 对JDBCUtils进行优化
 * */
public class JDBCUtils2 {
    private static DataSource dataSource;

    static {
        try {
            Properties prop = new Properties();
            prop.load(ClassLoader.getSystemResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接池
    public static DataSource getDataSource() throws Exception {
        return dataSource;
    }

    // 关闭连接
    @Deprecated
    public static void CloseConnection(Connection connection) throws Exception {
            DbUtils.closeQuietly(connection);
    }

    public static void release(Connection connection, Statement statement) throws Exception {
            DbUtils.closeQuietly(connection);
            DbUtils.closeQuietly(statement);
    }
}
