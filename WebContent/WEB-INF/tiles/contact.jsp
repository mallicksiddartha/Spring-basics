<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

	<div class="col-md-6 offset-md-3">
		<sf:form class="form-horizontal" method="post" commandName="message">
			<fieldset>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				<input type="hidden" name="_eventId" value="send"/>
				<!-- Form Name -->
				<legend>Send Message</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Your Name</label>
					<div class="col-md-4">
						<sf:input id="name" path="name" name="name" type="text" value="${fromName}"
							class="form-control input-md" />
							<sf:errors path="name" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
					<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Your Email</label>
					<div class="col-md-4">
						<sf:input id="email" path="email" name="email" type="text" value="${fromEmail}"
							class="form-control input-md" />
							<sf:errors path="email" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
					<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="subject">Subject</label>
					<div class="col-md-4">
						<sf:input id="subject" path="subject" name="subject" type="text"
							class="form-control input-md" />
							<sf:errors path="subject" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="content">Content</label>
					<div class="col-md-4">
						<sf:textarea id="content" path="content" name="content" type="text"
							class="form-control input-md" />
							<sf:errors path="content" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="Submit"></label>
					<div class="col-md-4">
						<button id="Submit" name="Submit" class="btn btn-success">Send</button>
					</div>
				</div>

			</fieldset>
		</sf:form>
	</div>