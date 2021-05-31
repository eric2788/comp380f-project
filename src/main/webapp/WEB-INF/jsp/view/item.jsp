<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/9/2021
  Time: 1:21 PM
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
    <% if (request.getParameter("addedCart") != null) {%>
        <div class="alert alert-success">Added to cart.</div>
    <% } %>
    <form:form method="post" modelAttribute="item">
        <form:hidden path="id" readonly="true" />
        <div class="mb-3 row">
            <form:label path="name" cssClass="col-sm-2 col-form-label">Name: </form:label>
            <div class="col-sm-10">
                <form:input cssClass="form-control" path="name" readonly="${mode == 'view'}"/>
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
            <label for="file" class="col-sm-2 col-form-label">Photo: </label>
            <div class="col-sm-10">
                <c:choose>
                    <c:when test="${empty item.photos}">
                        <small>No Photo</small>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="photo" items="${item.photos}">
                            <img id="file" alt="photo" src="data:image/png;base64, ${photo.base64}"/>
                            <br />
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="mb-3 row">
            <form:label path="available" cssClass="form-check-label">Available: </form:label>
            <div class="col-sm-10">
                <form:checkbox path="available" cssClass="form-check-input" disabled="${mode == 'view'}"/>
            </div>
            <form:errors path="available" cssClass="ui-state-error-text"/>
        </div>

        <br />

        <c:if test="${mode != 'view'}">
            <input type="submit" name="update" value="Update" />
        </c:if>

    </form:form>


        <c:if test="${mode == 'view'}">
            <security:authorize access="isAuthenticated()">
                <a href="<c:url value="/order/add/${item.id}"/>"><button class="btn btn-primary">Add To Cart</button></a>
                <a href="<c:url value="/comment/add/${item.id}"/>"><button class="btn btn-success">Comment</button></a>
            </security:authorize>

            <h2>Comments</h2>
            <ul class="list-group list-group-flush">
                <c:forEach var="comment" items="${item.comments}">
                    <li class="list-group-item">
                            ${comment.account.username}: ${comment.body}
                             <a class="ml-3" href="<c:url value="/comment/delete/${comment.id}?itemid=${item.id}" />">Delete</a>
                    </li>
                </c:forEach>
                <c:if test="${empty item.comments}">
                    list is empty
                </c:if>
            </ul>
        </c:if>
</div>

</body>
</html>
