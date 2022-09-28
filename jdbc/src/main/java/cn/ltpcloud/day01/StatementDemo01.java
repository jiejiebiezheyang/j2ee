package cn.ltpcloud.day01;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/16:11
 * @Description:
 */
@SuppressWarnings("all")
public class StatementDemo01 {
    @Test
    public void updateDemo() throws Exception {
        // 获取连接
        Connection conn = JDBCUtils.getConnection();
        // 创建Statement
        Statement stmt = conn.createStatement();
        // 执行更新操作
        // String sql = "INSERT INTO account(name,balance) VALUES ('zl','1000')";
        // String sql = "UPDATE account SET name='张三' WHERE id = 1";
        String sql = "DELETE FROM account WHERE id = 3";
        stmt.execute(sql);

        // int rows = stmt.executeUpdate(sql); // 返回影响的行数
        // System.out.println(rows);

        // 释放资源
        JDBCUtils.release(conn, stmt);
    }

    @Test
    public void queryDemo() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            // 执行查询
            String sql = "SELECT * FROM account";
            ResultSet rs = stmt.executeQuery(sql);

            // 解析结果集
            while (rs.next()) { // 判断是否有下一行数据
                // 方式1，通过列索引
                /*int id = rs.getInt(1);
                String name = rs.getString(2);
                int balance = rs.getInt(3);*/

                /*Object id = rs.getObject(1);
                Object name = rs.getObject(2);
                Object balance = rs.getObject(3);*/

                // 方式2,通过列的别名获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");
                Account account = new Account(id, name, balance);
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.release(conn, stmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
