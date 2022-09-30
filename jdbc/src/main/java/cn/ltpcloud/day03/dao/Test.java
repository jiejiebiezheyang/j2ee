package cn.ltpcloud.day03.dao;

import cn.ltpcloud.day03.JDBCUtils;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/12:12
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        AccountDaoImpl a = new AccountDaoImpl();
        System.out.println(a.queryOne(conn, 1));
        System.out.println(a.queryS(conn));
    }
}
