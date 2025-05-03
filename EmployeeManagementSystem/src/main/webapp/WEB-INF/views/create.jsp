<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ADD EMPLOYEE DETAILS HERE ...</h1>
	<br>
	<br>
	
	<form:form action="save" modelAttribute="employee">
	
	<form:label path="id">EMPLOYEE ID:</form:label>
	<form:input path="id"/>
	<br>
	<br>
	<form:label path="name">EMPLOYEE NAME:</form:label>
	<form:input path="name"/>
	<br>
	<br>
	<form:label path="phone">PHONE NUMBER:</form:label>
	<form:input path="phone"/>
	<br>
	<br>
	
	<input type="submit" value="ADD EMPLOYEE">
	</form:form>
	
	
	
	
	
	
	</body>
</html>