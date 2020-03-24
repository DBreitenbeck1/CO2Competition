<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<meta charset="ISO-8859-1">
<title>Company Scores!</title>

<%@ include file="partials/header.jsp"%>

</head>
<body>

	<h1 class="Jumborton">Company Goal:</h1>

	<section class="lead">
		<div>

			<h3>Current company total is: ${Total}</h3>
			<h3>Current company goal is: ${Goal}</h3>
		</div>


	</section>

	<div class="progress">
		<div class="progress-bar progress-bar-striped progress-bar-animated"
			role="progressbar" aria-valuenow="75" aria-valuemin="0"
			aria-valuemax="100" style="width: ${Score}%"></div>
	</div>

	<h3>${Score }%of the way there!</h3>


</body>
</html>