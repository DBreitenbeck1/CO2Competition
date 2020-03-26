<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<!--  <link rel="stylesheet" href="/style.css" /> -->
<%@ include file="../partials/header.jsp"%>
</head>
<body>
	<main class="container">
			<c:if test="${message ne null}">
			<div class="alert alert-${messageType}">
				<strong>${message}</strong>
			</div>
		</c:if>
	
		<h3>Register for Green on the Go</h3>
			
		<form method="post" onsubmit="passwordValidate()">
			<section class="jumbotron">
		<h4 style="color:red;">${valid }</h4>
				<div>
					<label>First Name:<input class="form-control" type="text"
						name="name" required minlength="3"></label>
				</div>
				<div>
					<label>Last Name:<input class="form-control" type="text"
						name="lastName" required></label>
				</div>
				<div>
					<label>Username:<input class="form-control" type="text"
						name="username" required></label>
				</div>
				<div>
					<label>Password:<input id="psw" class="form-control"
						type="password" name="password" onchange="validate()" required></label>
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
						name="streetAddress" required></label>
				</div>
				<div>
					<label>City:<input class="form-control" type="text"
						name="city" required></label>
				</div>
				<div>
					<label>Zip code:<input class="form-control" type="text"
						name="zipCode" required></label>
				</div>

				<select name="vehicleType" class="form-control" required>
					<c:forEach var="v" items="${vehicleTypes}">
						<option value="${v }">${v }</option>
					</c:forEach>


				</select> <select name="company" class="form-control" required>

					<c:forEach var="co" items="${companies }">
						<option value="${co.companyId }">${co.name }</option>
					</c:forEach>
				</select>
			</section>
			<div>




				<input type="hidden" name="admin" value="0">


			</div>



			<button type="submit" class="btn btn-primary">Submit</button>
		</form>


	</main>


	<script src="/script.js"></script>
</body>
</html>