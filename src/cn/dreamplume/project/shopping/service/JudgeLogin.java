package cn.dreamplume.project.shopping.service;

import cn.dreamplume.project.shopping.dao.ConnectionJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/18 9:40
 * @Created by 翊
 */
@WebServlet(name = "JudgeLogin")
public class JudgeLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String userType = request.getParameter("userType");

        try {
            // 调用辅助类判断是否登录成功
            Boolean flag = new ConnectionJDBC().judgeLogin(userName, userPassword, userType);
            // 如果跳转成功就转发到主页面
            if (flag) {
                // 创建转发器
                RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
                // 执行转发
                dispatcher.forward(request, response);
            }else {
                request.setAttribute("error","用户名称或者密码，用户类型错误，请重新登录！");
                // 创建转发器
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                // 执行转发
                dispatcher.forward(request, response);
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
