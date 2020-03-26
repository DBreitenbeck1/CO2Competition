<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About</title>
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

			<h2>About the Application</h2>
			<p align="justify">Whether cutting your carbon footprint or simply saving on gas,
				carpooling is a practice that more and more companies are trying to
				encourage in their employees. It's simple, it's efficient, and it
				doesn't cost your company a cent. </p>
				<p>So how do we encourage employees to start pooling their resources? 
				By making it simple, easy, and fun.</p>
				<p>The Green on the Go app provides a user-friendly
				interface that allows you the employee to both find potential
				carpools and to keep track of how much carbon you are saving. 
				Drawing from the Google Maps API, the app gives you the
				opportunity to find out which of your fellow employees live in
				your area and might like to carpool. Once you make the request for a
				specific date and time, the carpool is logged into the attached SQL
				database and the app calculates the amount of carbon that you will
				be saving that trip according to distance travelled and the kind of
				car you drive. The resulting tally of saved emissions is then added to
				the score of everyone in that carpool. <p>Once you start carpooling,
				your progress goes up on the company board, and then the race is on
				to see who can rack up the highest score per week, per month, or
				all time. The more you carpool, the more you're encouraged to
				carpool, and the more you will save in both gas and emissions.</p>
				<p>Not only does the app track emissions by employee, it also tracks
				how much the company as a whole is saving by aggregating the
				employee scores. The results are displayed on the score board of
				participating companies, allowing employers to compete for the
				ability to say that their employees have cut their emissions more
				than any of their rivals. </p>
				<p>Simple, easy, and fun means bigger gains
				for your company and cleaner air for everyone.</p>
				
		</section>
	<section class="jumbotron">
	<h2>Application Creators</h2>
	
	<a href="https://www.linkedin.com/in/david-breitenbeck-nc"><h6>David Breitenbeck</h6></a>
	<a href="https://linkedin.com/in/kyle-warchuck"><h6>Kyle Warchuck</h6></a>
	<a href="https://www.linkedin.com/in/rana-siroosian"><h6>Rana Siroosian</h6></a>
	<a href="https://github.com/Rana-Siroosian?tab=repositories"><i class="fa fa-github"></i></a> 
	<a href="https://www.linkedin.com/in/samkeim"><h6>Sam Keim</h6></a>
	</section>

	</main>
</body>
</html>