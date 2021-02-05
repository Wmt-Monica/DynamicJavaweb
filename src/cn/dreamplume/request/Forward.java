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
 * @Date 2021/2/5 21:10
 * @Created by 翊
 * 转发和重定向的区别，request 中的域对象的范围：一次请求的范围
 */
@WebServlet(name = "Forward")
public class Forward extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 向 request 域对象中存入数据 name 赋值为 石燔
        request.setAttribute("name","石燔");
        // 重定向到Java服务器类 Forward3 对象中
        // 使用重定向是向服务器发出了两次请求，不是一次，所有在 forward3 中获取域对象中的 name 属性值时为 null
        response.sendRedirect("/forward3");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
