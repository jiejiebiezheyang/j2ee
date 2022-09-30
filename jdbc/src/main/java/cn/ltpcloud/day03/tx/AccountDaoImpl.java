package cn.ltpcloud.day03.tx;

import cn.ltpcloud.day03.DBTools;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/10:27
 * @Description:
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public void update(Connection connection, Account account) {
        String sql = "update account set balance = ? where id = ?";
        try {
            DBTools.update(connection, sql, account.getBalance(), account.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account queryOne(Connection connection,int id) throws Exception {
        String sql = "select * from account where id = ?";
        return DBTools.queryOne(connection,Account.class,sql,id);
    }
}
