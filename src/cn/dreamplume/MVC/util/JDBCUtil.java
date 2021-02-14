package cn.dreamplume.MVC.util;

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
    public static ComboPooledDataSource source; // 这里不传入参数这里使用默认的数据库

    // 是用无参的工具类构造器是默认使用默认的数据库
    public JDBCUtil() {
        source = new ComboPooledDataSource();
    }

    // 使用有参的工具类构造方法使用指定的数据库
    public JDBCUtil(String databaseName) {
        source = new ComboPooledDataSource(databaseName);
    }

    // 创建 getConnection() 方法来获取 Connection 连接对象
    public Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
           throw new RuntimeException();
        }
    }
}
