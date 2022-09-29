package cn.ltpcloud.day02;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/29/14:45
 * @Description: PreparedStatement 通用更新和查询
 */
@SuppressWarnings("all")
public class PreparedStatementDemo02 {
    public static void main(String[] args) throws Exception {
        /*String sql = "update account set name = ? where name = ? or id = ?";
        Object[] arr = {"赵六六", "赵六", 2};
        System.out.println("受影响的行数:" + update(sql, arr));*/

        /*String sql = "select * from account where id = ?";
        Object[] arr = {5};
        System.out.println(queryOne(Account.class,sql, arr));*/

        String sql = "select * from account WHERE id < ?";
        Object[] arr = {11};
        List<Account> querys = querys(Account.class, sql, arr);
        querys.forEach(System.out::println);
    }

    // 通用更新
    public static int update(String sql, Object[] values) throws Exception {
        // 获取连接
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        // 设置值
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
        // 执行
        int rows = ps.executeUpdate();
        // 释放资源
        JDBCUtils.release(conn, ps);

        return rows;
    }

    // 通用查询 (单条数据)
    public static <T> T queryOne(Class<T> clazz, String sql, Object[] values) throws Exception {
        // 获取连接
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        // 设置值
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
        // 执行查询操作
        ResultSet rs = ps.executeQuery();
        // 处理数据数据
        ResultSetMetaData metaData = rs.getMetaData();
        if (rs.next()) {
            // 获取列数
            int columnCount = metaData.getColumnCount();
            // 需要返回的对象
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                // 获取列值(成员变量值)
                Object columnValue = rs.getObject(i + 1);
                // 获取列别名(成员变量)
                String columnLabel = metaData.getColumnLabel(i + 1);
                // 获取对象属性
                Field declaredField = clazz.getDeclaredField(columnLabel);
                // 取消语言检查机制
                declaredField.setAccessible(true);
                // 设置对象属性值
                declaredField.set(t, columnValue);
            }
            return t;
        }
        return null;
    }

    // 通用查询 (多条数据条数据)
    public static <T> List<T> querys(Class<T> clazz, String sql, Object[] values) throws Exception {
        // 结果集合
        List<T> list = new ArrayList<>();
        // 获取连接
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        // 设置值
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
        // 执行查询操作
        ResultSet rs = ps.executeQuery();
        // 处理数据数据
        ResultSetMetaData metaData = rs.getMetaData();
        while (rs.next()) {
            // 获取列数
            int columnCount = metaData.getColumnCount();
            // 需要返回的对象
            T t = clazz.newInstance();
            for (int i = 0; i < columnCount; i++) {
                // 获取列值(成员变量值)
                Object columnValue = rs.getObject(i + 1);
                // 获取列别名(成员变量)
                String columnLabel = metaData.getColumnLabel(i + 1);
                // 获取对象属性
                Field declaredField = clazz.getDeclaredField(columnLabel);
                // 取消语言检查机制
                declaredField.setAccessible(true);
                // 设置对象属性值
                declaredField.set(t, columnValue);
            }
            list.add(t);
        }
        return list;
    }
}
