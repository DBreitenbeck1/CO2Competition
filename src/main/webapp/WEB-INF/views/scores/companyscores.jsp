<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<meta charset="ISO-8859-1">
<title>Company Scores!</title>

<%@ include file="../partials/header.jsp"%>

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