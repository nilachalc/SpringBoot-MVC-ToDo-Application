<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>LogIn ToDo User</title>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="sp" %>
		<link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
	<p class="text-danger"><b>${ ErrorMessage }</b></p>
	<div class="container">
		<form action="/todo-login?lang=de" method="post">
		<table class="table table-striped" >
			<tr>
			  <th colspan="2" class="lead" style="background-color: aqua;">:: <sp:message code="todo.loginPageCaption" /> :: </th>
			</tr>
			<tr>
				<td><label>Name</label></td>
				<td><input class="text" type="text" name="name" /></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input class="password" type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" ><input type="submit" /></td>
			</tr>
		</table>
		</form>
	</div>
	<script src="/webjars/jquery/3.6.1/jquery.min.js"></script>
	<script src="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
	</body>
</html>