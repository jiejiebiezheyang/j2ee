package cn.ltpcloud.day03;


import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/9:35
 * @Description: 事务
 */

// 需求:王五给田七转账100
public class TXDemo {
    // 转账 // 如果两个update之间出现异常，下面update语句就不会执行了
    @Test
    public void transfer() {
        try {
            // 王五-100
            DBTool.update("update account set balance = ? Where name = ?", 900, "王五");
            //模拟异常
            System.out.println(1 / 0);
            // 田七+100
            DBTool.update("update account set balance = ? Where name = ?", 1100, "田七");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 转账优化(添加事务)
    @Test
    public void transfer2() throws Exception {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            // 取消自动提交
            conn.setAutoCommit(false);
            // 王五-100
            DBTool.update("update account set balance = ? Where name = ?", 900, "王五");
            //模拟异常
            System.out.println(1 / 0);
            // 田七+100
            DBTool.update("update account set balance = ? Where name = ?", 1100, "田七");
            // 手动提交
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtils.release(conn,null);
        }
    }


    // 事务优化
    @Test
    public void transfer3() throws Exception {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            // 取消自动提交
            conn.setAutoCommit(false);
            // 王五-100
            DBTools.update(conn,"update account set balance = ? Where name = ?", 900, "王五");
            //模拟异常
            System.out.println(1 / 0);
            // 田七+100
            DBTools.update(conn,"update account set balance = ? Where name = ?", 1100, "田七");
            // 手动提交
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtils.release(conn,null);
        }
    }
}
