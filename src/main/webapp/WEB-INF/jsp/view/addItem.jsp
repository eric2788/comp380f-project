<%@page contentType="text/html" pageEncoding="x-windows-950"%>
<%@page import=""  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=x-windows-950">
        <title>${mode == 'add' ? 'Add' : mode == 'view' ? 'View' : 'Update'} Item</title>
        <%@include file="../parts/meta.jsp"%>

    </head>
    <body>
        <%@include file="../parts/header.jsp"%>
        <%
            String msg=request.getparameter("msg");
            if("done".equals(msg))
            {
        %>
        <h3 class="alert"> Product added!</h3>
        /%>} %>

        <%
            String msg=request.getparameter("msg");
            if("wrong".equals(msg))
            {
        %>
        <h3 class="alert"> Error occur! Please try again!</h3>
        /%>} %>

        <form action="addNewProductAction.jsp" method="post">
            <h3 style="color: black;"> Product ID:<% out.println(id);%> </h3>
            <input type="hidden" name = "id" value="<% out.println(id);%>">

            <div class = "left-div">
                <h3>Enter Name</h3>
                <input class ="input-style"type="text" name="name" placeholder="Enter Name"required>
                <hr>
            </div>

            <div class="right-div">
                <h3>Enter Description</h3>
                <input class ="input-style"type="text" name="description" placeholder="Enter Description"required>
                <hr>
            </div>    

            <div class="left-div">
                <h3>Enter Price</h3>
                <input class ="input-style"type="number" name="price" placeholder="Enter Price"required>
                <hr>
            </div>   

            <div class="right-div">
                <h3>Available</h3>
                <select class="input-style" name="available">
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                </select>    
                <hr>
            </div>   

            <button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>

        </form>
    </body>
</html>
