<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/header.jsp"%>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/litera/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-pLgJ8jZ4aoPja/9zBSujjzs7QbkTKvKw1+zfKuumQF9U+TH3xv09UUsRI52fS+A6"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<meta charset="ISO-8859-1">
<title>Register a company</title>
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

		<h3>Register a company for Green on the Go</h3>

		<form method="post">
			<section class="jumbotron">

				<div>
					<label>Company Name:<input class="name" type="text"
						name="name" required minlength="3"></label>
				</div>

				<div class="add-div">Address:</div>
				<div>
					<label>City:<input class="city" type="text" name="city"
						required minlength="4"></label>
				</div>
				<div>
					<label>Street Address:<input class="street" type="text"
						name="streetAddress" required minlength="4"></label>
				</div>
				<div>
					<label>Zip code:<input class="zip" type="text"
						name="zipCode" required minlength="4"></label>
				</div>


			</section>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>


	</main>

</body>
</html>