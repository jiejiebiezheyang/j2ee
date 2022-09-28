package cn.ltpcloud.day01;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/17:09
 * @Description: 通用的更新和查询
 */
public class StatementDemo02 {
    public static void main(String[] args) throws Exception {
        /*String sql = "UPDATE account SET balance = 20000 WHERE name = '张三'";
        System.out.println("受影响行数:"+tableUpdate(sql));*/

        String sql = "SELECT * FROM account";
        Class<Account> clazz = Account.class;
        System.out.println(query(sql,clazz));
    }

    // 更新
    public static int tableUpdate(String sql) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate(sql);
        JDBCUtils.release(conn, stmt);
        return rows;
    }

    // 通用查询
    public static <T> List query(String sql, Class<T> ObjectClass) throws Exception {
        // 返回的结果集
        List<T> list = new ArrayList<>();

        // 获取连接
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();

        // 执行sql
        ResultSet resultSet = stmt.executeQuery(sql);

        // 处理结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 处理数据
        while (resultSet.next()) {
            // 通过类创建对象
            T t = ObjectClass.newInstance();
            // 获取类的字段属性
            Field[] declaredFields = ObjectClass.getDeclaredFields();
            for (int i = 1; i <= columnCount; i++) {
                resultSet.getObject(i);
                // 获取字段
                Field declaredField = declaredFields[i-1];
                // 取消语言检查机制
                declaredField.setAccessible(true);
                // 设置当前字段的值
                declaredField.set(t,resultSet.getObject(i));
            }
            // 将当前类的实列对象存入集合
            list.add(t);
        }
        // 返回结果集
        return list;
    }
}
