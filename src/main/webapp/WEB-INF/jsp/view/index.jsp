<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/23/2021
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <%@include file="../parts/meta.jsp" %>
</head>
<body>
<%@include file="../parts/header.jsp" %>
<div class="container">
    <ul class="list-group">
        <c:forEach var="item" items="${items}">
            <li class="list-group-item">
                    <a href="<c:url value="/shopitem/view?id=${item.name}"/> ">${item.name}</a>
                    <a href="<c:url value="/shopitem/edit?id=${item.name}"/> ">Edit</a>
                <a href="<c:url value="/shopitem/delete?id=${item.name}"/> ">Delete</a>
            </li>
        </c:forEach>
    </ul>
    <security:authorize access="hasRole('ADMIN')" >
        <a href="<c:url value="/shopitem/add" />"><button class="btn btn-primary">Add</button></a>
    </security:authorize>
</div>
</body>
</html>
