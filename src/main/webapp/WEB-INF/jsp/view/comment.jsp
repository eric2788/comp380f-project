

<%@page contentType="text/html" pageEncoding="x-windows-950"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=x-windows-950">
        <title>comment</title>
    </head>
    <body>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="ticketForm">
            <form:textarea path="body" rows="5" cols="30" /><br /><br />
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
