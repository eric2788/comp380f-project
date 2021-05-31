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
    <title>Fast Food Ordering System</title>
    <%@include file="../parts/meta.jsp" %>
</head>
<body>
<%@include file="../parts/header.jsp" %>
<div class="container">
    <ul class="list-group">
        <c:forEach var="item" items="${items}">
            <li class="list-group-item">
                    <!-- <a href="addNewProduct.jsp">Add New Product</a> -->
                    <a href="<c:url value="/item/${item.id}"/> ">${item.name}</a>
                    <security:authorize access="hasAnyAuthority('ADMIN')">
                        <a href="<c:url value="/item/edit/${item.id}"/> ">Edit</a>
                        <a href="<c:url value="/item/delete/${item.id}"/> ">Delete</a>
                        <a href="<c:url value="/item/edit/${item.id}/photo"/>">Manage Photo</a>
                    </security:authorize>
            </li>
        </c:forEach>
    </ul>
    <security:authorize access="hasAnyAuthority('ADMIN')" >
        <a href="<c:url value="/item/add" />"><button class="btn btn-primary">Add</button></a>
    </security:authorize>
</div>
</body>
</html>
