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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dr Kang's Dental</title>

</head>
<body>
	<%@include file="dynamicheader.jsp"%>

	<!-- Page Content -->
	<div class="container">
			<br />
		<br />
		<br />
		<h1>Our Office Gallery</h1>
		<br />
		<div class="row">



			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g1.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g2.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g3.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g4.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g5.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g6.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g7.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g8.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g9.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g10.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g11.jpg'/>" />
				</a>
			</div>
			<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				<a class="thumbnail" href="#"> <img class="img-responsive"
					src="<c:url value='/resources/img/g12.jpg'/>" />
				</a>
			</div>


		</div>
	</div>

	<%@include file="footer.jsp"%>

</body>
</html>