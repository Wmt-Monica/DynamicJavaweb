package cn.dreamplume.project.shopping.dao;

import cn.dreamplume.project.shopping.domain.ShoppingObject;
import cn.dreamplume.project.shopping.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ConnectionJDBC
 * @Description TODO
 * @Date 2021/2/18 9:11
 * @Created by 翊
 */
public class ConnectionJDBC {
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
        resultSet.close();
        pre.close();
        return false;
    }

    /**
     * 获取数据库中商品表中所有商品对象的 List 集合
     * @return 返回商品 List 集合
     */
    public List<ShoppingObject> getShoppingObjects(String commodityType, String commodityName) throws SQLException, NoSuchFieldException, IllegalAccessException {
        String sql = "select * from commodity";
        if (!commodityType.equals("")&&!commodityName.equals("")) {
            sql = "select * from commodity where type like "+"\'%"+commodityType+"%\'"+"or name like "+"\'%"+commodityName+"%\'";
        }else if (!commodityType.equals("")) {
            sql = "select * from commodity where type like "+"\'%"+commodityType+"%\'";
        }else if(!commodityName.equals("")) {
            sql = "select * from commodity where name like "+"\'%"+commodityName+"%\'";
        }else {
            sql = "select * from commodity";
        }
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int colCount = metaData.getColumnCount();
        List<ShoppingObject> shoppingObjectList = new ArrayList<>();
        while (resultSet.next()) {
            ShoppingObject shopObj = new ShoppingObject();
            for (int i = 1; i <= colCount; i ++) {
                Object colValue = resultSet.getObject(i);
                String colName = metaData.getColumnName(i);
                Field field = ShoppingObject.class.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(shopObj, colValue);
            }
            shoppingObjectList.add(shopObj);
        }
        resultSet.close();
        pre.close();
        return shoppingObjectList;
    }

    @Test
    public void test() throws SQLException {

    }

    /**
     * 获取商品类型的数据库中的enum所有可能值
     * @return 返回商品所有可能类型的字符串数组
     * @throws SQLException
     */
    public String[] getTypes() throws SQLException {
        String sql = "SELECT\n" +
                "column_type\n" +
                "FROM\n" +
                "information_schema.COLUMNS\n" +
                "WHERE\n" +
                "TABLE_SCHEMA = \"formdata\"\n" +
                "AND DATA_TYPE = 'enum'\n" +
                "AND table_name=\"commodity\"\n" +
                "AND column_name=\"type\"";
        PreparedStatement pre = new JDBCUtil().getConnection().prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        String typeList = null;
        while (resultSet.next()) {
            typeList = resultSet.getString(1);
        }
//        System.out.println(typeList.toString());
        typeList = typeList.replace("enum(","");
        typeList = typeList.replace(")","");
        typeList = typeList.replace("'","");
        String[] types = typeList.split(",");
        return types;
    }
}
