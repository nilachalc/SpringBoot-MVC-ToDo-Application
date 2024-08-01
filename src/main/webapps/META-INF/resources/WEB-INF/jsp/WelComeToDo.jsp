<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome ToDo User</title>
		<link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"
    		rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib uri="http://www.springframework.org/tags" prefix="sp" %>
	</head>
	<%
		if (request.getSession(true).getAttribute("loggedinUserId") == null) {
			response.sendRedirect("/todo-login");
		}
	%>
	<body>
		<div class="container" ><p class="lead"><sp:message code="todo.welcomePagecaption" /> ${ loggedinUserName }</p></div>
		<form id="WelComeMvc" name="WelComeMvcContainer" method="get" >
			<div class="container">
				<table class="table table-striped">
					<tr>
					  <th colspan="4" >:: All The ToDos are In The List Below ::</th>
					</tr>
					<tr>
					  	<td>
							<table class="table">
								<tr style="background-color: aqua;">
									<td><label class="label" >Sr. #</label></td>
									<td><label class="label" >Description</label></td>
									<td><label class="label" >Target Date</label></td>
									<td><label class="label" >Completion Status</label></td>
									<td align="right" ><label class="label" >Actions</label></td>
									<td></td>
								</tr>
								<c:forEach items="${allToDos}" var="toDo">
									<tr>
										<td><label>${toDo.toDoSerialNumber}</label></td>
										<td><label>${toDo.description}</label></td>
										<td><label>${toDo.targetDate}</label></td>
										<td><input disabled type="checkbox" 
								            <c:if test="${toDo.isCompleted}">
								                checked
								            </c:if>
								        ></td>
								        <td><a class="btn btn-primary" href="/update-ToDo?toDoId=${toDo.toDoId}">Update</a></td>
								        <td><a class="btn btn-danger" href="/delete-ToDo?toDoId=${toDo.toDoId}&loggedinUserId=${loggedinUserId}">Delete</a></td>
									</tr>		
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td>
							<a class="btn btn-success" href="/add-ToDo?loggedinUserId=${loggedinUserId}">Add</a>
						</td>
						<td align="right" colspan="100" >
							<a class="btn btn-danger" href="/todo-login">Logout</a>
						</td>
					</tr>
				</table>
			<p><p>
			</div>
		</form>
		<script src="/webjars/jquery/3.6.1/jquery.min.js"></script>
	    <script src="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
	</body>
</html>