<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		document.f.username.focus();
	});
</script>
<form class="form-horizontal" name='f'
	action='${pageContext.request.contextPath}/login' method='POST'>
	<fieldset>

		<!-- Form Name -->
		<legend>Login</legend>

		<div class="form-group">
			<c:if test="${param.error != null}">
				<p class="col-md-4 alert-danger">Invalid Username or password.
				<p>
			</c:if>
		</div>
		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="username">Username</label>
			<div class="col-md-4">
				<input id="username" name="username" type="text" placeholder=""
					class="form-control input-md">

			</div>
		</div>

		<!-- Password input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="password">Password</label>
			<div class="col-md-4">
				<input id="password" name="password" type="password" placeholder=""
					class="form-control input-md">

			</div>
		</div>

		<!-- Password input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="password">Remember
				me</label>
			<div class="col-md-4">
				<input id="remember-me" name="remember-me" type="checkbox"
					checked="checked" class="form-control input-md">

			</div>
		</div>
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="submit"></label>
			<div class="col-md-4">
				<button id="submit" name="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</fieldset>
</form>
<p>
	<a href="<c:url value='/createaccount' />"> Create new account</a>
</p>

