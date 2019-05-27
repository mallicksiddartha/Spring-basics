<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('ADMIN')">
	<p>
		<c:url var="adminPage" value="/admin" />
		<a href="${adminPage}">Admin page</a>
	</p>
</sec:authorize>
<c:choose>
	<c:when test="${hasNotice }">
		<c:url var="editNotice" value="/createnotice"></c:url>
		<a href="${editNotice}">Edit or Delete current notices</a>
	</c:when>
	<c:otherwise>
	<c:url var="createNotice" value="/createnotice"></c:url>
	<a href="${createNotice}">Create a new notice</a>
	</c:otherwise>
</c:choose>
<div class="col-md-12">
	<table class="table table-striped table-bordered table-hover">
		<caption>List of notices</caption>
		<thead class="thead-light">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Name</th>
				<th scope="col">Email</th>
				<th scope="col">Text</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="notice" items="${notices}" varStatus="noticeLoop">
				<tr>
					<th scope="row"><c:out value="${noticeLoop.count}"></c:out></th>
					<td><c:out value="${notice.user.name}"></c:out></td>
					<td><c:out value="${notice.user.email}"></c:out></td>
					<td><c:out value="${notice.text}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
