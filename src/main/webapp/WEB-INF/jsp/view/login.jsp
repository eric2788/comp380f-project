<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/25/2021
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@include file="../parts/meta.jsp"%>
</head>
<body>
<div class="container mt-5">
    <% if (request.getParameter("registered") != null) { %>
        <div class="alert alert-success">Successfully registered. please login.</div>
    <% }  else if (request.getParameter("error") != null) {%>
        <div class="alert alert-danger">Unknown username or password.</div>
    <% } %>
    <form action="login" method='POST'>
        <div class="mb-3">
            <label for="user" class="form-label">User</label>
            <input id="user" type='text' name='username' class="form-control">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input id="password" type='password' name='password' class="form-control"/>
        </div>
       <div class="form-check">
           <label for="checkbox" class="form-check-label">Remember Me</label>
           <input id="checkbox" type="checkbox" name="remember-me" class="form-check-input" />
       </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input name="submit" type="submit" value="Log In" class="btn btn-primary"/>
    </form>
    <a href="<c:url value="/register" />">or you can register</a>
</div>
</body>
</html>
