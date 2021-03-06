<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/">Notices</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/">Home <span
					class="sr-only">(current)</span>
			</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<sec:authorize access="!isAuthenticated()">
					<c:url var="loginPage" value="/login" />
					<a href="${loginPage}">Login page</a>
				</sec:authorize>
			</li>
			<li>
				<sec:authorize access="isAuthenticated()">
					<c:url var="logoutUrl" value="/logout" />
					<form action="${logoutUrl}" method="post">
						<input type="submit" value="Log out" /> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</sec:authorize>
			</li>
		</ul>
	</div>
</nav>