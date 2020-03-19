<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
<style>
body{
background-image:
url('https://www.chamonix.net/sites/default/files/nodeimages/carpool.png?itok=nDJFTegd');
background-repeat: no-repeat;
background-attachment: fixed;
  background-position: center;
	
}
input[type=text]{

border: 2px solid brown;
	border-radius: 4px;
	left: 230px;
	bottom: 330px;
	color: black;
	margin-left:15px;
}
input[type=password]{

border: 2px solid brown;
	border-radius: 4px;
	left: 230px;
	bottom: 330px;
	color: black;
	margin-left:20px;
}
.control-label{
margin-top: 50px;
}

.submit-div {
	margin-top :30px;
	margin-left:16px;
}
h1{
margin-top:15px;
}
</style>
</head>
<body>
	<main class="container">
		<form method="post">
		<section class="container">
		<h1>Welcome to CO2 carpool competition</h1>
		</section>
			<section class="container-fluid">

				<div>
					<label class="control-label">Username:<input type="text" name="username" required></label>
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


	</main>
</body>
</html>