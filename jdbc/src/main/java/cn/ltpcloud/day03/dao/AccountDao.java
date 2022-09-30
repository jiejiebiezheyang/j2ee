package cn.ltpcloud.day03.dao;

import java.sql.Connection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/30/10:25
 * @Description:
 */
/*
 * 数据层
 * */
public interface AccountDao {

    // void update(Connection connection, Account account);

    cn.ltpcloud.day03.dao.Account queryOne(Connection connection, int id) throws Exception;
    List<Account> queryS(Connection connection) throws Exception;
    /*boolean insert(Account account);
    boolean delete(Account account);
    Account[] queryS(Account account);*/
}
