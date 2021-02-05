package cn.dreamplume.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/5 15:53
 * @Created by 翊
 */
@WebServlet(name = "HeaderServlet")
public class HeaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        // 1.获取指定的头
        String header = request.getHeader("User-Agent");
        writer.write("获得指定的响应头(User-Agent)<br>header = "+header);
        // 2.获取所有的头部名称
        writer.write("获得所有的响应头名称及其对应的值<br>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String  headerValue = request.getHeader(headerName);
            writer.write(headerName+" ----> "+headerValue+"<br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
