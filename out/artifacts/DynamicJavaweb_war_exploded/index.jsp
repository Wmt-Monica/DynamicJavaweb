<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/1/28
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <%--  此处表单的 action 的参数填写 <url-pattern> 中具体的参数--%>
    <form action="introduction" method="post">
        <input type="submit" value="点击一下">
    </form>

    <form action="example" method="post">
        <input type="submit" value="点击一下">
    </form>

    <form action="example2" method="get">
        <input type="submit" value="提交">
    </form>

    <form action="servletContext" method="post">
      <input type="submit" value="获取 a b c.txt 的绝对路径">
    </form>

    <form action="header" method="post">
        <input type="submit" value="setHeader">
    </form>
  </body>
</html>