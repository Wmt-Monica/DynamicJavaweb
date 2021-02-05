package cn.dreamplume.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/5 17:22
 * @Created by 翊
 */
@WebServlet(name = "Parameter")
public class Parameter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String[] hobbys = request.getParameterValues("hobby");
        PrintWriter writer = response.getWriter();
        // 获取参数值的时候中文使用 URLDecoder.decode(参数值,"utf-8");
        writer.write("userName = "+ URLDecoder.decode(userName,"utf-8") +"<br>");
        writer.write("password = "+password+"<br>");
        for (String hobby : hobbys) {
            writer.write("hobby = "+hobby+"<br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
