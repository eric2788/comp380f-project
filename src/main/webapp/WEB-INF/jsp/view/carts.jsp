<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/9/2021
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Carts</title>
    <%@include file="../parts/meta.jsp" %>
</head>
<body>
<%@include file="../parts/header.jsp" %>

<div class="container">
    <ul class="list-group">
        <c:forEach var="cart" items="${carts}">
            <li class="list-group-item">
                <!-- <a href="addNewProduct.jsp">Add New Product</a> -->
                <a href="<c:url value="/item/${cart.item.id}"/> ">${cart.item.name}</a>
                <a href="<c:url value="/order/delete/${cart.id}"/> ">Delete</a>
            </li>
        </c:forEach>
    </ul>
    <a href="#">Checkout</a>
</div>
</body>
</html>
