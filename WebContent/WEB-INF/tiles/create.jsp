<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-6 offset-md-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="notice">
		<fieldset>
			<sf:input type="hidden" name="id" path="id" />
			<!-- Form Name -->
			<legend>Create or Update Notice</legend>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Notice</label>
				<div class="col-md-4">
					<sf:textarea class="form-control" id="text" path="text" name="text"></sf:textarea>
					<sf:errors path="text" cssClass="alert-danger"></sf:errors>
				</div>
			</div>

			<!--Save Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="Submit"></label>
				<div class="col-md-4">
					<button id="Submit" name="Submit" class="btn btn-success">Save
						Notice</button>
				</div>
			</div>

			<!--Delete Button -->
			<c:if test="${notice.getId() != 0 }">
				<div class="form-group">
					<label class="col-md-4 control-label" for="Submit"></label>
					<div class="col-md-4">
						<button id="delete" name="delete" class="btn btn-danger">Delete
							Notice</button>
					</div>
				</div>
			</c:if>

		</fieldset>
	</sf:form>
</div>

