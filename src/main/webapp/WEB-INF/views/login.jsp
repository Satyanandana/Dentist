<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div id="root">
	<c:url value="/" var="root" />
</div>
<head>
<title>Dr Kang's Dental</title>
<link rel="stylesheet" media="screen"
	href=" <c:url value='/resources/css/business-frontpage.css'/>">




</head>
<body>
	<p id="action" style="visibility: hidden;">${action}</p>

	<!-- Page Content -->
	<!-- Page Content -->
	<%@include file="dynamicheader.jsp"%>
		<br />
		<br />
		<br />
	<div class="container">

		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">


				<div id="login" style="display: none;">
					<h2>
						<span class="glyphicon glyphicon-user"></span>Login
					</h2>

					<div class="form-group">
						<c:url value="/login/process" var="loginUrl" />
						<c:if test="${error != null}">
							<div class="alert alert-dismissible alert-success"
								id="errorLoginDiv">

								<strong><p>${error}</p></strong>
							</div>
						</c:if>
						<form method="POST" action="${loginUrl}">
							<label for="email">Username</label> <input type="text"
								class="form-control" id="email" name="email"
								placeholder="Enter username" value="" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" title="Please enter your email address in xyz@yahoo.com format."  required="required"/>

							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,10}" title="Atleast one upper case,one lowercase,on number and the length between 6 to 10" required="required"
									placeholder="Password" />
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						<button type="submit" class="btn btn-primary">Login</button>	
							<a class="forgotlink" style="margin-left: 20px;"> <u>Forgot
									Password ?</u></a><br /> <br />
						</form>
						

						<a class="signuplink">Don't Have An Account ? <u>Create
								One</u></a>

					</div>

				</div>
				<div id="signup" style="display: none;">



					<p>${errorEmail}</p>
					<p>${errorPassword}</p>
					<p>${errorFirstName}</p>
					<p>${errorPhoneNumber}</p>

					<h2>
						<span class="glyphicon glyphicon-log-in"></span> Register
					</h2>
					<c:url value="/signup/process" var="signupUrl" />
					<form:form method="POST" action="${signupUrl}" id="signupForm"
						modelAttribute="patient">
						<div class="col-sm-4">
							<div class="form-group">
								<label for="first-name">First Name</label>


								<form:input type="text" class="form-control" path="firstName"
									id="firstName" required="required"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="last-name">Middle Name</label>


								<form:input type="text" class="form-control" path="middleName"
									id="middleName" required="required"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="last-name">Last Name</label>

								<form:input type="text" class="form-control" path="lastName"
									id="lastName" required="required" />
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label for="email">Email</label>

								<form:input type="text" class="form-control"
									path="userAuth.userEmail" id="userEmail" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" title="Please enter your email address in xyz@yahoo.com format."  required="required" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="password">Password</label>


								<form:input type="password" class="form-control"
									path="userAuth.userPwd" id="userpassword" name="userpassword" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,10}" title="Atleast one upper case,one lowercase,on number and the length between 6 to 10" required="required" value=""/>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="password">Re-enter Password</label> <input
									type="password" id="reuserpassword" name="reuserpassword" class="form-control" name="repassword" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,10}" title="Atleast one upper case,one lowercase,on number and the length between 6 to 10" required="required" value="">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="major">Phone Number</label>

								<form:input type="text" class="form-control"
									placeholder="xxx-xxx-xxxx" path="phoneNumber" id="phoneNumber" required="required" pattern="^[0-9]+$" maxlength="10"/>
							</div>
						</div>
						<div class="col-sm-6">

							<div class="form-group">
								<label for="dob12">Date Of Birth</label> <input type="text"
									class="form-control" id="dob" placeholder="mm/dd/yyyy"
									pattern="\d{1,2}/\d{1,2}/\d{4}" name="dob" required="required" title="date should be in mm/dd/yyyy format">


							</div>
						</div>
						<div class="col-sm-12">
							<div class="checkbox">
								<label> <input type="checkbox" checked="checked">
									I Agree
								</label>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button type="submit" class="btn btn-primary">Register</button>
							<div style="float: right; font-size: large;">
								<a  class="loginlink" style="margin-right: 20px;">Login</a> <a
									class="forgotlink">Forgot Password</a>
							</div>

						</div>

					</form:form>

				</div>

				<div id="forgot" style="display: none;">
					<h2>
						<i class="fa fa-key"></i> Forgot Password
					</h2>
					<div class="col-sm-12">
						<div class="alert alert-dismissible alert-success" id="successDiv"
							style="display: none;">

							<strong><p></p></strong>
						</div>

						<form id="forgotPasswordForm">
							<div class="form-group">
								<label for="first-name">Enter Your Email</label> <input
									type="text" class="form-control" name="email" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" title="Please enter your email address in xyz@yahoo.com format."  required="required"  />
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
								
								<button type="submit" class="btn btn-primary">Send
							Me An Email</button>


						</form>

					

						<div style="float: right; font-size: large;">
							<a class="loginlink" style="margin-right: 20px;">Login</a> <a
								class="signuplink">SignUp</a>
						</div>


					</div>

					<!-- 
  <div id="forgot" style="display: none;">
  <h2><i class="fa fa-key"></i> Forgot Password</h2>

<div class="col-sm-6">
       <div class="alert alert-dismissible alert-success" id="successDiv" style="display: none;">
  
  <strong><p></p></strong>.
</div>
<div class="alert alert-dismissible alert-danger" id="errorDiv" style="display: none;">
  
  <strong><p></p></strong>
</div>
</div>

			<form id="forgotPasswordForm">
			  <div class="form-group">
			    <label for="first-name">Enter Your Email</label>
			     <input type="text" class="form-control" name="email" id="email"/>
			  </div>
			  <button type="submit" class="btn btn-primary" onclick="forgot.sendEmail('../login/forgotpassword')">Send Me An Email </button><div style="float: right;"><a class="loginlink" style="margin-right: 20px;font-size: large;">Login</a>
 <a class="signuplink" style="font-size: large;">SignUp</a></div>
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  </form>
			  
			 
 </div>
 
  -->


				</div>

			</div>



		</div>
</div>
		<%@include file="footer.jsp"%>

		<script type="text/javascript"
			src="<c:url value='/resources/js/bootstrap-datepicker.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/resources/js/jquery.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/resources/js/forgot.js'/>"></script>

		<script type="text/javascript">
			function login() {

				$("#signup").hide();
				$("#forgot").hide();
				$("#login").show();

			}
			function signup() {

				$("#login").hide();

				$("#forgot").hide();
				$("#signup").show();

			}
			function forgot() {
				$("#login").hide();
				$("#signup").hide();
				$("#forgot").show();

			}

			$(document).ready(function() {
				//prevent submit event and send ajax call ..
				$("#forgotPasswordForm").on("submit", function (e) {
				    e.preventDefault();
				    forgotPassword.sendEmailPassword('../login/forgotpassword');
				});
				$("#signupForm").on("submit", function (e) {
					
					if($('#signupForm #userpassword').val() != $('#signupForm #reuserpassword').val()) {
			            alert("Password and Confirm Password don't match");
			            // Prevent form submission
			            e.preventDefault();
			        }
				});
				

				$(".signuplink").on('click', signup);
				$(".loginlink").on('click', login);
				$(".forgotlink").on('click', forgot);
				var action = $("#action");

				if (action.html() === "" || action.html() === "login") {

					login();
				} else if (action.html() === "signup") {

					signup();
				} else if (action.html() === "forgot") {

					forgot();
				}

			});
		</script>
</body>
</html>