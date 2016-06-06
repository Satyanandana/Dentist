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
<meta name="google-site-verification" content="gNGRp9Tf8KTXCX8wlMHvUbHbYg2ywlJm4I63F0_KEA4" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dr Kang's Dental</title>

<style type="text/css">
.image {
	position: relative;
	width: 100%; /* for IE 6 */
}

h2 {
	position: absolute;
	top: 0px;
	left: 0;
	width: 100%;
}

h2 span {
	color: white;
	font: bold 24px/45px Helvetica, Sans-Serif;
	letter-spacing: -1px;
   /*background: rgb(0, 0, 0); /* fallback color */
	/*background: rgba(30, 30, 30, 0.4);*/
/*	padding: 10px;*/
	float: right;
}

h2 span.spacer {
	padding: 0 5px;
	float: left;
	
}

.intro-header {
    
    background: no-repeat center center;
    background-attachment: scroll;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    background-size: cover;
    -o-background-size: cover;
    margin-bottom: 50px;
}
</style> 


</head>

<body>

	<%@include file="dynamicheader.jsp"%>


	<div class="row" >
		<div class="col-xs-12 intro-header" class="img-responsive" style="background-image: url('resources/img/home.jpg');background-size:100% auto;height:531px;width:100% ;padding:5px;" >
		<div class="visible-lg site-heading">
		  <h2>
					<span>  <br/>
					<span class='spacer'><a	href="https://www.facebook.com/ykangdmd" data-toggle="tooltip"	title="FACEBOOK" target="\blank">
					<i	class="fa fa-facebook-official"	style="font-size: 40px; color: #3b5998">&nbsp;<font size="30px;">Facebook</font></i></a></span>
					
					<br /><br /> 
					<span class='spacer'><a	href="https://plus.google.com/+Ykangdmd/about?gl=US&hl=en-US" target="\blank" data-toggle="tooltip" title="GOOGLE"><i class="fa fa-google-plus-square"
								style="font-size: 40px; color: red">&nbsp;<font size="30px;">Google</font></i></a></span>
								
								
					<br /><br />
					 <span class='spacer'><a href="https://www.yelp.com/biz/dr-kangs-dental-newton-centre" target="\blank" data-toggle="tooltip" title="YELP">
					 <i class="fa fa-yelp"	style="font-size: 40px; color: #c41200">&nbsp;<font size="30px;">Yelp</font></i></a></span>
					 
					 <br /><br />
					 <span class='spacer'><a href="http://www.wellness.com/dir/4153468/dentist/ma/newton-center/yoon-kang-yoon-h-kang-dmd-phd-llc-dmd-phd" target="\blank">
					 <img src="<c:url value='/resources/img/wellness.jpg'/>" style="height: 50px;" /></a></span>
					<br /><br />
					 <span class='spacer'><a href="http://www.healthgrades.com/dentist/dr-yoon-kang-ynrd4" target="\blank">
					 <img src="<c:url value='/resources/img/health.png'/>"	style="height: 45px;" /></a></span></span>
</h2>
		</div>
			
		</div>


	</div>

<div class="hidden-lg row" style="    margin-top: -21px;
    margin-left: 39px;">

		  <div class="col-xs-12 col-sm-6 col-md-4"><span>  <br/>
					<span class=''><a	href="https://www.facebook.com/ykangdmd" data-toggle="tooltip"	title="FACEBOOK" target="\blank">
					<i	class="fa fa-facebook-official"	style="font-size: 40px; color: #3b5998">&nbsp;<font size="30px;">Facebook</font></i></a></span>
					 </div>
		  <div class="col-xs-12 col-sm-6 col-md-4"><br /><br /> 
					<span class=''><a	href="https://plus.google.com/+Ykangdmd/about?gl=US&hl=en-US" target="\blank" data-toggle="tooltip" title="GOOGLE"><i class="fa fa-google-plus-square"
								style="font-size: 40px; color: red">&nbsp;<font size="30px;">Google</font></i></a></span>
							 </div>
		  <div class="col-xs-12 col-sm-6 col-md-4"><br /><br />
					 <span class=''><a href="https://www.yelp.com/biz/dr-kangs-dental-newton-centre" target="\blank" data-toggle="tooltip" title="YELP">
					 <i class="fa fa-yelp"	style="font-size: 40px; color: #c41200">&nbsp;<font size="30px;">Yelp</font></i></a></span>
					  </div>
		  <div class="col-xs-12 col-sm-6 col-md-4"> <br /><br />
					 <span class=''><a href="http://www.wellness.com/dir/4153468/dentist/ma/newton-center/yoon-kang-yoon-h-kang-dmd-phd-llc-dmd-phd" target="\blank">
					 <img src="<c:url value='/resources/img/wellness.jpg'/>" style="height: 50px;" /></a></span> </div>
		  <div class="col-xs-12 col-sm-6 col-md-4"><br /><br />
					 <span class=''><a href="http://www.healthgrades.com/dentist/dr-yoon-kang-ynrd4" target="\blank">
					 <img src="<c:url value='/resources/img/health.png'/>"	style="height: 45px;" /></a></span></span>

		   </div>	
			

</div>

	<%-- 	<!-- carousal images bootstrap -->
	<div id="carousel-example-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="<c:url value='/resources/img/slider-1.jpg'/>" alt="...">
				<div class="carousel-caption">
					<!-- start -->
					<div class="page-header">
						<div style="float: right; position: relative;">
							<h2>Healthy Smile, Healthy You</h2>
							<br />
							<p>
								<a class="btn btn-primary btn-lg" href="<c:url value='/profile/scheduleappointment'/>"
									role="button">Schedule a Consultation</a>
							</p>
						</div>
					</div>
					<!-- end -->
				</div>
			</div>
			<div class="item">
				<img src="<c:url value='/resources/img/slider-3.jpg'/>" alt="...">
				<div class="carousel-caption">



					<div style="float: left; position: relative;">
						<h2>Results You Will Love</h2>
						<br />

						<p>
						
							<a class="btn btn-primary btn-lg" href="<c:url value='/aboutme'/>" role="button">Read
								More</a>
						</p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="<c:url value='/resources/img/slider-2.jpg'/>" alt="...">
				<div class="carousel-caption">

					<div style="float: left; position: relative;">
						<h2>Relaxed and Friendly</h2>
						<br />

						<p>
							<a class="btn btn-primary btn-lg" href="<c:url value='/profile/scheduleappointment'/>" role="button">Schedule
								an Appointment</a>
						</p>
					</div>
				</div>
			</div>

		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div> --%>
	


	<div id="howitworks" class="container">

<br />
	<div class="row">
			

				<p>
				<h4 >I offer all types of dental treatments within the scope of general 
				    dentistry with your best dental health as the top priority. In addition
				    to the traditional general family dentistry, I offer the latest concepts 
				    of biomedical approach to preventive, restorative and cosmetic dentistry 
				    to communities of Newton and neighboring towns.</h4>
				</p>
			

		</div>
	<br />
	
		<!-- Thumbnail  -->
		<div class="row">

			<div class="col-xs-12 col-md-4">
				<div class="thumbnail">
					<center>
						<div class="caption">
							<h3>Register With Us</h3>
							<a class="fa fa-lock" style="font-size: 160px; color: lightblue;"
								href="login/form"></a>

						</div>
					</center>
				</div>
			</div>

			<div class="col-xs-12 col-md-4">
				<div class="thumbnail">
					<center>
						<div class="caption">
							<h3>Schedule An Appointment</h3>

							<a class="fa fa-clock-o"
								style="font-size: 160px; color: lightblue;"
								href="profile/scheduleappointment"></a>


						</div>
					</center>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="thumbnail">
					<center>
						<div class="caption">
							<h3>Wait for confirmation</h3>

							<a class="fa fa-envelope"
								style="font-size: 160px; color: lightblue;" href="contactus"></a>

						</div>
					</center>
				</div>
			</div>

		</div>
		


	</div>

	<%@include file="footer.jsp"%>
	<script type="text/javascript">
		$(function() {

			$("h2").wrapInner("<span>")

			$("h2 br").before("<span class='spacer'>").after(
					"<span class='spacer'>");

		});
	</script>
</body>

</html>