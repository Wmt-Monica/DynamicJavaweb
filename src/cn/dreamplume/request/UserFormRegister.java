package cn.dreamplume.request;

import cn.dreamplume.util.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/6 10:26
 * @Created by 翊
 * User 表单注册
 */
@WebServlet(name = "UserFormRegister")
public class UserFormRegister extends HttpServlet {

    @Override
    public void init() throws ServletException {
        int userID = 0;
        this.getServletContext().setAttribute("userID",userID);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");



        // 使用 request 请求对象的 getParameterMap() 方法获取参数 Map 集合
        Map<String, String[]> objectMap = request.getParameterMap();
//        Map objectMap = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : objectMap.entrySet()) {
//            response.getWriter().write("key--->"+entry.getKey()+" : ");
//            for (String value : entry.getValue()) {
//                response.getWriter().write("value--->"+value+" , ");
//            }
//            response.getWriter().write("<br>");
//        }
//        response.getWriter().write("遍历结束");

        // 创建表单的 javaBean 对象 User
        User user = new User();

        // 使用 BeanUtils 进行自动映射封装
        // 此处需要导入包 commons-beanutils-1.8.3.jar和commons-logging-1.1.1.jar
        // 注意：除了导入 src 的 lib 包以外，还要导入 WEB-INF 的 lib 包下
        try {
            BeanUtils.populate(user, objectMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        int userID = (int) this.getServletContext().getAttribute("userID");
        userID ++;
        user.setId(String.valueOf(userID));
        this.getServletContext().setAttribute("userID",userID);

        //现在这个位置 user对象已经封装好了
        //手动封装uid----uuid---随机不重复的字符串32位--java代码生成后是36位
        user.setUid(UUID.randomUUID().toString());

        try {
            register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 返回注册成功提示页面
        response.getWriter().write("注册成功。请尝试登录<br>");
        response.getWriter().write("<form action=\"userFormSignIn\" method=\"post\">\n" +
                "        用户名：<input type=\"text\" name=\"name\"><br>\n" +
                "        密码：<input type=\"password\" name=\"password\"><br>\n" +
                "        <input type=\"submit\" value=\"登录\">\n" +
                "    </form>                    ");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void register(User user) throws SQLException {

        // 1.在连接池获取连接对象(使用默认的数据库 formData 对象)
        Connection conn = new JDBCUtil().getConnection();

        // .创建 SQL 语句
        String sql = "insert into userform values(?,?,?,?,?,?,?,?)";

        // .创建预编译 SQL 语句 PreparedStatement 对象
        PreparedStatement pre = conn.prepareStatement(sql);

        // 填充 SQL 语句中的占位符
        pre.setString(1, user.getUid());
        pre.setString(2, user.getId());
        pre.setString(3, user.getName());
        pre.setString(4, user.getPassword());
        pre.setString(5, user.getEmail());
        pre.setString(6, user.getTelephone());
        pre.setString(7, user.getBirthday());
        pre.setString(8, user.getSex());

        // 执行 SQL 语句
        pre.execute();

        pre.close();
        conn.close();

    }
}
