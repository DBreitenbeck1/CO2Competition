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
<%@ include file="../partials/header.jsp"%>

</head>
<body>

	<main class="container">
	
		<form method="post">
			<section class="container">
				<h1 class="h1-title">Welcome to Green on the Go</h1>
			</section>
			<section class="container-fluid">
				<c:if test="${message ne null}">
					<div class="alert alert-${messageType}">
						<strong>${message}</strong>
					</div>
				</c:if>

				<div class="text-div">
					<label class="control-label">Username:<input id="text-id"type="text"
						name="username" required></label>
				</div>
				<div class="p-div">
					<label>Password:<input id="pass-id" type="password" name="password"
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