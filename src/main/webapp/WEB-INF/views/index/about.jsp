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
			<h2 class="about-p">About the Application</h2>
			<p align="justify">Whether cutting your carbon footprint or
				simply saving on gas, carpooling is a practice that more and more
				companies are trying to encourage in their employees. It's simple,
				it's efficient, and it doesn't cost your company a cent.</p>
			<p align="justify">So how do we encourage employees to start
				pooling their resources? By making it simple, easy, and fun.</p>
			<img src="Footprint.jpg" alt="CO2 Footprint" align="left"
				style="width: 30%; border: 2px black solid" hspace="20">
			<p align="justify">The Green on the Go app provides a
				user-friendly interface that allows you the employee to both find
				potential carpools and to keep track of how much carbon you are
				saving. Drawing from the Google Maps API, the app gives you the
				opportunity to find out which of your fellow employees live in your
				area and might like to carpool. Once you make the request for a
				specific date and time, the carpool is logged into the attached SQL
				database and the app calculates the amount of carbon that you will
				be saving that trip.
			<p align="justify">Each gallon of gas burns 19.64 lbs of CO2.</p>
			<p align="justify">Measuring that against the average mileage of
				the kind of car you drive and the distance to work gives us the
				amount of CO2 you save by carpooling. Each 0.1 lbs of CO2 nets you a
				point, and the resulting tally is added to the score of everyone in
				that carpool.
			<p align="justify">Once you start carpooling, your progress goes
				up on the company board, and then the race is on to see who can rack
				up the highest score per week, per month, or all time. The more you
				carpool, the more you're encouraged to carpool, and the more you
				will save in both gas and emissions.</p>
			<p align="justify">Not only does the app track emissions by
				employee, it also tracks how much the company as a whole is saving
				by aggregating the employee scores. The results are displayed on the
				score board of participating companies, allowing employers to
				compete for the ability to say that their employees have cut their
				emissions more than any of their rivals.</p>
			<p align="justify">Simple, easy, and fun means bigger gains for
				your company and cleaner air for everyone.</p>

		</section>
		<section class="jumbotron">
			<h2>Application Creators</h2>
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="card mb-3">
							<div class="card-body"></div>
							<img style="width: 100%; display: block;"
								src="David Breitenbeck.jpeg" alt="David Breitenbeck Headshot">
							<div class="card-body">
								<h3 class="card-header">David Breitenbeck</h3>
								<p class="card-text">
								<h6></h6>
								David Breitenbeck has been a Professional Writer for several
								years and views coding as a further opportunity to create the
								kind of elegant, high quality work that he sees a need for in
								the world. He finds that the two disciplines demand many of the
								same skills: attention to detail, a clear idea of the end goal,
								and the ability to see connections in unexpected places.
								Whatever he does, he strives to be open-minded, curious, and
								eager to learn, and to maintain high standards for himself and
								his work.
								</p>
							</div>
							<div class="card-body">
								<h5 class="card-title text-center">
									<a href="https://www.linkedin.com/in/david-breitenbeck-nc"
										class="btn btn-success">Linked In</a> <a
										href="https://github.com/DBreitenbeck1"
										class="btn btn-success">GitHub</a>
								</h5>
							</div>
						</div>
					</div>
					<div class="col">

						<div class="card mb-3">
							<div class="card-body"></div>
							<img style="width: 100%; display: block;" src="Sam Keim.jpeg"
								alt="Card image">
							<div class="card-body">
								<h3 class="card-header">Sam Keim</h3>
								<p class="card-text">
								<h6></h6>
								My name is Sam Keim, I am a Java developer and graduate of Grand
								Circus in Detroit. Before enrolling I was working on an
								accounting team for an automotive company, developing reports
								and efficiency tools for our team. I have a passion for
								remaining a jack of all trades, one who is quick to learn and
								adapt to new skills. Grand Circus has been an immersive and
								intense experience with deep roots in teamwork. These were the
								traits I was looking for when seeking to continue my education
								in programming. This final project has been a wonderful
								experience in Agile methodology. I am proud of the work that my
								teammates and I have completed on the project, and I hope you
								find it enjoyable as well.
								</p>
							</div>
							<div class="card-body">
								<h5 class="card-title text-center">
									<a href="http://www.linkedin.com/in/SamKeim"
										class="btn btn-success">Linked In</a> <a
										href="https://github.com/SamKeim" class="btn btn-success">GitHub</a>
								</h5>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="card mb-3">
							<div class="card-body"></div>
							<img style="width: 100%; display: block;"
								src="Rana Siroosian.jpg" alt="Card image">
							<div class="card-body">
								<h3 class="card-header">Rana Siroosian</h3>
								<p class="card-text">
								<h6></h6>
								I graduated from a technical school overseas where I studied
								computer science. In addition I studied information technology
								for two years, but because of the poor education services I
								changed my major to Soil Science Engineering. Recently, I was
								working as a customer care manager at the Delta terminal. While
								I enjoyed my job, I have always wanted to work in the tech
								field, and I made the decision to enroll full time in a Java
								Bootcamp at Grand Circus. I am hard-working and passionate about
								learning new skills and implementing those skills in my career
								path. The ability to create anything that comes to mind only
								with the help of a computer and my knowledge and have the
								possibility of turning those ideas into reality is amazing.
								</p>
							</div>
							<div class="card-body">
								<h5 class="card-title text-center">
									<a href="http://www.linkedin.com/in/rana-siroosian"
										class="btn btn-success">Linked In</a> <a
										href="https://github.com/Rana-Siroosian"
										class="btn btn-success">GitHub</a>
								</h5>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card mb-3">
							<div class="card-body"></div>
							<img style="width: 100%; display: block;" src="Kyle Warchuck.png"
								alt="Card image">
							<div class="card-body">
								<h3 class="card-header">Kyle Warchuck</h3>
								<p class="card-text">
								<h6></h6>
								My name is Kyle Warchuck, i'm a developer graduating from Grand
								Circus. Before Grand Circus I took Python classes which fueled
								my interest in object orientated programming. Grand Circus has
								taught me relevant skills, including Java, algorithms and Agile
								methodologies. I'm excited to start my career in the tech field!
								</p>
							</div>
							<div class="card-body">
								<h5 class="card-title text-center">
									<a href="http://www.linkedin.com/in/kyle-warchuck"
										class="btn btn-success">Linked In</a> <a
										href="https://github.com/KeeleBoy" class="btn btn-success">GitHub</a>
								</h5>
							</div>
						</div>
					</div>
				</div>
		</section>

	</main>
</body>
</html>