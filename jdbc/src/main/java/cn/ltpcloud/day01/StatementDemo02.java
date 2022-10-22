package cn.ltpcloud.day01;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/17:09
 * @Description: 通用的更新和查询
 */
public class StatementDemo02 {
    public static void main(String[] args) throws Exception {
        // System.out.println("受影响行数:" + tableUpdate("UPDATE account SET balance = 20000 WHERE name = '张三'"));
        // System.out.println(queryOne("SELECT * FROM account WHERE id = 1", Account.class));
        System.out.println(queryOne("SELECT * FROM customers WHERE id = 1", Customer.class));
    }

    // 更新
    public static int tableUpdate(String sql) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate(sql);
        JDBCUtils.release(conn, stmt);
        return rows;
    }

    // 通用查询(单条数据)
    public static <T> T queryOne(String sql, Class<T> clazz) throws Exception {
        // 获取连接
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();

        // 执行sql
        ResultSet resultSet = stmt.executeQuery(sql);

        // 处理结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        if (resultSet.next()) {
            int columnCount = metaData.getColumnCount();
            // 需要返回的对象
            T t = clazz.newInstance();

            for (int i = 0; i < columnCount; i++) {
                // 获取列值(成员变量值)
                Object columnValue = resultSet.getObject(i + 1);
                // 获取列别名(成员变量)
                String columnLabel = metaData.getColumnLabel(i + 1);

                // 给对象属性复制
                Field declaredField = clazz.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t, columnValue);
            }

            return t;
        }
        return null;
    }
}