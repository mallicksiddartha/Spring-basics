<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="col-md-6 offset-md-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="notice">
		<fieldset>

			<!-- Form Name -->
			<legend>Create Notice</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="name">Name</label>
				<div class="col-md-4">
					<sf:input id="name" path="name" name="name" type="text"
						class="form-control input-md" />
					<sf:errors path="name" cssClass="alert-danger"></sf:errors>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-5">
					<sf:input id="email" path="email" name="email" type="text"
						placeholder="" class="form-control input-md" />
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Notice</label>
				<div class="col-md-4">
					<sf:textarea class="form-control" id="text" path="text" name="text"></sf:textarea>
					<sf:errors path="text" cssClass="alert-danger"></sf:errors>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="Submit"></label>
				<div class="col-md-4">
					<button id="Submit" name="Submit" class="btn btn-success">Create
						Notice</button>
				</div>
			</div>

		</fieldset>
	</sf:form>
</div>

