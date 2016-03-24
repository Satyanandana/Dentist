<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
<!-- Bootstrap Core CSS -->
<link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet">
</head>
<body>


	<c:url value="/login/process" var="loginUrl" />
	<form id="login" action="${loginUrl}" method="POST">
		<c:if test="${param.error != null}">
			<p>Invalid username and password.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>You have been logged out.</p>
		</c:if>
		<c:if test="${error != null}">
			<p> ${error} </p>
		</c:if>
		<p>
			<label for="username">email</label> <input type="text" id="email"
				name="email" />
		</p>
		<p>
			<label for="password">Password</label> <input type="password"
				id="password" name="password" />
		</p>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn">Log in</button>
	</form>

	<P>The time on the server is ${serverTime}.</P>
	<!-- script references -->
	<script	src="<c:url value='/resources/js/jquery-2.2.2.min.js'/>"></script>
	<script src="<c:url value='/resources/js/login.js'/>"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
</body>
</html>