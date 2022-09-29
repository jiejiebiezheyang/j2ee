package cn.ltpcloud.day02;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/29/14:33
 * @Description:
 */

@SuppressWarnings("all")
public class PreparedStatementDemo01 {
    @Test
    public void demo1() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        String sql = "delete from account where id = ?";
        // 获取PreparedStatement
        PreparedStatement ps = conn.prepareStatement(sql);
        // 填充占位符的值
        ps.setInt(1, 1);
        int rows = ps.executeUpdate();
        System.out.println("受影响的行数:" + rows);

        JDBCUtils.release(conn, ps);
    }

    @Test
    public void demo2() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        String sql = "select * from account where id = ?";
        // 获取PreparedStatement
        PreparedStatement ps = conn.prepareStatement(sql);
        // 填充占位符的值
        ps.setInt(1, 2);
        ResultSet rs = ps.executeQuery();

        // 解析结果
        if (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int balance = rs.getInt(3);
            System.out.println(id + name + balance);
        }

        JDBCUtils.release(conn, ps, rs);

    }
}
