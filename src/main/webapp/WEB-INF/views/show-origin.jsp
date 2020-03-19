<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="/style.css" />
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
<form action="/find-carpool/${id }">
<section class="jumbotron">
<img src="http://mapsdirection.live/livelp/landerb/source/pin.png"/>
<div class="home-div">Home - ${eCity }, ${eStreet }, ${eZip }</div>
<div class="work-div">Work - ${cCity }, ${cStreet }, ${cZip }</div>
<div class="date-div">Select pickup date: <input type="date" name="date" required/></div>
<div class="time-div">Select pickup time: <input type="time" name="time" required/></div>

</section>
<input type="hidden" name="eCity" value="${eCity }"/>
<button class="btn btn-primary" type="submit">Find carpool</button>
</form>
</main>
</body>
</html>