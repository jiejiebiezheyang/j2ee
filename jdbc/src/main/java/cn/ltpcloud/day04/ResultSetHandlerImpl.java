package cn.ltpcloud.day04;

import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/08/10:25
 * @Description:
 */
public class ResultSetHandlerImpl<T> implements ResultSetHandler<T> {
    private Class<T> clazz;

    public ResultSetHandlerImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handle(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (resultSet.next()) {
            try {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(columnLabel);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, value);

                }
                return t;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
