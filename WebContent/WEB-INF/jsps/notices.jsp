<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notices</title>
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
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
						<td><c:out value="${notice.getName()}"></c:out></td>
						<td><c:out value="${notice.getEmail()}"></c:out></td>
						<td><c:out value="${notice.getText()}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>