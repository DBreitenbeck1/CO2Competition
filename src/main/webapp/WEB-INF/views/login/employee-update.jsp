<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Settings || Green on the Go</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="../partials/header.jsp"%>
<style>
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
}

input[class=name] {
	margin-left: 10px;
}

input[class=last] {
	margin-left: 12px;
}

input[class=user] {
	margin-left: 15px;
}

input[class=pass] {
	margin-left: 20px;
}

input[class=city] {
	margin-left: 59px;
	margin-top: 10px;
}

input[class=street] {
	margin-left: 45px;
}

input[class=zip] {
	margin-left: 25px;
}

.add-div {
	margin-top: 10px;
}

.sel-select {
	margin-top: 15px;
}
</style>
</head>
<body>
	<main class="container">

		<c:if test="${message ne null}">
			<div class="alert alert-${messageType}">
				<strong>${message}</strong>
			</div>
		</c:if>

		<form method="post">
			<section class="jumbotron">

				<div>
					<div class="container-fluid">
						<h3>Update Account</h3>
					</div>
					<label>Name:<input class="name" type="text" name="name"
						required minlength="3" value="${employee.name}"></label>
				</div>
				<div>
					<label>Username:<input class="user" type="text"
						name="username" required minlength="4"
						value="${employee.username}"></label>
				</div>
				<div>
					<label>New Password:<input class="pass" type="password"
						name="password" minlength="4" value=""></label>
				</div>
				<div>
					<label>Confirm New Password:<input class="pass"
						type="password" name="passwordConfirm" minlength="4"></label>
				</div>
				<div class="add-div">Address:</div>
				<div>
					<label>City:<input class="city" type="text" name="city"
						required minlength="4" value="${employee.city}"></label>
				</div>
				<div>
					<label>Street:<input class="street" type="text"
						name="streetAddress" required minlength="4"
						value="${employee.streetAddress}"></label>
				</div>
				<div>
					<label>Zip code:<input class="zip" type="text"
						name="zipCode" value="${employee.zipCode}" required minlength="4"></label>
				</div>



				<label for="vehicleType">Vehicle Type</label> <select
					class="form-control" name="vehicleType" required>
					<c:forEach items="${vehicleTypes}" var="v">
						<c:choose>
							<c:when test="${v eq employee.vehicleType}">
								<option value="${v}" selected>${v}</option>
							</c:when>
							<c:when test="${v ne employee.vehicleType}">
								<option value="${v}">${v}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select> <label for="company">Company</label> <select class="form-control"
					name="company" required>
					<c:forEach items="${companies}" var="c">
						<c:choose>
							<c:when test="${c.name eq employee.company.name}">
								<option value="${c.companyId}" selected>${c.name}</option>
							</c:when>
							<c:when test="${c.name ne employee.company.name}">
								<option value="${c.companyId}">${c.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select> <label>Enter Current Password:<input class="pass"
					type="password" name="current" required></label>

				<button type="submit" class="btn btn-primary">Submit</button>
		</form>

	</main>
</body>
</html>