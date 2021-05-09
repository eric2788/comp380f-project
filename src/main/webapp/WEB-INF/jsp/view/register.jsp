<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/9/2021
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <%@include file="../parts/meta.jsp"%>
</head>
<body>
<div class="container mt-5">
    <form:form method="post" modelAttribute="user" action="register">
        <div class="mb-3 row">
            <form:label path="username" cssClass="col-sm-2 col-form-label">Username: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="username" required="true"/>
            </div>
            <form:errors path="username" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="fullname" cssClass="col-sm-2 col-form-label">FullName: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="fullname"/>
            </div>
            <form:errors path="fullname" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="phone" cssClass="col-sm-2 col-form-label">Phone: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="phone" maxlength="8" max="99999999" type="number"/>
            </div>
            <form:errors path="phone" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="address" cssClass="col-sm-2 col-form-label">Address: </form:label>
            <div class="col-sm-10">
                <form:textarea cssClass="form-control" path="address"/>
            </div>
            <form:errors path="address" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="password" cssClass="col-sm-2 col-form-label">Password: </form:label>
            <div class="col-sm-10">
                <form:password cssClass="form-control" path="password" required="true"/>
            </div>
            <form:errors path="password" cssClass="ui-state-error-text"/>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <br />

        <input type="submit" name="register" value="Register" />

    </form:form>
    <a href="<c:url value="/login" />">already registered ? login</a>
</div>

</body>
</html>
