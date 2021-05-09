<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/9/2021
  Time: 1:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${mode == 'add' ? 'Add' : mode == 'view' ? 'View' : 'Update'} User</title>
    <%@include file="../parts/meta.jsp"%>
</head>
<body>
<%@include file="../parts/header.jsp"%>

<div class="container">
    <form:form method="post" modelAttribute="item" action="update" enctype="multipart/form-data">

        <div class="mb-3 row">
            <form:label path="name" cssClass="col-sm-2 col-form-label">Name: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="'form-control'" path="name" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="name" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="description" cssClass="col-sm-2 col-form-label">Description: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="description" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="description" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="price" cssClass="col-sm-2 col-form-label">Price: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="price" step="0.1" max="9999" min="0" type="number" readonly="${mode == 'view'}"/>
            </div>
            <form:errors path="price" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="photo" cssClass="col-sm-2 col-form-label">Photo: </form:label>
            <div class="col-sm-10">
                <c:choose>
                    <c:when test="${mode == 'view'}">
                        <img alt="photo" src="data:image/png;base64, ${item.photo}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="file" name="files" accept="image/jpg, image/png" multiple="multiple"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <form:errors path="photo" cssClass="ui-state-error-text"/>
        </div>

        <div class="mb-3 row">
            <form:label path="available" cssClass="form-check-label">Available: </form:label>
            <div class="col-sm-10">
                <form:checkbox path="available" cssClass="form-check-input" disabled="${mode == 'view'}"/>
            </div>
            <form:errors path="available" cssClass="ui-state-error-text"/>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <br />

        <c:if test="${mode != 'view'}">
            <input type="submit" name="update" value="Update" />
        </c:if>

    </form:form>
</div>

</body>
</html>
