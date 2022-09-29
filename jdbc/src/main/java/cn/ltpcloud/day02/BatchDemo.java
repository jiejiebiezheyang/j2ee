package cn.ltpcloud.day02;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/29/16:50
 * @Description:
 */
// Statement和PreparedStatement的批处理
@SuppressWarnings("all")
public class BatchDemo {
    //Statement
    @Test
    public void demo1() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            String sql = "INSERT INTO batch values('name" + i + "')";
            stmt.executeUpdate(sql);
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1); //38718
    }

    //PreparedStatement(伪批处理)
    @Test
    public void demo2() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO batch values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            ps.setString(1, "name" + i);
            ps.executeUpdate();
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1); //33940
    }

    //PreparedStatement(真批处理)
    @Test
    public void demo3() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        // 取消自动提交
        conn.setAutoCommit(false);

        String sql = "INSERT INTO batch values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        long l1 = System.currentTimeMillis();
        for (int i = 1; i <= 20000; i++) {
            ps.setString(1, "name" + i);
            // 攒sql语句(将sql语句放入批处理缓冲区)
            ps.addBatch();
            if (i % 500 == 0) {
                // 执行批处理缓冲区所有的sql
                ps.executeBatch();
                // 清理批处理缓冲区
                ps.clearBatch();
            }
        }
        // 提交
        conn.commit();
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1); //3986
    }
}
