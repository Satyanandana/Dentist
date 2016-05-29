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



	<div class="container">

	<br />
		<br />
		<br />
		<h1>Ask the Doctor</h1>
		<br />

		<!-- Bootstrap FAQ - START -->
		<div class="container">


			<div class="alert alert-dismissible alert-info">
				<button type="button" class="close" data-dismiss="alert"></button>

				<p>We've provided answers to a number of commonly asked dental
					questions. If you have a question that is not addressed here,
					please call us at (617) 244-8087. We look forward to resolving all
					of your dental concerns.</p>
			</div>
			<br />

			<div class="panel-group" id="accordion">
				<div class="faqHeader">General questions</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent="#accordion" href="#collapseOne">Do you offer
								crown and bridge and denture treatments?</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
							<p>Yes, I do offer of crown and bridge and denture
								treatments.</p>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTen">Do you offer
								extractions?</a>
						</h4>
					</div>
					<div id="collapseTen" class="panel-collapse collapse">
						<div class="panel-body">
							<p>I do offer simple extractions to be completed at my
								office.</>
							<p>However, complex cases, including most of wisdom teeth
								extractions, are referred to the oral surgery specialists.</p>

						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseEleven">Do you offer
								root canal procedures?</a>
						</h4>
					</div>
					<div id="collapseEleven" class="panel-collapse collapse">
						<div class="panel-body">
							<p>Complicated cases are referred to the specialists. Some
								simple cases are offered at my office.</p>
						</div>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwelve">Do you offer
								implant dentistry?</a>
						</h4>
					</div>
					<div id="collapseTwelve" class="panel-collapse collapse">
						<div class="panel-body">
							<p>Implant surgery consultations and surgery itself is done
								by the specialists.</p>
							<p>Restoration of the placed implants are completed at my
								office.</p>
						</div>
					</div>
				</div>



				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThirteen">Do you
								treat children?</a>
						</h4>
					</div>
					<div id="collapseThirteen" class="panel-collapse collapse">
						<div class="panel-body">
							<p>Routine check up, cleaning,fluoride treatments and simple
								restorations and extractions are offered at my office.</p>
							<p>Procedures of complex nature are referred to the
								appropriate specialists.</p>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFourteen">Do you
								offer sedation dentistry?</a>
						</h4>
					</div>
					<div id="collapseFourteen" class="panel-collapse collapse">
						<div class="panel-body">


							<p>I do not offer sedation dentistry.</p>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFifteen">Do you
								offer payment plan?</a>
						</h4>
					</div>
					<div id="collapseFifteen" class="panel-collapse collapse">
						<div class="panel-body">
							<p>I do not offer payment plan, although I do accept most
								major credit cards.</p>

						</div>
					</div>
				</div>




			</div>
		</div>

		<style>
.faqHeader {
	font-size: 27px;
	margin: 20px;
}

.panel-heading [data-toggle="collapse"]:after {
	font-family: 'Glyphicons Halflings';
	content: "\e072"; /* "play" icon */
	float: right;
	color: #F58723;
	font-size: 18px;
	line-height: 22px;
	/* rotate "play" icon from > (right arrow) to down arrow */
	-webkit-transform: rotate(-90deg);
	-moz-transform: rotate(-90deg);
	-ms-transform: rotate(-90deg);
	-o-transform: rotate(-90deg);
	transform: rotate(-90deg);
}

.panel-heading [data-toggle="collapse"].collapsed:after {
	/* rotate "play" icon from > (right arrow) to ^ (up arrow) */
	-webkit-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-o-transform: rotate(90deg);
	transform: rotate(90deg);
	color: #454444;
}
</style>

		<!-- Bootstrap FAQ - END -->

	</div>


	<%@include file="footer.jsp"%>
</body>
</html>