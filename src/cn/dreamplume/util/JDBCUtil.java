package cn.dreamplume.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Classname JDBCUtil
 * @Description TODO
 * @Date 2021/1/31 14:06
 * @Created by 翊
 */
public class JDBCUtil {

    // 创建 ComboPooledDataSource 数据库连接池对象
    public static ComboPooledDataSource source = new ComboPooledDataSource(); // 这里不传入参数这里使用默认的数据库

    // 创建 getConnection() 方法来获取 Connection 连接对象
    public Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
           throw new RuntimeException();
        }
    }
}
