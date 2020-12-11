<%@page import="it.lea.controllers.CheckLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="red">

<% String m1 = (String) request.getAttribute("palla");
   out.println(m1);
%>
</body>
</html>