package cn.dreamplume.affair;

import cn.dreamplume.util.JDBCUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/16 8:28
 * @Created by 翊
 */
@WebServlet(name = "Transfer")
public class Transfer extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 使用辅助类JDBCUtil类对象创建连接对象
        Connection conn = new JDBCUtil().getConnection();

        // 创建TransferPeople类List集合对象
        List<TransferPeople> peopleList = new ArrayList<>();

        String Transfer_out_account = request.getParameter("Transfer_out_account");  // 转出帐户名称
        String Transfer_in_account = request.getParameter("Transfer_to_account");  // 转入账户名称
        String amount_of_money = request.getParameter("amount_of_money");  // 转出金额

        // 创建 SQL 获取数据库中帐户所有的信息
        String sql = "select * from transfer";
        // 注意：此处的SQL语句中字符串参数要使用" ' " 字符括起来，其中就要使用转义字符
        String sql1 = "update transfer set balance = balance - "+amount_of_money+" where userName = "+ "\'"+Transfer_out_account+"\'";
        String sql2 = "update transfer set balance = balance + "+amount_of_money+" where userName = "+"\'"+Transfer_in_account+"\'";

        PreparedStatement pre = null;
        ResultSet resultSet = null;
        boolean flag = false;

        try {
            //  设置不自动提交
            conn.setAutoCommit(false);

            // 实行转账SQL语句
            transferRunning(conn, sql1);
            transferRunning(conn, sql2);

            // 实例化预编译SQL的PreparedStatement
            pre = conn.prepareStatement(sql);

            // 实例化 ResultSet 结果集对象
            resultSet = pre.executeQuery();

            // 创建结果元素对象
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 获取筛选出的一条记录中的属性总列数
            int colCount = metaData.getColumnCount();

            // 循环遍历每一条筛选出来的记录
            while (resultSet.next()) {
                TransferPeople people = new TransferPeople();

                for (int  i = 1; i <= colCount; i ++) {
                    String colName = metaData.getColumnName(i);
                    Object colValue = resultSet.getObject(i);
                    Field field = TransferPeople.class.getDeclaredField(colName);
                    field.setAccessible(true);
                    field.set(people, colValue);
                }
                peopleList.add(people);  // 将创建填充完属性的 people 对象添加入List集合中去
            }

            // 提交数据
            conn.commit();
            flag = true;
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            // 事务中间出现错误，实行回滚操作
            try {
                conn.rollback();  // 出现异常回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            // 在finally语句块中释放资源
            try {
                resultSet.close();
                pre.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //  将是否转账成功的表示布尔变量传入 request 对象中去
        request.setAttribute("flag", flag);

        // 将创建添加完对象的 TransferPeople 的 List 对象赋值到 request 域对象中去
        request.setAttribute("peopleList",peopleList);

        // 创建转发器
        RequestDispatcher dispatcher = request.getRequestDispatcher("Transfer.jsp");

        // 实行转发
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void transferRunning(Connection conn, String sql) throws SQLException {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.execute();
        pre.close();
    }
}
