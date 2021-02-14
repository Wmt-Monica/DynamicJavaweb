<%@ page import="cn.dreamplume.MVC.domain.People" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/13
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>风景展示</title>
</head>
<style>
    .all {
        width: 100%;
        height: auto;
        float: left;
        background-color: aquamarine;
    }
    .row {
        float: left;
        width: 90%;
        margin-left: 5%;
        border: solid cornflowerblue 1px;
    }
    .col {
        width: 23%;
        float: left;
        padding: 1%;
    }
    .img img {
        float: left;
        width: 100%;
    }
    .contain {
        float: left;
        width: 100%;
        background-color: azure;
    }
</style>
<body>

    <%
        List<People> peopleList = (List<People>) request.getAttribute("peopleList");
    %>
    <%--使用javaEE三层架构+MVC创建风景展示图--%>
    <div class="all">
        <c:forEach begin="0" end="4" var="i">
            <div class="row">
                <c:forEach items="${requestScope.peopleList}" var="people">
                    <div class="col">
                        <div class="img">
                            <%-- 设置路径的时候使用 EL 语句的数据要使用 toString() 方法来转成字符串对象 --%>
                            <img src=${people.picture.toString()}>
                        </div>
                        <div class="contain">
                            姓名：${people.name}<br>
                            年龄：${people.age}<br>
                            职业：${people.position}<br>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</body>
</html>
