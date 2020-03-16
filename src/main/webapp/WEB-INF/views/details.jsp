<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<main class="container">
		<section class="jumbotron">
		<h2>${invalid }</h2>
		<section class="jumbotron">
			<h1>From:</h1>
			<h1>${street}</h1>
			<h1>${city}</h1>
			<h1>${zip}</h1>
			</section>
			<section class="jumbotron">
			<h1>To: ${coName }</h1>

			<h1>${street1}</h1>
			<h1>${city1}</h1>
			<h1>${zip1}</h1>
			</section>
			<section class="jumbotron">
			<h1>Is:</h1>
			<h1>${distance.text }</h1>
			<h1>And Produces:</h1>
			<h1>${em} lbs of CO2</h1>
			</section>
		</section>
		<div>
		
		</div>
	</main>
</body>
</html>