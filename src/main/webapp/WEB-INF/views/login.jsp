<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="partials/header.jsp"%>
<style>

input[type=text] {
	border: 2px solid brown;
	border-radius: 4px;
	left: 230px;
	bottom: 330px;
	color: black;
	margin-left: 15px;
}

input[type=password] {
	border: 2px solid brown;
	border-radius: 4px;
	left: 230px;
	bottom: 330px;
	color: black;
	margin-left: 20px;
}

.control-label {
	margin-top: 50px;
}

.submit-div {
	margin-top: 30px;
	margin-left: 16px;
}

h1 {
	margin-top: 15px;
}
</style>
</head>
<body>

	<main class="container">
		<form method="post">
			<section class="container">
				<h1>Welcome to Green on the Go</h1>
			</section>
			<section class="container-fluid">
				<c:if test="${message ne null}">
					<div class="alert alert-${messageType}">
						<strong>${message}</strong>
					</div>
				</c:if>

				<div>
					<label class="control-label">Username:<input type="text"
						name="username" required></label>
				</div>
				<div>
					<label>Password:<input type="password" name="password"
						required></label>
				</div>

			</section>
			<div class="submit-div">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form>

		<center>
			<img src="carpool.png" />
		</center>
	</main>
</body>
</html>