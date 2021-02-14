package cn.dreamplume.MVC.dao;

import cn.dreamplume.MVC.domain.People;
import cn.dreamplume.MVC.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ConnectionJDBC
 * @Description TODO
 * @Date 2021/2/14 14:53
 * @Created by 翊
 */
public class ConnectionJDBC {
    Connection conn;

    // 初始化 Connection 连接对象
    public ConnectionJDBC() {
        conn = new JDBCUtil().getConnection();
    }

    public List<People> getObjectList() throws SQLException, NoSuchFieldException, IllegalAccessException {
        List<People> peopleList = new ArrayList<>();
        String sql = "select picture, name, age, position from people";
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int colCount = metaData.getColumnCount();
        while (resultSet.next()) {
            People people = new People();
            for (int i = 1; i <= colCount; i ++) {
                String colValue = resultSet.getString(i);
                String colName = metaData.getColumnName(i);
                Field field = People.class.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(people, colValue);
            }
            peopleList.add(people);
        }
        return peopleList;
    }

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException, SQLException {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();
        List<People> peopleList = connectionJDBC.getObjectList();
        for (People p : peopleList) {
            System.out.println(p.toString());
        }
    }
}
