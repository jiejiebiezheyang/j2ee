package cn.ltpcloud.day03.tx;

import java.sql.Connection;

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

    void update(Connection connection,Account account);

    Account queryOne(Connection connection,int id) throws Exception;
    /*boolean insert(Account account);
    boolean delete(Account account);
    Account[] queryS(Account account);*/
}
