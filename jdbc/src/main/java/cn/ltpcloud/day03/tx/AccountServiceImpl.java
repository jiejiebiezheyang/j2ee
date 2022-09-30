package cn.ltpcloud.day03.tx;

import cn.ltpcloud.day03.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/10:34
 * @Description:
 */

/*
 * 业务层
 * */
public class AccountServiceImpl implements AccountService {
    private AccountDao dao;

    public AccountServiceImpl(AccountDao dao) {
        this.dao = dao;
    }

    /**
     * @param srcID  转出账户
     * @param destID 转入账户
     * @param amount 转账金额
     */
    // 转账业务
    @Override
    public boolean transfer(Integer srcID, Integer destID, Integer amount) {
        Connection conn = null;
        try {
            // 在业务曾提供连接对象
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            // 获取转出账户
            Account srcAccount = dao.queryOne(conn, srcID);
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            // 转出
            dao.update(conn, srcAccount);
            System.out.println(1/0);
            // 获取转入账户
            Account destAccount = dao.queryOne(conn, destID);
            destAccount.setBalance(destAccount.getBalance() + amount);
            dao.update(conn, destAccount);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.release(conn, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
