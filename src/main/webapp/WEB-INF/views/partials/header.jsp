<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand">Go Green</a>
    <small class="text-muted">A CO2 Savings Competition</small>

  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/employee/${id}">Home</a>
      </li>
	<!-- navbar buttons showing only if logged in -->
      <c:if test="${employee ne null}">
	      <li class="nav-item">
	        <a class="nav-link" href="/carpool/${emId}">Request a Carpool</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/routes/${emId}">Available Routes</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/previous-routes/${emId}">Previous Routes</a>
	      </li>
	<!--  <li class="nav-item">
	        <a class="nav-link" href="/favorite-routes">Favorite Routes</a>
	      </li>
	 -->
	      <li class="nav-item">
	        <a class="nav-link" href="/summary">Leaderboard</a>
	      </li>
		</c:if>
	  </ul>
  	<c:if test="${employee ne null}">
		<div class="nav-item active navbar-nav"><a class="nav-link">Welcome ${employee.name}! </a>
		<span class="btn btn-link text-secondary"><a href="/logout"><i>Logout</i></a></span>	
		</div>
	</c:if>
	<c:if test="${employee eq null}">
		<!-- Right side, login button // user name and score and logout button -->
		<a class="navbar-brand" href="/login">Login</a>
	</c:if>  
  </div>
</nav>

