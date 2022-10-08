package cn.ltpcloud.day04;

import org.junit.Test;
import org.springframework.jdbc.core.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/08/14:10
 * @Description:
 */
public class JDBCTemplateDemo {
    @Test
    public void updateTest() throws Exception {
        // 创建jdbc template
        DataSource ds = JDBCUtils2.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);
        // 执行通用的更新操作
        String sql = "delete from account where id = ?";
        template.update(sql, 4);
    }

    @Test
    public void queryOne() throws Exception {
        // 创建jdbc template
        DataSource ds = JDBCUtils2.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);
        // 执行通用的更新操作
        String sql = "select count(*) from account";
        // 查询单个
        Long count = template.queryForObject(sql, long.class);
        System.out.println(count);
    }

    @Test
    public void queryOne2() throws Exception {
        // 创建jdbc template
        DataSource ds = JDBCUtils2.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);
        // 执行通用的更新操作
        String sql = "select * from account where id = ?";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
        // 查询单个
        Account account = template.queryForObject(sql, rowMapper, 2);
        System.out.println(account);
    }

    @Test
    public void query() throws Exception {
        // 创建jdbc template
        DataSource ds = JDBCUtils2.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);
        // 执行通用的更新操作
        String sql = "select * from account";
        List<Account> list = template.query(sql, new BeanPropertyRowMapper<>(Account.class));
        list.forEach(System.out::println);
    }

    @Test
    public void queryMap() throws Exception {
        // 创建jdbc template
        DataSource ds = JDBCUtils2.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);
        // 执行通用的更新操作 单条
        String sql = "select * from account where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    @Test
    public void queryMapList() throws Exception {
        // 创建jdbc template
        DataSource ds = JDBCUtils2.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);
        // 执行通用的更新操作 单条
        String sql = "select * from account";
        List<Map<String, Object>> maps = template.queryForList(sql);
        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("==========================================");

        }
    }
}
