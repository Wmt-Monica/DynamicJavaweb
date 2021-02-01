package cn.dreamplume.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/1 9:28
 * @Created by 翊
 */
@WebServlet(name = "ServletContext")
public class ServletContextTest extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1.获取 ServletContext 对象
        ServletContext servletContext = getServletContext();
        // 2.获得初始化参数
        String initParameter = servletContext.getInitParameter("url");
        response.getWriter().write("initParameter = "+initParameter+"<br>");
        // 3.获取a b c d.txt 不同路径下的文件的绝对路径
        String realPath_A = servletContext.getRealPath("a.txt");
        String realPath_B = servletContext.getRealPath("WEB-INF/b.txt");
        String realPath_C = servletContext.getRealPath("WEB-INF/classes/c.txt");
        response.getWriter().write("a.txt : "+realPath_A+"<br>");
        response.getWriter().write("b.txt : "+realPath_B+"<br>");
        // 在项目 src 下的文件姐可以从 web 项目的 classes 目录下使用 getRealPath() 方法找到对应的绝对路径
        response.getWriter().write("c.txt : "+realPath_C+"<br>");
        // 当文件不在 web 目录以下就wu
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
