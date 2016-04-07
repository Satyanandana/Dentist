<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUp</title>
<!-- Bootstrap Core CSS -->
<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
</head>
<body>
	<h2>Sign up Form</h2>
	<c:url value="/signup/process" var="signUpUrl" />
	<form:form  id="signupForm" action="${signUpUrl}" method="POST" modelAttribute="patient">
		<p>${errorEmail}</p>
		<p>${errorPassword}</p>
		<p>${errorFirstName}</p>
		<p>${errorPhoneNumber}</p>

		<p></p>
		<table>
			<tr>
				<td><label for="Firstname">FirstName: </label></td>
				<td><form:input path="firstName" id="firstname" /></td>
			</tr>
			<tr>
				<td><label for="lastName">LastName: </label></td>
				<td><form:input path="lastName" id="lastName" /></td>
			</tr>
			<tr>
				<td><label for=middleName>middleName: </label></td>
				<td><form:input path="middleName" id="middleName" /></td>
			</tr>
			<tr>
				<td><label for=dateOfBirth>dateOfBirth: </label></td>
				<td>
					<%-- <form:input  path="dateOfBirth" id="dateOfBirth"/> --%> <input
					type="date" name="dob" />
				</td>
			</tr>
			<tr>
				<td><label for=date>date: </label></td>
				<td>
					<input type="text" pattern="\d{1,2}/\d{1,2}/\d{4}" class="datepicker" name="date" value="" />
				</td>
			</tr>
			<tr>
				<td><label for=useremail>user email: </label></td>
				<td><form:input path="userAuth.userEmail" id="userEmail" /></td>
			</tr>
			<tr>
				<td><label for=useremail>phone number </label></td>
				<td><form:input path="phoneNumber" id="phoneNumber" /></td>
			</tr>
			<tr>
				<td><label for=userepassword>password: </label></td>
				<td><form:input path="userAuth.userPwd" id="userpassword" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</td>

			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Register" /></td>
			</tr>
		</table>
	</form:form>
		<!-- script references -->
	<script	src="<c:url value='/resources/js/jquery-2.2.2.min.js'/>"></script>
	<script src="<c:url value='/resources/js/signup.js'/>"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	
</body>
</body>
</html>