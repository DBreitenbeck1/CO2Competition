<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Plan Carpool || Green on the Go</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<%@ include file="partials/header.jsp"%>


<style>
.home-div{

margin-top:-100px;
margin-left:35px;
}
.work-div{
margin-left:35px;
margin-top:50px;
}
.date-div{
margin-left:35px;
margin-top:40px;
}
.time-div{
margin-left:35px;
margin-top:20px;

}
</style>
</head>
<body>

	<main class="container">
		<section class="jumbotron">

			<form action="/submit-carpool" method="post">
				<c:if test="${method eq 'To Work'}">
					<div>Home - ${employee.streetAddress}, ${employee.city},
						${employee.zipCode}</div>
					<div>Work - ${employee.company.streetAddress},
						${employee.company.city}, ${employee.company.zipCode}</div>
				</c:if>

				<c:if test="${method eq 'Back Home'}">
					<div>Work - ${employee.company.streetAddress},
						${employee.company.city}, ${employee.company.zipCode}</div>
					<div>Home - ${employee.streetAddress}, ${employee.city},
						${employee.zipCode}</div>
				</c:if>

				<div>
					Select pickup date: <input type="date" name="date" required />
				</div>
				<div>
					Select pickup time: <input type="time" name="time" required />
				</div>

				<input type="hidden" value="${username}" name="username">
				<button class="btn btn-primary" type="submit">Find Carpool</button>
			</form>
		</section>
	</main>
</body>
</html>