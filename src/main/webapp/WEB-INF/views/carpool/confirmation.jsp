<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation || Green on the Go</title>
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
		<form action="/employee/${id }">
			<section class="jumbotron">

				<h4 class="h4-s">Your carpool request successfully submitted</h4>
				<h5>${name}will carpool with you on ${date} at ${time} to take
					you to ${destination}</h5>
				<h5>This will save ${saved} lbs of CO2 and earn you ${score}
					points!</h5>
			</section>
		</form>
	</main>
</body>
</html>