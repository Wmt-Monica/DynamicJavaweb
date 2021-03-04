package cn.dreamplume.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/3/4 16:14
 * @Created by 翊
 */
@WebServlet(name = "ServletContextAttributeListenerServer")
public class ServletContextAttributeListenerServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取 Context 对象
        ServletContext context = request.getServletContext();

        // 创建 Context 域对象
        context.setAttribute("content","Monica");
        System.out.println("==============================");

        // 修改 Context 域对象
        context.setAttribute("content","Hahn");
        System.out.println("==============================");

        // 移除 Context 域对象
        context.removeAttribute("content");
        System.out.println("==============================");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
