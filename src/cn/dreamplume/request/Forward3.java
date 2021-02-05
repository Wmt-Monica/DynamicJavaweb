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
 * @Date 2021/2/5 21:26
 * @Created by 翊
 * 转发和重定向的区别，request 中的域对象的范围：一次请求的范围
 */
@WebServlet(name = "Forward3")
public class Forward3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = (String)request.getAttribute("name");
        response.getWriter().write("此处为 forward3 Java服务器Java对象中的相应内容......<br>");
        response.getWriter().write("name = "+name);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
