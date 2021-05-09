<%@ page import="ouhk.comps380f.dao.Account" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/4/2021
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>User List</title>
    <%@include file="../parts/meta.jsp" %>
</head>
<body>
<%@include file="../parts/header.jsp" %>
<div class="container">
    <ul class="list-group">
        <c:forEach var="user" items="${users}">
            <li class="list-group-item">
                    ${user.username} (${user.admin ? 'Admin': 'User'})
                        <a href="<c:url value="/user/${user.username}"/>">View</a>
                        <a href="<c:url value="/user/edit/${user.username}"/>">Edit</a>
                        <a href="<c:url value="/user/delete/${user.username}"/>">Delete</a>
            </li>
        </c:forEach>
    </ul>
    <security:authorize access="hasAnyAuthority('ADMIN')" >
        <a href="<c:url value="/user/add" />"><button class="btn btn-primary">Add</button></a>
    </security:authorize>
</div>
</body>
</html>
