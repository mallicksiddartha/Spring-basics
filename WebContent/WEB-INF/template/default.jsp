<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.5">
<title><tiles:getAsString name="title"></tiles:getAsString></title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.3/examples/starter-template/">

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/notices-main.css" rel="stylesheet">
</head>
<body>
	<div>
		<tiles:insertAttribute name="page-header"></tiles:insertAttribute>
	</div>
	<div class="container">
		<tiles:insertAttribute name="page-content"></tiles:insertAttribute>
	</div>
	<div>
		<tiles:insertAttribute name="page-footer"></tiles:insertAttribute>
	</div>
</body>
</html>