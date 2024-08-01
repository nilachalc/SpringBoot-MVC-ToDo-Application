<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Upsert ToDo</title>
		<link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"	rel="stylesheet">
    	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="sp" %>
	</head>
	<%
		if (request.getSession(true).getAttribute("loggedinUserId") == null) {
			response.sendRedirect("/todo-login");
		}
	%>
	<body>
	<p class="text-danger"><b>${ ErrorMessage }</b></p>
	<div class="container">
		<form:form method="post" modelAttribute="toDo" >
			<form:hidden path="toDoId"/>
			<form:hidden path="mappedUserId"/>
			<table class="table table-striped" >
				<tr style="background-color: aqua;">
				  <th colspan="2" class="lead" >:: Enter Your ToDo ::</th>
				</tr>
				<tr>
					<td><form:label path="description">Description</form:label></td>
					<td>
						<form:input class="text" type="text" path="description" required="required"/>
						<form:errors path="description" cssClass="text-danger"></form:errors>
					</td>
				</tr>
				<tr>
					<td><form:label path="targetDate" >Target Date</form:label></td>
					<td>
						<jsp:include page="../jsp/DatePicker.html"></jsp:include>
				    	<form:input path="targetDate" id="targetDateId" type= "hidden" class="text"/>
				    	<form:errors path="targetDate" cssClass="text-danger"></form:errors>
					</td>
				</tr>
				<tr>
					<td><form:label path="isCompleted">Is Completed</form:label></td>
					<td>
						<form:checkbox path="isCompleted" />
					</td>
				</tr>
				<tr>
					<td colspan="2" ><input type="submit" class="btn btn-success"  value="Submit ToDo" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<script>
		updateValueReverse();
		function updateValueReverse() {
			var targetDateValue = document.getElementById('targetDateId').value;
			document.getElementById('dateInput').value = targetDateValue; 
	    } 
	
		function updateValue() {
            var dateInputValue = document.getElementById('dateInput').value;
            document.getElementById('targetDateId').value = dateInputValue;
        } 
    </script>
	<script src="/webjars/jquery/3.6.1/jquery.min.js"></script>
	<script src="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
	</body>
</html>