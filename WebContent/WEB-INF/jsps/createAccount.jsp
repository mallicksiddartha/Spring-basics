<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new Notice</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body>
	<div class="col-md-6 offset-md-3">
		<sf:form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/accountcreated" commandName="user">
			<fieldset>

				<!-- Form Name -->
				<legend>Create User</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Username</label>
					<div class="col-md-4">
						<sf:input id="username" path="username" name="username" type="text"
							class="form-control input-md" />
							<sf:errors path="username" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Email</label>
					<div class="col-md-5">
						<sf:input id="email" path="email" name="email" type="text" placeholder=""
							class="form-control input-md" />
						<sf:errors path="email" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Password</label>
					<div class="col-md-5">
						<sf:input id="password" path="password" name="password" type="text"
							class="form-control input-md" />
					</div>
				</div>
				
				<!-- Confirm Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Confirm password</label>
					<div class="col-md-5">
						<input id="confirmPassword" name="confirmPassword" type="text"
							class="form-control input-md" />
					</div>
				</div>
				
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="Submit"></label>
					<div class="col-md-4">
						<button id="Submit" name="Submit" class="btn btn-success">Create
							User</button>
					</div>
				</div>

			</fieldset>
		</sf:form>
	</div>
	<script type="text/javascript">
	
		var password = document.getElementById("password"), confirm_password = document
				.getElementById("confirmPassword");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password.setCustomValidity("<fmt:message key = 'Create.account.password.not.matched'/>");
			} else {
				confirm_password.setCustomValidity('');
			}
		}

		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>
</html>





