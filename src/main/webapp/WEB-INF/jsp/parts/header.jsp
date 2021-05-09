<%--
  Created by IntelliJ IDEA.
  User: Eric Lam
  Date: 4/23/2021
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/" />">Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav" style="text-align: right; justify-items: right; justify-content: right">
            <ul class="navbar-nav text-right">
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/order" />">My Carts</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/profile" />">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/logout"/>">Logout</a>
                    </li>
                    <security:authorize access="hasAnyAuthority('ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/user"/>">Manage User</a>
                        </li>
                    </security:authorize>
                </security:authorize>
                <security:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/login" />" >Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/register" />" >Register</a>
                    </li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
