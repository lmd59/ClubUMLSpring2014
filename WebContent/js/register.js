/**
 * Combined javascript for login and registeration.
 * @author AmeyaCJoshi & SahilPatil
 * 
 */

// valid user flag. Used by Ajax call.
var validUser = false;

/**
 * Validates all required fields.
 * 
 * @returns true if all fields are valid, else false
 */
function validateForm() {

	var username = document.forms.registerForm.username.value;
	var password = document.forms.registerForm.password.value;
	var password2 = document.forms.registerForm.password2.value;
	var email = document.forms.registerForm.email.value;

	if (!validUser) {
		alert("Require valid user!");
		return false;
	} else if (password == "") {
		alert("Password can not be empty!");
		return false;
	} else if (document.forms.registerForm.password.value != document.forms.registerForm.password2.value) {
		alert("Password does not match!");
		return false;
	} else if (!validateEmail(email)) {
		alert("Require valid email!");
		return false;
	}

	return true;
}

/**
 * Check if user name already exist in user table.
 * 
 * @param value is the user name to check
 */
function checkUsername(value) {

	var block = document.getElementById("username");
	var patt1 = new RegExp("^[a-z][a-z0-9_]*.{3,12}$");
	var userFlag;
	
	userFlag = patt1.test(value); 
	if (userFlag)
	{
		ajax = createAjax();
		ajax.onreadystatechange = function(){
			if (ajax.readyState == 4 && ajax.status == 200)
			{
				var response = ajax.responseText;
				block.innerHTML = response;
			}
		};
		ajax.open("get", "ValidateServlet?username=" + value + "", true);
		ajax.send(null);
		validUser = true;
	}
	else
	{
		block.innerHTML = "<font color='red'>Username should consist only characters, numbers and underscore<br/> and should begin with a character only.</font>";
		validUser = false;
	}
	
}

/**
 * Validate passwords match.
 * 
 * Displays message for valid and invalid password.
 */
function checkPassword(pass2) {
	
	var block2 = document.getElementById("passText");
	var password1 = document.forms.registerForm.password.value;
	var password2 = document.forms.registerForm.password2.value;

	if (password1 == "" || password2 == "")
	{
		block2.innerHTML = "<font color = 'red'>Password field(s) cannot be left blank.</font>";
	}
	
	else if (password1 != password2)
	{
		block2.innerHTML = "<font color = 'red'>Password does not match.</font>";
	}
	
	else
	{
		var passFlag = validatePass(pass2);
		if(!passFlag)
		{
			block2.innerHTML = "<font color = 'red'>Password must be at least 6 characters, no more than 20 characters, and " +
								"<br>must include either upper case letters, lower case letters, numeric digits, and special characters.</font>";
		}
		else if(passFlag)
		{
			block2.innerHTML = "<font color = 'green'>Password valid.</font>";
		}
	}
}

function validatePass(password) {
	var pattern1 = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$/;

	if (password == "") {
		return false;
	} else if (pattern1.test(password)) {
		return true;
	} else {
		return false;
	}
}

/**
 * Displays message for valid and invalid email.
 * 
 * @param email to verify
 * 
 * @author AmeyaCJoshi
 */
function checkEmail(email) {
	
	var block = document.getElementById("emailText");
	var patt1 = new RegExp(".+@.+\.[com|net|org|biz|gov|edu]$");
	
	if(email == "")
	{
		block.innerHTML = "<font color = 'red'>Email field cannot be left blank.</font>";
	}
	else
	{
		var emailFlag = patt1.test(email);
		block.innerHTML = emailFlag;
		if (emailFlag)
		{
			ajaxObj = createAjax();
			ajaxObj.onreadystatechange = function() {
				if (ajaxObj.readyState == 4 && ajaxObj.status == 200)
				{
					var response = ajaxObj.responseText;
					block.innerHTML = response;
				}
			};
			ajaxObj.open("get", "ValidateEmail?email=" + email + "", true);
			ajaxObj.send(null);
		}
		else
		{
			block.innerHTML = "<font color = 'red'>Invalid email format.</font>";
		}
	}
}

/**
 * Validates if email is in correct format
 * 
 * @param email to verify
 * @return true if valid email, else false
 */

/*
function validateEmail(email) {
	

	if (patt1.test(email)) {
		return true;
	} else {
		return false;
	}
}
*/

/**
 * Creates Ajax object
 * 
 * @returns Ajax object
 */
function createAjax() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

/**
 * (Have not seen used. Candidate for removal)
 * 
 * @returns
 */
function checkPromote() {
	if (document.forms.promote1.comment.value == ""
			|| document.forms.promote1.comment.value == null) {
		alert("Comment cannot be empty!");
		return false;
	}
}

/**
 * Validation for index page.
 * EDITED BY: AmeyaCJoshi
 */

function checkLoginUsernamePassword()
{
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var userFlag = false;
	var passFlag = false;
	var regexFlag;
	
	/* Check username */
	if(username == "") {
		userFlag = false;
	}

	var re = new RegExp("^[a-z][a-z0-9_]*.{3,12}$");
	regexFlag = re.test(username.value);
	if(!regexFlag) {
		userFlag = false;
	}

	else
	{
		userFlag = true;
	}


	/* Check password */

	if(password == "") {
		passFlag = false;
	}
	
	re = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$/;
	regexFlag = re.test(password.value);
	if(!regexFlag)
		passFlag = false;
	
	/*
	re = /[a-z]/;
	if(!re.test(password)) {
		passFlag = false;
	}

	re = /[A-Z]/;
	if(!re.test(password)) {
		passFlag = false;
	}

	re = /\d/;
	if(re.test(password)) {
		passFlag = false;
	}
	*/

	else 
	{
		passFlag = true;
	}
	
	if(!userFlag || !passFlag)
		alert("Incorrect username or password!");

}

