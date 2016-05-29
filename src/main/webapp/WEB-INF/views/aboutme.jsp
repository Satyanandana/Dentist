<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<h1>About Me</h1>
		<br />
		<div class="row">
			<div class="col-sm-4">
				<img src="<c:url value='/resources/img/aboutkang.jpg'/>"
					height="400" width="250">

			</div>
			<div class="col-sm-8">
				<p>I received Doctor of Dental Medicine degree at the Tufts
					University School of Dental Medicine in 2008. I appreciate being a
					dentist because it took special path for me to get where I am
					today.</p>

				<p>Using technology to solve medical problems has always been my
					passion, and my academic training has all been related to the
					field. Prior to my Dental education, I received PhD in Biomedical
					Engineering from the University of Iowa in 1998, developing
					artificial hip replacement materials. After that, I was a
					researcher at Harvard Medical school working on development of
					artificial liver devices. I decided to receive training in
					Dentistry because I wanted to make immediate difference in
					patients' lives with my personality and skill.</p>

				<p>I have a always thought of Dentistry as a pioneering field
					that utilized man-made materials to replace functions of impaired
					or missing natural body parts. I decided to receive clinical
					training in dentistry because it allows me to instantaneously
					identify and provide solutions to each patients' concerns with my
					trained scientific background and clinical skills. It has been an
					incredible discovery of a wonderful profession in Dentistry that
					has so much to offer. And I would love to share that experience
					with you. Mission Statements:</p>

				<p>It is my mission to establish a dental practice to the
					community that provides highest quality of dental care to the
					members of community by:</p>
				<ul>
					<li>
						<p>Respect patients' concerns by really listening to the
							patients.</p>
					</li>
					<li>
						<p>Respect patients' time.</p>
					</li>
					<li><p>Respect patients choices by providing different
							options and recommendations within the highest standard of care.</p></li>
					<li><p>Respect patients' privacy and finances.</p></li>
				</ul>

				<p>
					<strong>My Vision of Dental Practice</strong>
				</p>


				<p>In my dental practice, I want to make sure that your concerns
					are respectfully received and understood. I fully recognize that
					each individual's dental concern is a personal and serious matter
					therefore should be addressed with professionalism and trust. I
					want to provide you with the most comfortable healing and educating
					environment. I want to help you make most optimal decisions for
					your dental health by providing different available treatment
					options then rendering highest quality treatments with up-to-date
					clinical and scientific information, confident clinical skills and
					compassionate human aspects. I truly look forward to serving you
					with highest standard of dental care for present and future. Please
					give me an opportunity!</p>
				<p>
					<strong>My Dental Office in Newton</strong>
				</p>


				<p>I offer all types of dental treatments within the scope of
					general dentistry with your best dental health as the top priority.
					In addition to the traditional general family dentistry, I offer
					the latest concepts of biomedical approach to preventive,
					restorative and cosmetic dentistry to communities of Newton and
					neighboring towns.</p>




			</div>

		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>