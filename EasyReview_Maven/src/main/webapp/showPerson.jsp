<%@page import="com.lra.model.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="cyan">

<%

Person m1 = (Person)request.getAttribute("person");
out.println(m1); 

%>

</body>
</html>