package cn.ltpcloud.day04;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/08/9:42
 * @Description: 通过dbutils实现通用的CRUD
 */
public class DBUtilsDemo {
    @Test
    public void updateTest() throws Exception {
        // 创建查询运行期
        QueryRunner runner = new QueryRunner();
        // 执行通用的“更新”操作
        Connection conn = JDBCUtils.getConnection();
        String sql = "update account set name = ? where id = ?";
        runner.update(conn, sql, "王五", 1);
    }

    @Test
    public void updateTest2() throws Exception {
        // 通过连接池创建查询运行器
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行通用的更新操作
        String sql = "insert into account (name,balance)values(?,?)";
        runner.update(sql, "张无忌", 5000);
    }

    @Test
    public void queryOneTest() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from customers where id = ?";
        Customer customer = runner.query(sql, new ResultSetHandler<Customer>() {
            @Override
            public Customer handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date birth = resultSet.getDate("birth");
                    return new Customer(id, name, email, birth);
                }
                return null;
            }
        }, 1);
        System.out.println(customer);
    }

    @Test
    public void queryOneTest2() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from customers where id = ?";
        Customer customer = runner.query(sql, new ResultSetHandler<Customer>() {
            @Override
            public Customer handle(ResultSet resultSet) throws SQLException {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                if (resultSet.next()) {
                    try {
                        Class<Customer> clazz = Customer.class;
                        Customer customer = clazz.newInstance();
                        for (int i = 0; i < columnCount; i++) {
                            String columnLabel = metaData.getColumnLabel(i + 1);
                            Object value = resultSet.getObject(columnLabel);
                            Field declaredField = clazz.getDeclaredField(columnLabel);
                            declaredField.setAccessible(true);
                            declaredField.set(customer, value);

                        }
                        return customer;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                return null;
            }
        }, 1);
        System.out.println(customer);
    }

    @Test
    public void queryOneTest3() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from customers where id = ?";
        Customer customer = runner.query(sql, new BeanHandler<Customer>(Customer.class), 1);
        System.out.println(customer);
    }

    @Test
    public void queryTest() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from Account";
        List<Account> list = runner.query(sql, new BeanListHandler<Account>(Account.class));
        list.forEach(System.out::println);
    }

    @Test
    public void queryTest2() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from account";
        // key字段名，value字段值
        Map<String, Object> map = runner.query(sql, new MapHandler());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    @Test
    public void queryTest3() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from account";
        // 将每条数据封装到一个map中，然后将map封装到list中
        List<Map<String, Object>> list = runner.query(sql, new MapListHandler());
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("====================");
        }
    }

    @Test
    public void querySingleTest() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils2.getDataSource());
        // 执行单条数据的查询
        String sql = "select * from account where id > ?";
        Object query = runner.query(sql, new ScalarHandler<>(), 1);
        System.out.println(query);
    }
}
