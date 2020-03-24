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
<title>Register a company</title>
</head>
<body>

	<main class="container">
		<form method="post">
			<section class="jumbotron">

				<div>
					<label>Company Name:<input class="name" type="text"
						name="name" required minlength="3"></label>
				</div>
				<div>
					<label>User-name:<input class="user" type="text"
						name="username" required minlength="4"></label>
				</div>
				<div>
					<label>Password:<input class="pass" type="password"
						name="password" required minlength="4"></label>
				</div>
				<div class="add-div">Address:</div>
				<div>
					<label>City:<input class="city" type="text" name="city"
						required minlength="4"></label>
				</div>
				<div>
					<label>Street Adress:<input class="street" type="text"
						name="streetAddress" required minlength="4"></label>
				</div>
				<div>
					<label>Zip code:<input class="zip" type="text"
						name="zipCode" required minlength="4"></label>
				</div>
				<div>
					<label>Goal:<input class="goal" type="number" name="goal"></label>
				</div>


			</section>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>


	</main>

</body>
</html>