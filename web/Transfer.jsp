<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/16
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>转账</title>
</head>
<body>
    <c:if test="${requestScope.flag}">
        <h1>
            转账成功！
        </h1>
    </c:if>
    <c:if test="!${requestScope.flag}">
        <h1>
            转账失败
        </h1>
    </c:if>
    <h3>银行用户及其余额</h3>
    <c:forEach items="${requestScope.peopleList}" var="people">
        <span>用户名：${people.userName}</span>
        <span>余额：${people.balance}</span><br>
    </c:forEach>
</body>
</html>
