package cn.ltpcloud.day03;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/14:44
 * @Description:
 */
public class C3P0Demo {
    // 通过默认配置创建C3P0连接池
    // public static DataSource ds = new ComboPooledDataSource();

    // 通过指定配置名创建c3p0连接池
    public static DataSource ds = new ComboPooledDataSource("otherC3P0");

    // 获取连接对象
    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }
}
