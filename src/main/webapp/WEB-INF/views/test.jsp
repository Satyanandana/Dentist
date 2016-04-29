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
	<c:url value="/doc/upload" var="upload" />
	<form action="${upload}" enctype="multipart/form-data" method="POST">
		<table>
			<tr>
				<td>File to upload:</td>
				<td><input type="file" name="file" /></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Upload" /></td>
			</tr>
		</table>
	</form>


</body>
</html>