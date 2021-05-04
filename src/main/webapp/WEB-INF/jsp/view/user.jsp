<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/4/2021
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${mode == 'add' ? 'Add' : mode == 'view' ? 'View' : 'Update'} User</title>
    <%@include file="../parts/meta.jsp"%>
</head>
<body>
<%@include file="../parts/header.jsp"%>

<div class="container">
    <form:form method="post" modelAttribute="user" action="update">
        <div class="mb-3 row">
            <form:label path="username" cssClass="col-sm-2 col-form-label">Username: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="${mode == 'add' ? 'form-control' : 'form-control-plaintext'}" path="username" readonly="${mode != 'add'}"/>
            </div>
            <form:errors path="username" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="fullname" cssClass="col-sm-2 col-form-label">FullName: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="fullname" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="fullname" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="phone" cssClass="col-sm-2 col-form-label">Phone: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="phone" maxlength="8" max="99999999" type="number" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="phone" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="address" cssClass="col-sm-2 col-form-label">Address: </form:label>
            <div class="col-sm-10">
                <form:textarea cssClass="form-control" path="address" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="address" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="password" cssClass="col-sm-2 col-form-label">Password: </form:label>
            <div class="col-sm-10">
                <form:password cssClass="form-control" path="password" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="password" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="admin" cssClass="form-check-label">Is Admin: </form:label>
            <div class="col-sm-10">
                <form:checkbox path="admin" cssClass="form-check-input" disabled="${mode == 'view'}"/>
            </div>
            <form:errors path="admin" cssClass="ui-state-error-text"/>
        </div>

        <br />

        <c:if test="${mode != 'view'}">
            <input type="submit" name="update" value="Update" />
        </c:if>

    </form:form>
</div>

</body>
</html>
