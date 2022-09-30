package cn.ltpcloud.day03;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/14:24
 * @Description:
 */
public class DBCPDemo {
    private static DataSource dataSource;// 连接池

    static {
        Properties prop = new Properties();
        try {
            prop.load(ClassLoader.getSystemResourceAsStream("dbcp.properties"));
            // 创建dbcp连接池
            dataSource = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取连接
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public static void main(String[] args)throws Exception{
        Connection conn = DBCPDemo.getConnection();
        System.out.println(conn);
    }
}
