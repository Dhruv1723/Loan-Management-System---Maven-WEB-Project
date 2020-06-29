/**
 * 
 */
function validation(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var confirmpassword = document.getElementById("confirmpassword").value;
	var age = document.getElementById("age").value;
	var number = document.getElementById("number").value;
	var amount = document.getElementById("amount").value;
	var year = document.getElementById("year").value;
	var errormsg = document.getElementById("errormsg");
	var text;
	errormsg.style.padding="10px";

	if(username.length < 3){
		text="Username Must be of atleast 4 characters long";
		errormsg.innerHTML=text;
		return false;
	}
	if(password.length < 6){
		text="Password Must be of atleast 6 characters";
		errormsg.innerHTML=text;
		return false;
	}
	if(password != confirmpassword){
		text="Password and Confirm Password doesn't match";
		errormsg.innerHTML=text;
		return false;
	}
	if (age < 21) {
		text="You are not elder enough";
		errormsg.innerHTML=text;
		return false;
	}
	if (number.length < 10 || number.length > 10 ) {
		text="Please Enter Valid Contact Number";
		errormsg.innerHTML=text;
		return false;
	}
	if (amount <= 0) {
		text="Please Enter Valid Amount";
		errormsg.innerHTML=text;
		return false;
	}
	if (year <= 0) {
		text="Please Enter Valid Time Period";
		errormsg.innerHTML=text;
		return false;
	}
	alert("Form Submitted Successfully.!")
	return true;


}