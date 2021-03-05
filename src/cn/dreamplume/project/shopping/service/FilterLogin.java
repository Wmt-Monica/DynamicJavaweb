package cn.dreamplume.project.shopping.service;

import cn.dreamplume.project.shopping.dao.ConnectionJDBC;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Classname FilterLogin
 * @Description TODO
 * @Date 2021/3/5 22:11
 * @Created by 翊
 */
public class FilterLogin implements Filter {
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();

        Cookie[] cookies = ((HttpServletRequest)request).getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                // 存在上一次自动登录的保留选项且该复选框时被勾选的状态
                if (c.getName().equals("automaticLogon") && c.getValue().equals("no")) {
                    // 从 session 对象中获取对应登录的用户信息
                    String userName = (String) session.getAttribute("userName");
                    String userPassword = (String) session.getAttribute("userPassword");
                    String userType = (String) session.getAttribute("userType");
                    try {
                        // 使用辅助类 ConnectionJDBC 的 judgeLogin() 方法判断用户信息是否成功登录
                        Boolean flag = new ConnectionJDBC().judgeLogin(userName, userPassword, userType);
                        // 通断判断用户信息来进行转发或者时提示
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
            }
        }

//        if (automaticLogon != null || rememberInformation != null) {
//            //  设置 Cookie 的存在路径
//            userNameCookie.setPath("/judgeLogin");
//            userPasswordCookie.setPath("/judgeLogin");
//            userTypeCookie.setPath("/judgeLogin");
//            automaticLogonCookie.setPath("/judgeLogin");
//            rememberInformationCookie.setPath("/judgeLogin");
//            // 将 Cookie 对象存入 response 对象中去
//            // 注意：addCookie() 方法只有 HttpServletResponse 对象中可以添加 Cookie 对象
//            ((HttpServletResponse) response).addCookie(userNameCookie);
//            ((HttpServletResponse) response).addCookie(userPasswordCookie);
//            ((HttpServletResponse) response).addCookie(userTypeCookie);
//            ((HttpServletResponse) response).addCookie(automaticLogonCookie);
//            ((HttpServletResponse) response).addCookie(rememberInformationCookie);
//            // 将 Cookie 数据存入 HttpSession 对象中去
//            session.setAttribute("userNameCookie",userNameCookie);
//            session.setAttribute("userPasswordCookie",userPasswordCookie);
//            session.setAttribute("userTypeCookie",userTypeCookie);
//            session.setAttribute("automaticLogonCookie",automaticLogonCookie);
//            session.setAttribute("rememberInformationCookie",rememberInformationCookie);
//        }else {
//            Cookie[] cookies = ((HttpServletRequest)request).getCookies();
//            for (Cookie c : cookies) {
//
//            }
//            // 如果未勾选下次自动登录和记住用户名和密码就将这些 Cookie 从该路径中删除
//            userNameCookie.setMaxAge(0);
//            userPasswordCookie.setMaxAge(0);
//            userTypeCookie.setMaxAge(0);
//            automaticLogonCookie.setMaxAge(0);
//            rememberInformationCookie.setMaxAge(0);
//        }
    }

    @Override
    public void destroy() {

    }
}
