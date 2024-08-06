<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Upsert ToDo</title>
		<link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"	rel="stylesheet">
		<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet" >
    	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="sp" %>
	</head>
	<%
		if (request.getSession(true).getAttribute("loggedinUserId") == null) {
			response.sendRedirect("/todo-login");
		}
	%>
	<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
		<a class="navbar-brand m-1" href="https://www.linkedin.com/in/nilachal-chakraborty-9607aa11a/">LinkedIn</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/add-ToDo?loggedinUserId=${loggedinUserId}">Add A ToDo</a></li>
				<li class="nav-item"><a class="nav-link" href="/welCome-ToDo?loggedinUserId=${loggedinUserId}">Your Todos</a></li>
			</ul>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/todo-login">Logout</a></li>
		</ul>	
	</nav>
	<p class="text-danger"><b>${ ErrorMessage }</b></p>
	<div class="container">
		<form:form method="post" modelAttribute="toDo" >
			<form:hidden path="toDoId"/>
			<form:hidden path="mappedUserId"/>
				<h3 style="background-color: aqua;" >:: Enter Your ToDo ::</h3>
				<fieldset class="mb-2" >		
					<form:label path="description">Description</form:label>
					<form:input class="text" type="text" path="description" required="required"/>
					<form:errors path="description" cssClass="text-danger"></form:errors>
				</fieldset>
				<fieldset class="mb-2" >			
					<form:label path="targetDate" >Target Date</form:label>
			    	<form:input path="targetDate" id="targetDateId" required="required"/>
			    	<form:errors path="targetDate" cssClass="text-danger"></form:errors>
				</fieldset>
				<fieldset>
					<form:label path="isCompleted">Is Completed</form:label>
					<form:checkbox path="isCompleted" />
				</fieldset>
				<fieldset class="mb-4" >
					<input type="submit" class="btn btn-success"  value="Submit ToDo" />
				</fieldset>
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
	<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript">
		$('#targetDateId').datepicker({
		    format: 'dd/mm/yyyy',
		    startDate: '-10d'
		});
	</script>
	</body>
</html>