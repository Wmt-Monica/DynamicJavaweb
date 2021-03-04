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
 * @Date 2021/3/4 18:05
 * @Created by 翊
 */
@WebServlet(name = "HttpSessionActivationListenerServerStart")
public class HttpSessionActivationListenerServerStart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSessionActivationListenerPeople customer = new HttpSessionActivationListenerPeople();
        customer.setName("Hahn");
        customer.setAge(22);
        HttpSession session = request.getSession();

        // 将 people 对象存入 session 域对象中钝化
        System.out.println("将 customer 存入 session 内存中");
        session.setAttribute("customer", customer);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
