package cn.dreamplume.session_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/7 20:49
 * @Created by 翊
 * Session 对象的创建，使用，生命周期，销毁
 */
@WebServlet(name = "SessionTest")
public class SessionTest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        /**
         * Session 对象是存储在硬盘中的数据，是属于一个服务器准确分别会话的 Session 对象的标记
         * Session 属于客户端（会话）私有的 session 区域
         * request.getSession()方法内部会自动判断，该客户是否在服务端已经存在 session 对象
         * 如果客户端在该服务器不存在 session，那么就会创建一个新的 session 对象
         * 如果该客户在此服务器已经存在 session 获得已经存在的该 session 返回
         */
        HttpSession session = request.getSession();
        String id = session.getId();
        writer.write("JSESSIONID："+id+"<br>");

        /**
         * session 对象本身就是域对象，故因此向 session 对象中存取对象和销毁对象方法和域对象方法一致
         * setAttribute(String name,Object obj) ----> 存储对象
         * getAttribute(String name) ----> 取对象
         * removeAttribute() ----> 销毁对象
         */
        // 向 session 与对象存储数据
        session.setAttribute("name","石燔");

        // 向 session 域对象获取数据
        String name = (String)session.getAttribute("name");

        // 在页面中显示 session 所有的属性值
        writer.write("未删除 session 域对象中的 name 属性之前获取 session 的所有参数名和所对应的值<br>");
        Enumeration<String> names1 = session.getAttributeNames();
        while (names1.hasMoreElements()) {
            String name1 = names1.nextElement();
            String nameValue = (String)session.getAttribute(name1);
            writer.write(name1+" ----> "+nameValue+"<br>");
        }

        // 销毁 session 域对象中属性为 name 的数据
        session.removeAttribute("name");

        // 在页面中显示 session 所有的属性值
        writer.write("删除 session 域对象中的 name 属性之后获取 session 的所有参数名和所对应的值<br>");
        Enumeration<String> names2 = session.getAttributeNames();
        while (names2.hasMoreElements()) {
            String name2 = names2.nextElement();
            String nameValue = (String)session.getAttribute(name2);
            writer.write(name2+" ----> "+nameValue+"<br>");
        }

        /**
         * 设置 Session 对象的生命周期在web.xml 文件中写入 <session-config></session-config> 中的
         * <session-timeout></session-timeout> 中写入生命周期时间参数 （单位：分）
         * 创建：第一次执行 request.getSession() 方法时创建
         * 销毁：
         *      1）：服务器（非正常）关闭时
         *      2）：session 过期/失效（自己可以设置期效时间）
         *          期效起始时间：从不操作服务器的资源开始计时
         *      3）：手动销毁 session
         *          session.invalidate();
         */
        session.invalidate();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
