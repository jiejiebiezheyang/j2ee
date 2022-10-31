package cn.ltpcloud;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws Exception {
        getInfo(1);

    }

    public static void getInfo(int id) throws Exception {
        DriverManager.registerDriver(new Driver());

        String url = "jdbc:mysql://127.0.0.1/test";
        String user = "root";
        String password = "123";
        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement ps = conn.prepareStatement("select * from dept where id = ?");
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();

        Map<String, Object> map = new HashMap<>();

        while (resultSet.next()) {
            for (int i = 0; i < count; i++) {
                // 获取列值(成员变量值)
                Object columnValue = resultSet.getObject(i + 1);
                // 获取列别名(成员变量)
                String columnLabel = metaData.getColumnLabel(i + 1);
                map.put(columnLabel, columnValue);
            }
        }

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }
}
