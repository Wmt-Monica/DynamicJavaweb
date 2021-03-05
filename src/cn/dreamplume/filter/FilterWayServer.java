package cn.dreamplume.filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/3/5 19:12
 * @Created by 翊
 */
@WebServlet(name = "FilterWayServer")
public class FilterWayServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        System.out.println("FilterWayServer is running....");
        System.out.println("forward is success over....");
        request.getRequestDispatcher("/FilterTest.jsp").forward(request,response);
        // 其中 response 对象中的 sendRedirect() 方法是采用重定向的方式跳转到指定的页面，此方法可以用于 Filter
        // 的默认方式 REQUEST(request) 来实行有效的 Filter 中的 doFilter() 方法：重定向属于直接访问
//        response.sendRedirect("/FilterTest.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
