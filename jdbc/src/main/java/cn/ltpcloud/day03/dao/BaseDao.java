package cn.ltpcloud.day03.dao;

import cn.ltpcloud.day02.JDBCUtils;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/29/14:45
 * @Description: PreparedStatement 通用更新和查询
 */
public abstract class BaseDao<T> {

    private Class<T> clazz;

    // 获取子类继承父类时，父类声明的泛型
    {
        // 获取子类带有泛型父类
        Type type = this.getClass().getGenericSuperclass();
        // 转成参数化类型
        ParameterizedType parameterizedType = (ParameterizedType) type;
        // 获取泛型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        // 获取泛型中声明的类型
        clazz = (Class<T>) actualTypeArguments[0];
    }

    // 通用更新
    public int update(Connection connection, String sql, Object... values) throws Exception {
        // 获取连接
        PreparedStatement ps = connection.prepareStatement(sql);
        // 设置值
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
        // 执行
        int rows = ps.executeUpdate();
        // 释放资源
        JDBCUtils.release(null, ps);

        return rows;
    }

    // 通用查询 (单条数据)
    public T queryOne(Connection connection, String sql, Object... values) throws Exception {
        // 获取连接
        PreparedStatement ps = connection.prepareStatement(sql);
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
    public  List<T> querys(Connection connection, String sql, Object[] values) throws Exception {
        // 结果集合
        List<T> list = new ArrayList<>();
        // 获取连接
        PreparedStatement ps = connection.prepareStatement(sql);
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

    public  List<T> queryAll(Connection connection,String sql) throws Exception {
        // 结果集合
        List<T> list = new ArrayList<>();
        // 获取连接
        PreparedStatement ps = connection.prepareStatement(sql);
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
