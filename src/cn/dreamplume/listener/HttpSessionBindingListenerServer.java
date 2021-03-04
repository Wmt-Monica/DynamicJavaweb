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
 * @Date 2021/3/4 17:08
 * @Created by 翊
 */
@WebServlet(name = "HttpSessionBindingListenerServer")
public class HttpSessionBindingListenerServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        HttpSessionPeople people = new HttpSessionPeople();
        people.setName("Monica");
        people.setAge(20);

        // 将 People 对象绑定到 Session 域对象中去
        session.setAttribute("people", people);

        // 将 People 对象从 Session 域对象解除绑定
        session.removeAttribute("people");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
