package cn.dreamplume.listener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/3/4 18:06
 * @Created by 翊
 */
@WebServlet(name = "HttpSessionActivationListenerServerEnd")
public class HttpSessionActivationListenerServerEnd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("从 session 域内存中获取 customer 对象");
        HttpSessionActivationListenerPeople customer = (HttpSessionActivationListenerPeople) session.getAttribute("customer");
        System.out.println("customer name is "+customer.getName());
        System.out.println("customer age is "+customer.getAge());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
