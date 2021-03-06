package cn.dreamplume.project.shopping.service;

import cn.dreamplume.project.shopping.dao.ConnectionJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
                // 判断用户信息正确，再转发到 main.jsp 页面中前要对用户是否勾选下次自动登录的选项
                String automaticLogon = request.getParameter("automaticLogon");  // 获取是否勾选了自动登录的选项
                if (automaticLogon != null) {
                    // 创建用户信息的 Cookie 对象
                    Cookie userNameCookie = new Cookie("userNameCookie", URLEncoder.encode(userName,"utf-8"));
                    Cookie userPasswordCookie = new Cookie("userPasswordCookie",URLEncoder.encode(userPassword,"utf-8"));
                    Cookie userTypeCookie = new Cookie("userTypeCookie",URLEncoder.encode(userType,"utf-8"));
                    // 设置 Cookie 的持久化时间（单位：秒）
                    userNameCookie.setMaxAge(60*60*24);
                    userPasswordCookie.setMaxAge(60*60*24);
                    userTypeCookie.setMaxAge(60*60*24);
                    // 设置 Cookie 的携带路径
                    userNameCookie.setPath("/");
                    userPasswordCookie.setPath("/");
                    userTypeCookie.setPath("/");
                    // 将 Cookie 对象存入 response 对象中
                    response.addCookie(userNameCookie);
                    response.addCookie(userPasswordCookie);
                    response.addCookie(userTypeCookie);
                }

                HttpSession session = request.getSession();
                session.setAttribute("userName",userName);


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
