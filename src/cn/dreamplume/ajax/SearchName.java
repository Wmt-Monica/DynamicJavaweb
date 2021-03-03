package cn.dreamplume.ajax;

import cn.dreamplume.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/3/3 17:52
 * @Created by 翊
 */
@WebServlet(name = "SearchName")
public class SearchName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("name");  // 获取搜索商品类型框中的输入内容
        // 创建根据商品类型筛选商品的 SQL 语句
        System.out.println("name = "+name);
        String sql = "select name from commodity where name like "+"\"%"+name+"%\"";
        System.out.println("sql = "+sql);
        Connection conn = new JDBCUtil().getConnection();  // 通过辅助类创建 Connection 连接对象
        List<String> nameList = new ArrayList<>();  // 创建根据输入框的关键字筛选出的所有商品类型的List对象
        PreparedStatement pre = null;  // 创建预编译 SQL 语句 PreparedStatement 对象
        ResultSet resultSet = null;
        try {
            pre = conn.prepareStatement(sql);
            resultSet = pre.executeQuery();  // 创建筛选语句结果集对象 ResultSet
            System.out.println("====================");
            while (resultSet.next()) {  // 遍历结果集中的所有查询结果
                System.out.println("===="+resultSet.getString(1)+"===");
                nameList.add(resultSet.getString(1));  // 将获取到的数据添加入 typeList 集合中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {  // 在 try/catch 语句最后的 finally 语句中释放资源
            try {
                resultSet.close();
                pre.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String data = "[";
        for (int i = 0; i < nameList.size(); i ++) {
            if (i != nameList.size()-1) {
                String addString = "{\"name\":\""+nameList.get(i)+"\"},";
                data += addString;
            }else {
                String addString = "{\"name\":\""+nameList.get(i)+"\"}";
                data += addString;
            }
        }
        data += "]";
        System.out.println(data);
        response.getWriter().write(data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
