package cn.dreamplume.project.shopping.service;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @Classname FilterLogin
 * @Description TODO
 * @Date 2021/3/6 16:11
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
        // 将请求对象和相应对象转换成 Http 类型
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 获取 Cookie 中对应的用户数据
        String userName = null;
        String userPassword = null;
        String userType = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                System.out.println("Cookie name is "+c.getName()+"\t Cookie value is "+URLDecoder.decode(c.getValue(),"utf-8")+"\t Cookie maxAge is "+c.getMaxAge());
                if (c.getName().equals("userNameCookie")) {
                    c.setMaxAge(60*60*24);
                    userName = URLDecoder.decode(c.getValue(),"utf-8");
                }
                if (c.getName().equals("userPasswordCookie")) {
                    c.setMaxAge(60*60*24);
                    userPassword = URLDecoder.decode(c.getValue(),"utf-8");
                }
                if (c.getName().equals("userTypeCookie")) {
                    c.setMaxAge(60*60*24);
                    userType = URLDecoder.decode(c.getValue(),"utf-8");
                }
            }
        }

        // 如果用户对应的信息没有为空值，说明上次有成功登录且勾选了
        if (userName != null && userPassword != null && userType != null) {
            // 创建 HttpSession 对象
            HttpSession session = req.getSession();
            session.setAttribute("userName",userName);
        }

        // 放行
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {

    }
}
