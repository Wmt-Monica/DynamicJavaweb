package cn.dreamplume.ajax;

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
 * @Date 2021/3/2 19:50
 * @Created by 翊
 */
@WebServlet(name = "JudgeUseName")
public class JudgeUseName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String sql = "select count(*) from commodity_user where userName ="+"\""+userName+"\"";
        Connection conn = new JDBCUtil().getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            pre = conn.prepareStatement(sql);
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                pre.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        boolean flag = count > 0;
        response.getWriter().write(String.valueOf(flag));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
