
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
	<div id="nav-text">
		<div>
			<a class="navbar-brand mb-0 h1 my-0" href="/">Green on the Go</a>
		</div>
		<div>
			<small class="text-white">A CO2 Savings Competition</small>
		</div>
	</div>
	<div class="collapse navbar-collapse" id="navbarColor02">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href="/">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/about">About</a>
			</li>
			
			<!-- navbar buttons showing only if logged in -->
			<c:if test="${employee ne null}">
				<li class="nav-item"><a class="nav-link" href="/dashboard">Dashboard</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/routes">Available
						Carpools</a></li>
				<li class="nav-item"><a class="nav-link" href="/summary">Leaderboard</a>
				</li>

				
			</c:if>
		</ul>
	</div>
	<c:if test="${employee ne null}">
		<div id="nav-text">
			<div class="text-right" class="my-0">
				<a class="navbar-text text-blue">Welcome ${employee.name}!</a>
			</div>
			<div class="text-right">
				<a class="navbar-text text-blue" href="/updateuser">Settings</a>
				<a class=navbar-text text-blue"> || </a>
				<a class="navbar-text text-blue" href="/logout">Logout</a>
			</div>

		</div>
	</c:if>
	<c:if test="${employee eq null}">
		<!-- Right side, login button // user name and score and logout button -->
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href="/login">Login</a>
			</li>
			<li class="nav-item">
			          <a class="nav-link" href="/register">New User</a>
			</li>
			<li class="nav-item">
			          <a class="nav-link" href="/registercompany">New Company</a>
		    </li>
		</ul>
	</c:if>
</nav>



