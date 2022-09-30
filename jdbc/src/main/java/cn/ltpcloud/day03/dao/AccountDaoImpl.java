package cn.ltpcloud.day03.dao;

import cn.ltpcloud.day03.DBTools;

import java.sql.Connection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/10:27
 * @Description:
 */
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

    @Override
    public Account queryOne(Connection connection, int id) throws Exception {
        String sql = "select * from account where id = ?";
        return queryOne(connection,sql,id);
    }

    @Override
    public List<Account> queryS(Connection connection) throws Exception {
        String sql = "select * from account";
        return queryAll(connection,sql);
    }
}
