<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Settings || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="../partials/header.jsp"%>
</head>
<body>
	<main class="container">

		<c:if test="${message ne null}">
			<div class="alert alert-${messageType}">
				<strong>${message}</strong>
			</div>
		</c:if>

		<div class="jumbotron">
			<div id="update-t" class="container-fluid">
				<h3>Update Account</h3>
			</div>
			<form method="post" onsubmit="passwordValidate()"
				action="/updateuser">

				<input type="hidden" name="id" value="${employee.employeeId}">
				<input type="hidden" name="employeeId" value="${employeeToEdit.employeeId}">
				<label>Name:<input class="form-control" type="text"
					name="name" required minlength="3" value="${employeeToEdit.name}"></label>

				<div>
					<label>Username:<input class="form-control" type="text"
						name="username" required value="${employeeToEdit.username}"></label>
				</div>
				<div>
					<label>New Password:<input id="psw" class="form-control"
						type="password" name="password" onchange="validate()"></label>
					<div id="feedback" class="invalid-feedback">Password must be
						at least 4 characters long and include at least one numeric digit.</div>
				</div>
				<div>
					<label>Confirm New Password:<input class="form-control"
						type="password" name="passwordConfirm"></label>
				</div>
				<div class="add-div">Address:</div>
				<div>
					<label>Street:<input class="form-control" type="text"
						name="streetAddress" required value="${employeeToEdit.streetAddress}"></label>
				</div>
				<div>
					<label>City:<input class="form-control" type="text"
						name="city" required value="${employeeToEdit.city}"></label>
				</div>
				<div>
					<label>Zip code:<input class="form-control" type="text"
						name="zipCode" value="${employeeToEdit.zipCode}" required></label>
				</div>



				<label for="vehicleType">Vehicle Type</label> <select
					class="form-control" name="vehicleType" required>
					<c:forEach items="${vehicleTypes}" var="v">
						<c:choose>
							<c:when test="${v eq employeeToEdit.vehicleType}">
								<option value="${v}" selected>${v}</option>
							</c:when>
							<c:when test="${v ne employeeToEdit.vehicleType}">
								<option value="${v}">${v}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select> <label for="company">Company</label> <select class="form-control"
					name="company" required>
					<c:forEach items="${companies}" var="c">
						<c:choose>
							<c:when test="${c.name eq employeeToEdit.company.name}">
								<option value="${c.companyId}" selected>${c.name}</option>
							</c:when>
							<c:when test="${c.name ne employeeToEdit.company.name}">
								<option value="${c.companyId}">${c.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select> <label>Enter Current Password:<input class="form-control"
					type="password" name="current" required></label>
<div class="submit-d">
				<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
		<c:if test="${admin ne null}">
			<div class="container-fluid">
				<h3>Admin Access Panel</h3>
				<!-- Dropdown of employees to edit -->
				<h4>Select an Employee to Edit</h4>
				<form method="post" action="/updateadmin">

					<label for="employee">Employee</label> <select class="form-control"
						name="id" required>
						<c:forEach items="${employeeList}" var="e">
							<option value="${e.employeeId}">${e.name}</option>
						</c:forEach>
					</select>
					<button id="edit-btn" type="submit" class="btn btn-primary">Edit</button>

				</form>
				<!-- Dropdown of employees to pass admin access to -->
				<h4 class="s-h">Select a new Admin</h4>
				<div class="alert alert-danger">
					<strong>THIS WILL REMOVE YOU AS ADMIN</strong>
				</div>
				<form method="post" action="/newadmin">
					<label for="employee">Employee</label> <select class="form-control"
						name="id" required>
						<c:forEach items="${employeeList}" var="e">
							<option value="${e.employeeId}">${e.name}</option>
						</c:forEach>
					</select>
					<label>Enter Current Password:<input class="form-control"
					type="password" name="current" required></label>
					<div>
					<button id="change-btn" type="submit" class="btn btn-danger">CHANGE
						ADMIN</button>
					</div>
					

				</form>

			</div>
		</c:if>
	</main>
	<script src="/script.js"></script>


</body>
</html>

