<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello world!!</title>
</head>
<body>


	<p>
		<a href="${pageContext.request.contextPath}/notices">Show available notices</a>	
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/createnotice">Create a new notice</a>
	</p>
	<sec:authorize access="!isAuthenticated()">
		<p>
			<c:url var="loginPage" value="/login"/>
			<a href="${loginPage}">Login page</a>
		</p>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
	<p>
		<c:url var="logoutUrl" value="/logout" />
		<form action="${logoutUrl}"
			method="post">
		<input type="submit"
			value="Log out" />
		<input type="hidden"
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		</form>
	</p>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<p>
			<c:url var="adminPage" value="/admin"/>
			<a href="${adminPage}">Admin page</a>
		</p>
	</sec:authorize>
	

</body>
</html>