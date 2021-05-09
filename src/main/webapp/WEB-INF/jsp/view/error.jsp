<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/9/2021
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error: ${cls}</title>
    <%@include file="../parts/meta.jsp"%>
</head>
<body>
    <div class="container mt-5">
        <div class="alert alert-danger">Error! ${message}</div>
        <button class="btn btn-primary" onclick="history.back()">Back</button>
    </div>
</body>
</html>
