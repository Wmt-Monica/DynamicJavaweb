package cn.dreamplume.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/5 17:04
 * @Created by 翊
 * 防止窃取超链接的方式
 */
@WebServlet(name = "PreventTheft")
public class PreventTheft extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 使用 getHeader() 方法中的参数 referer 参数值获取请求头，防盗链
        String header = request.getHeader("referer");
        if (header == null || header.startsWith("http://localhost")) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("只有王梦婷本人的主机才可以访问到的页面<br>告诉你个秘密：石燔是个大傻逼");
        }else {
            response.getWriter().write("你这个盗链狗！！！");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
