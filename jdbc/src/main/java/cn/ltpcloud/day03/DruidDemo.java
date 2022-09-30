package cn.ltpcloud.day03;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/14:54
 * @Description:
 */
public class DruidDemo {
    private static DataSource ds;

    static {
        Properties prop = new Properties();
        try {
            prop.load(ClassLoader.getSystemResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }
}
