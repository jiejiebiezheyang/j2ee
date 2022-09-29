package cn.ltpcloud.day02;

import cn.ltpcloud.day01.JDBCUtils;
import cn.ltpcloud.day01.StatementDemo02;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/28/16:11
 * @Description:
 */
@SuppressWarnings("all")
public class StatementDemo03 {
    // 需要拼串
    @Test
    public void demo1() throws Exception {
        String name = "赵六";
        int balance = 1000;
        String sql = "insert into account(name,balance) values('" + name + "'," + balance + ")";
        System.out.println(sql);
        System.out.println(StatementDemo02.tableUpdate(sql));
    }

    @Test
    public void demo2() throws Exception {
        String sql = "select * from account where name = 1 or '1' = '1' and balance = 1 or 1 = 1";
        System.out.println(sql);
        System.out.println(StatementDemo02.queryOne(sql, Account.class));
    }
}
