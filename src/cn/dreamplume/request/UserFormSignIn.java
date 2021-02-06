package cn.dreamplume.request;

import cn.dreamplume.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/6 10:36
 * @Created by 翊
 * User 表单登录
 */
@WebServlet(name = "UserFormSignIn")
public class UserFormSignIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // 创建连接对象
        Connection conn = new JDBCUtil().getConnection();
        // 创建 SQL 语句
        String sql = "select password from userform where name = ?";
        try {
            // 创建预编译 SQL 语句对象 pre
            PreparedStatement pre = conn.prepareStatement(sql);

            // 填充占位符
            pre.setString(1, name);

            // 获得筛选后的结果集
            ResultSet realPassword = pre.executeQuery();

            // 创建判断是否成功登录的布尔变量
            Boolean flag = false;
            while (realPassword.next()) {
                // 获取该结果集指针所指结果的第 1 个 String 对象密码是否等于用户输入密码
                if (realPassword.getString(1).equals(password)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                response.getWriter().write("成功登录.....");
            }else {
                response.getWriter().write("登陆失败，用户名或者密码错误");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
