package cn.ltpcloud.day02;

import cn.ltpcloud.day01.Customer;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/09/29/16:21
 * @Description:
 */

// 操作Blob类型的数据
public class PreparedStatementDemo03 {
    @Test
    public void demo1() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, "张三");
        ps.setObject(2, "zs@sian.com");
        ps.setObject(3, "2012-12-12");
        InputStream is = new FileInputStream("src/main/resources/img/01.jpg");
        ps.setBlob(4, is);

        ps.executeUpdate();

        JDBCUtils.release(conn, ps);
    }

    @Test
    public void demo2() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 19);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            Date birth = rs.getDate(4);
            Customer c = new Customer(id, name, email, birth);
            System.out.println(c);

            // 获取blob事件
            Blob photo = rs.getBlob(5);
            InputStream is = photo.getBinaryStream();
            OutputStream os = new FileOutputStream("src/main/resources/img/01_bak.jpg");
            byte[] buff = new byte[1024];
            int len;
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
        }

        JDBCUtils.release(conn, ps);
    }
}
