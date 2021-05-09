<%-- 
    Document   : addNewProductAction
    Created on : 2021�~5��8��, �U��10:12:39
    Author     : Keith
--%>

<%@page contentType="text/html" pageEncoding="x-windows-950"%>
<%@page import="java.sql.*"%>
<%
    String id=request.getParameter("id");
    String name=request.getParameter("name");
    String category=request.getParameter("description");
    String price=request.getParameter("price");
    String available=request.getParameter("available");

   

%>
