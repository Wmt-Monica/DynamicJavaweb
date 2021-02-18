package cn.dreamplume.project.shopping.dao;

import cn.dreamplume.project.shopping.util.JDBCUtil;
import org.junit.Test;

import java.sql.*;

/**
 * @Classname ConnectionJDBC
 * @Description TODO
 * @Date 2021/2/18 9:11
 * @Created by 翊
 */
public class ConnectionJDBC {
    @Test
    public void test() throws SQLException {
        Boolean f = judgeLogin("王梦婷","12345678","管理员");
        System.out.println(f);
    }

    Connection conn;  // 连接对象

    public ConnectionJDBC() {
        conn = new JDBCUtil().getConnection();  // 使用辅助类从JDBC连接池中获取连接对象
    }

    /**
     * 判断用户登录是否出成功
     * @param userName 用户名
     * @param userPassword 密码
     * @param userType 用户类型
     * @return 返回是否成功登录
     */
    public Boolean judgeLogin(String userName, String userPassword, String userType) throws SQLException {
        String sql = "select userPassword, userType from commodity_user where userName = "+"\'"+userName+"\'";
        PreparedStatement pre = conn.prepareStatement(sql);

        ResultSet resultSet = pre.executeQuery();
        String password = null;
        String type = null;
        while (resultSet.next()) {
            password = resultSet.getString(1);
            type = resultSet.getString(2);
        }
        if (userPassword.equals(password) && userType.equals(type)) {
            return true;
        }
        return false;
    }
}
