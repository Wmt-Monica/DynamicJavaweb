<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/10
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="false" %>
<%-- contentType="text/html;charset=UTF-8": response.setContentType="text/html;charset=UTF-8"
     language="java": 这里是下面语言编写的语言类型，但是 jsp 文件先仅仅支持 java 语言，所以写不写无关紧要
     pageEncoding="UTF-8": 把jsp翻译成utf的java代码事的编码，所以必须和jsp文件本省的编码保持一致
     session="true": 不写该属性，默认是 session="true",表示jsp被创建默认创建session对象,当赋值为false时无法直接使用session对象，必须自己手动创建
 --%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
    在以下代码中创建了 List 子类对象 ArrayList 对象。所以我们需要引用 List 和 ArrayLsit 包，这里的 impprt 属性就是引用包的属性
--%>
<%@ page errorPage="index.jsp" %>
<%--
    在以下代码中如果发生错误，要进行错误页面的转页面的操作，在以上写入页面的路径，当当前jsp文件中如果发生错误，就会转到 index.jsp 页面中中去
--%>
<%@ page isErrorPage="false" %>
<%--
    设置该 jsp 页面是否为错误页面，默认不写时 false 值，此外在 web.xml 配置文件中 使用
    <error-page>
        <error-code>XXX</error-code> XXX:例如：404，500，302....
        <location>/XXX.XXX</location> /XXX.XXX为显示自定义错误页面的路径
    </error-page>
--%>
<%@ include file="index.jsp"%>
<%--
    用于页面的拼接，将 index.jsp 中的页面和当前页面显示在一块（页面的引入）
--%>
<html>
<head>
    <title>JSP脚本和诠释</title>
</head>
<body>
    <%
        /**
         * jsp 运行原理：
         *      jsp 的本质就是 servlet
         *      jsp在第一次被访问是就会被 Web 容器翻译成 servlet,在执行过程：
         *          第一次访问 ----> JSPTest.jsp ----> JSPTest_jsp.java ----> 编译运行
         *          被翻译后的 servlet 在 Tomcat 的 work 目录中可以找到
         */
    %>
    <%
        String content = "该形式是写Java代码部分";
        // 再写 java  代码中部分此为单行注解
        /*
            在写 java 代码中部分此为多行注解
         */
    %>
    <%= content%>  <!-- 该部分会被翻译成 service 方法内部 out.print() 中的参数部分  -->
    <%! String name = "石燔傻瓜";  // 此部分会被翻译成 service 的成员的内容%>
    <%= name%>
    <%
        HttpSession session = request.getSession();
        session.setAttribute("age","22");
        List<Integer> list = new ArrayList<>();
    %>

</body>
</html>
