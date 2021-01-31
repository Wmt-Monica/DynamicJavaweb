package cn.dreamplume.servlet;

import cn.dreamplume.util.JDBCUtil;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/1/31 13:26
 * @Created by 翊
 * 表单验证
 */
@WebServlet(name = "FormVerification")
public class FormVerification extends HttpServlet {

    @Test
    public void test() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.获取请求信息中的登录用户信息
        request.setCharacterEncoding("UTF-8");  // 设置 request 请求对象的字符集为 UTF-8
        response.setCharacterEncoding("UTF-8");  // 设置 response 相应对象的字符集为UTF-8

        // request 对象使用 getParameter() 方法传入表单信息的 name 属性中的参数来获取表单填写信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        response.setContentType("text/html");  // 设置 response 对象写入的信息内容的类型
        response.getWriter().write("userName = "+userName+"<br>");
        response.getWriter().write("password = "+password+"<br>");

        // 获取数据库中相关用户信息表的数据
        // 1):创建查询用户信息的 SQL语句
        String sql = "select user_name, user_password from user_information";
        // 2):通过工具类 JDBCUtil 类对象的 getConnection() 方法获取连接池中的对象
        Connection conn = new JDBCUtil().getConnection();
        System.out.println(conn);
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        try {
            // 3):创建 PreparedStatement 对象,来预编译 SQL 语句
             pre = conn.prepareStatement(sql);

            // 4):使用 PreparedStatement 对象的 executeQuery() 方法执行 SQL 语句获取查询结果集
             resultSet = pre.executeQuery();

            // 5):创建 ResultSetMetaData 元数据对象
            ResultSetMetaData metaData = resultSet.getMetaData();

            boolean flag = false;  // 判断用户是否成功登录的布尔变量

            // 6):遍历结果集进行登录验证操作
            while (resultSet.next()) {
                System.out.println("userName = "+resultSet.getString(1));
                System.out.println("password = "+resultSet.getString(2));
                if (userName.equals(resultSet.getString(1)) && password.equals(resultSet.getString(2))) {
                    flag = true;
                    break;
                }
            }

            // 7):根据登录验证返回相应的登录提示信息
            if (flag) {
                response.getWriter().write("成功登录");
                System.out.println("成功登录");
            }else {
                response.getWriter().write("用户名或密码错误，登录失败");
                System.out.println("用户名或密码错误，登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 8):释放资源
            try {
                resultSet.close();
                pre.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
