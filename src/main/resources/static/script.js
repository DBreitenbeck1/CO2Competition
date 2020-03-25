/**
 * 
 */
function validate(){
  var password = document.getElementById("psw");
  var message = document.getElementById("feedback");
  var regex = new RegExp("^(?=.*[0-9])(?=.{4,})")
  if(regex.test(password.value)) {
	message.style.display = "none";
    password.className = "form-control is-valid";
  } else {
	message.style.display = "block";
    password.className = "form-control is-invalid";
  }
}

function passwordValidate(){
	var password = document.getElementById("psw").value;
	var regex = new RegExp("^(?=.*[0-9])(?=.{4,})");
	if(password === "" || password === null){
		return true;
	} else if(regex.test(password)){
		return true;
	} else {
		alert("Password does not pass requirements.");
		event.preventDefault();
		return false;
	}
}