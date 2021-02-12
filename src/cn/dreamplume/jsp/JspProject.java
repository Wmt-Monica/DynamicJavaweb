package cn.dreamplume.jsp;

import cn.dreamplume.util.JDBCUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/12 11:38
 * @Created by 翊
 */
@WebServlet(name = "JspProject")
public class JspProject extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        try {
            // 获取人物 People 的 List 集合对象
            List<People> peopleList = getPeopleList();
            // 将人物集合存入 request 对象中
            request.setAttribute("peopleList", peopleList);
            // 创建转发器
            RequestDispatcher dispatcher = request.getRequestDispatcher("JspProject.jsp");
            // 执行转发方法
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        List<People> list = (List<People>) request.getAttribute("peopleList");
        String name = list.get(1).getName();
        writer.write("name = "+name);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public List<People> getPeopleList() throws SQLException, NoSuchFieldException, IllegalAccessException {
        // 使用工具类JDBCUtil创建连接池对象
        Connection conn = new JDBCUtil().getConnection();

        // 创建SQL语句
        String sql = "select * from people";

        // 创建 People 对象 List 集合对象
        List<People> peopleList = new ArrayList<>();

        // 创建 PreparedStatement
        PreparedStatement pre = conn.prepareStatement(sql);

        // 创建 ResultSet 结果集对象
        ResultSet resultSet = pre.executeQuery();

        // 获取 java 对象对应表中的一行记录中的字段数
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col = metaData.getColumnCount();

        // 循环遍历所有的筛选出的结果集
        while (resultSet.next()) {
            // 根据结果集创建 People 对象
            People people = new People();

            // 循环遍历查询记录的字段
            for (int i = 1; i <= col; i ++) {
                String colValue = resultSet.getString(i);  // 获取该字段的值
                String colLabel = metaData.getColumnLabel(i);  // 获取该字段的字段名或者别称
                Field field = People.class.getDeclaredField(colLabel);  // 获取字段对应People类的属性对象
                field.setAccessible(true);  // 设置该属性可视化，可操作化
                field.set(people, colValue);  // 为创建的people对象赋值属性值
            }

            peopleList.add(people);  // 将重新赋值所有属性的people对象添加入People的List集合中
        }

        return peopleList;
    }
}
