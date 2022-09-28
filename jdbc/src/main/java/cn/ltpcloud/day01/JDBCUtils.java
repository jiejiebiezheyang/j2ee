package cn.ltpcloud.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/16:13
 * @Description:
 */
public class JDBCUtils {
    static Properties prop;
    static String driver;
    static String url;
    static String  user;
    static String password;
    // 获取连接
    static {
        try {
            prop = new Properties();
            prop.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws Exception{
        // 注册驱动
        Class.forName(driver);
        // 返回连接
        return DriverManager.getConnection(url,user,password);
    }

    // 关闭连接
    @Deprecated
    public static void CloseConnection(Connection connection) throws Exception{
        if(connection!=null){
            connection.close();
        }
    }

    public static void release(Connection connection, Statement statement) throws Exception{
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
    }
}
