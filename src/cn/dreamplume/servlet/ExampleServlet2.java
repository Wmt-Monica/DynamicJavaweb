package cn.dreamplume.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExampleServlet2")
public class ExampleServlet2 extends HttpServlet {

    /*
        此处未重写 service() 方法，故从父类 HttpServlet 寻找该方法
        父类中的 service() 方法中将 ServletRequest 和 ServletResponse 对象分别转换成了
        HttpServletRequest 和 HttpServletResponse 对象之后再执行 protected 方法 service()
        之后会根据表单的提交方式分别进行不同的操作
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("石燔大傻瓜！");
    }
}
