<%@ page import="cn.dreamplume.jsp.People" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: 翊
  Date: 2021/2/13
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>jsp的Jstl标签库</title>
</head>
<body>
    <%--  EL中可以做运算  --%>
    <%--  c:if中间是判断的语句执行  --%>
    <%
        String age = "";
    %>
    <c:if test="${empty age}">
        <span>放在 c:if test 语句中的 EL 语句只能是 empty 判空语句否则会报 500 服务器内部错误</span><br>
        <span>此为 c:if 语句的代码块执行....</span><br>
        <span>当字符串中赋值为空字符串，则在 EL empty 判空语句中是为 null 的结果</span><br>
    </c:if>
    <hr>
    <c:if test="true">
        <span>c:if  语句中只有当 test 中的值为字符串 true 才会执行其中的语句块</span><br>
        <span>1<2 ? 1 : 2 的结果：</span>
        ${1<2 ? 1 : 2}<br>
        <span>EL 语句中还可以完成三目运算</span><br>
    </c:if>
    <hr>


    <%--
        for(int i = 0; i <= 5; i ++) {
            System.out.print(i);
        }
     --%>
    <c:forEach begin="0" end="5" var="i" >
        ${i}
    </c:forEach><br>
    <hr>

    <%
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 5; i ++) {
            list.add(i);
        }
        request.setAttribute("list",list);
    %>
    <%--
        for(int  i : list) {
            System.out.print(i);
        }
    --%>
    <c:forEach items="${requestScope.list}" var="i">
        ${i}
    </c:forEach><br>
    <hr>


    <%--
        示例：
        1）：遍历List<String>的值
        2）：遍历List<People>的值
        3）：遍历Map<String, String>的值
        4）：遍历Map<String, People>的值
    --%>
    <%
        List<String> strList = new ArrayList<>();
        for (int i = 1; i <= 5; i ++) {
            strList.add(""+i);
        }
        List<People> peoList = new ArrayList<>();
        for (int i = 1; i <= 5; i ++) {
            peoList.add(new People(""+i, "Hahn"+i));
        }
        Map<String, String> strMap = new HashMap<>();
        for (int i = 1; i <= 5; i ++) {
            strMap.put(""+i, "Monica"+i);
        }
        Map<String, People> peoMap = new HashMap<>();
        for (int i = 1; i <=5; i ++) {
            peoMap.put(""+i, new People(""+i, "Love"+i));
        }
        request.setAttribute("strList",strList);
        request.setAttribute("peoList",peoList);
        request.setAttribute("strMap",strMap);
        request.setAttribute("peoMap",peoMap);
    %>

    <c:forEach items="${requestScope.strList}" var="str">
        ${str}
    </c:forEach><br>
    <hr>
    <c:forEach items="${requestScope.peoList}" var="peo">
        <span>people.id ----> </span> ${peo.id} &nbsp;&nbsp;&nbsp;
        <span>people.name ----> </span> ${peo.name} <br>
    </c:forEach>
    <hr>
    <c:forEach items="${requestScope.strMap}" var="str">
        <span>str.key ----> </span> ${str.key} &nbsp;&nbsp;&nbsp;
        <span>str.value ----> </span> ${str.value} <br>
    </c:forEach>
    <hr>
    <c:forEach items="${requestScope.peoMap}" var="peo">
        <span>peo.key ----> </span> ${peo.key} &nbsp;&nbsp;&nbsp;
        <span>peo.value.id ----> </span> ${peo.value.id} &nbsp;&nbsp;&nbsp;
        <span>peo.value.name ----> </span> ${peo.value.name} <br>
    </c:forEach>
</body>
</html>
