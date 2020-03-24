<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home || Green on the Go</title>
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
		<section class="jumbotron">

			<h1>Welcome ${employee.name}!</h1>
			<p>You are currently working at ${employee.company.name}.</p>

<!-- Hey what's this cool list for??? --Sam -->
			<ul>
				<c:forEach items="${carpools }" var="cp">
					<li><a href="/carpoolsummary/${cp.carpoolId }">${cp.carpoolId }</a>
					</li>
					<li>${cp.co2 }</li>
					<li><c:forEach items="${cp.employees }" var="emp">
							<ul>
								<li>${emp.name }</li>

							</ul>
						</c:forEach></li>
				</c:forEach>
			</ul>
		</section>
	</main>
</body>
</html>