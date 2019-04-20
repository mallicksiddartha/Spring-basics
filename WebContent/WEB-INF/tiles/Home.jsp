<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<p>
	<a href="${pageContext.request.contextPath}/notices">Show available
		notices</a>
</p>
<p>
	<a href="${pageContext.request.contextPath}/createnotice">Create a
		new notice</a>
</p>

<sec:authorize access="hasRole('ADMIN')">
	<p>
		<c:url var="adminPage" value="/admin" />
		<a href="${adminPage}">Admin page</a>
	</p>
</sec:authorize>
