package cn.ltpcloud.day01;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/14:14
 * @Description: 数据库连接
 */
@SuppressWarnings("all")
public class DBConnectionDemo {
    @Test
    public void demo1() throws Exception{
        // 加载(注册)驱动
        DriverManager.registerDriver(new Driver());
        // 获取连接对象
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String password = "123";
        Connection conn = DriverManager.getConnection(url, user, password);

        System.out.println(conn);
    }

    @Test
    public void demo2() throws Exception{
        // 加载(注册)驱动
        DriverManager.registerDriver(new Driver());
        // 获取连接对象
        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        Connection conn = DriverManager.getConnection(url, user, password);

        System.out.println(conn);
    }

    @Test
    public void demo3() throws Exception{
        // 加载配置文件
        Properties prop = new Properties();
        prop.load(DBConnectionDemo.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        // 获取配置数据
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        // 加载(注册)驱动
        Class.forName(driver);
        // 获取连接对象
        Connection conn = DriverManager.getConnection(url, user, password);

        System.out.println(conn);
    }
}
