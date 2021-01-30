package cn.dreamplume.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExampleServelt extends HttpServlet {
    String contain;

    // 1.默认初始 Servlet 对象执行的 init() 方法
    @Override
    public void init() throws ServletException {
        contain = "hello world";
    }

    // 2.每当请求与服务器建立连接都调用的 service() 方法
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service() 方法启动......");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置相应的类型
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.write("<h1>doGet() 方法调用......</h1>");
    }

    // 每当连接对象被销毁前执行 destroy() 方法
    @Override
    public void destroy() {
        System.out.println("destroy() 方法被调用.....");
    }
}
