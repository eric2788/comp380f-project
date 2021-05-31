<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/31/2021
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Photo for ${item.name}</title>
</head>
<body>
    <h2>Upload Photo for ${item.name} (png)</h2>
    <c:forEach var="photo" items="${item.photos}">
        <img id="file" alt="photo" src="data:image/png;base64, ${photo.base64}"/>
        <br />
        <a href="<c:url value="/item/delete/${photo.id}/photo?itemid=${item.id}" />"><button>Delete This Photo</button></a>
        <br />
        <hr />
    </c:forEach>
    <form method="post" enctype="multipart/form-data">
        <input type="file" name="files" multiple accept="image/png">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <br />
        <button>Save</button>
    </form>
</body>
</html>
