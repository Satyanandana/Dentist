<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
        <c:if test="${param.error != null}">
			<p>Invalid username and password.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>You have been logged out.</p>
		</c:if>

	<c:url value="/logout" var="logout" />
	<form action="${logout}" method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn">Log Out</button>
	</form>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<c:url value="/" var="home" />
	<form action="${home}" method="POST">
		<c:if test="${param.error != null}">
			<p>Invalid username and password.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>You have been logged out.</p>
		</c:if>
		<p>
			<label for="username">email</label> <input type="text"
				id="user_email" name="user_email" />
		</p>
		<p>
			<label for="password">Password</label> <input type="password"
				id="user_pwd" name="user_pwd" />
		</p>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn">Log in</button>
	</form>
</body>
</html>
