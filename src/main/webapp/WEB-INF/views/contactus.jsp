<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dr Kang's Dental</title>

<style>
@media ( max-width : 550px) {
	.big-container {
		display: none;
	}
}

@media ( min-width : 550px) {
	.small-container {
		display: none;
	}
}
/* Responsive iFrame */
.responsive-iframe-container {
	position: relative;
	padding-bottom: 56.25%;
	padding-top: 30px;
	height: 0;
	overflow: hidden;
}

.responsive-iframe-container iframe, .vresponsive-iframe-container object,
	.vresponsive-iframe-container embed {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
<script type="text/javascript"
	src="<c:url value='/resources/js/contactusTemp.js'/>"></script>

	<%@include file="dynamicheader.jsp"%>
	
	<div class="container">

			<br />
		<br />
		<br />
		<h1>Contact Us</h1>
		<br />

		<div class="row">
			<div class="col-sm-6">

				<form class="form-horizontal" name="contactus" id="contactus">
					<fieldset>


						<div class="form-group">
							<div class="alert alert-dismissible alert-success" style="display: none;"  id="successContactDiv" name="successContactDiv">

								<strong>Well done!</strong> Your message has been sent.</a>.
							</div>
							<div class="alert alert-dismissible alert-danger" style="display: none;" id="error" name="error">
  
 Something Went Wrong ! Send it again .
</div>
							<label for="inputPassword" class="col-lg-2 control-label">Name</label>
							<div class="col-lg-10">
								<input type="text" class="form-control" name="name" id="name" placeholder="Name" required="required">

							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail" class="col-lg-2 control-label">Email</label>
							<div class="col-lg-10">
								<input type="text" class="form-control" id="email" name="email"
									placeholder="Email" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-lg-2 control-label">Number</label>
							<div class="col-lg-10">
								<input type="number" class="form-control" id="number" name="number"
									placeholder="Number" required="required">

							</div>
						</div>

						<div class="form-group">
							<label for="textArea" class="col-lg-2 control-label">Summary</label>
							<div class="col-lg-10">
								<textarea class="form-control" rows="3" id="summary" name="summary" required="required"> </textarea>
								<span class="help-block">Please tell us your reason for
									contacting us .</span>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</fieldset>
						
							<div class="form-group">
							<div class="col-lg-10 col-lg-offset-2">
							<!-- 	<button type="reset" class="btn btn-default">Cancel</button> -->
								<button type="submit" class="btn btn-primary" >Send</button>
							</div>
						</div>
</form>

					
					
				
			</div>
			<div class="col-sm-6">

				<p>
					<strong>If you're coming from another location and you are
						not familiar with the streets on the map below, please contact our
						office for directions at (617) 244-8087. Our office is located in
						the building right across the Union street from D-line (Green
						Line) Newton Centre T-Station.</strong>
				</p>
				<br />
				<br />
				<div class="responsive-iframe-container big-container">
					<iframe
						src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2949.6088459480216!2d-71.193321!3d42.32954099999999!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89e382786f7ed0b1%3A0x6c6af8f44698047b!2sYoon+H.+Kang%2C+DMD+PhD+LLC!5e0!3m2!1sen!2sus!4v1398192235059"
						style="border: 0" frameborder="0" height="400" width="500"></iframe>
				</div>

				<div class="responsive-iframe-container small-container">
					<iframe
						src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2949.6088459480216!2d-71.193321!3d42.32954099999999!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89e382786f7ed0b1%3A0x6c6af8f44698047b!2sYoon+H.+Kang%2C+DMD+PhD+LLC!5e0!3m2!1sen!2sus!4v1398192235059"
						style="border: 0" frameborder="0" height="300" width="350"></iframe>
				</div>


			</div>
		</div>
	</div>
	
 <%@include file="footer.jsp"%>
	<script type="text/javascript">
	$(document).ready(function(){
		 
		 
		   
		
		
		 
		$("#contactus").on("submit", function (e) {
		    e.preventDefault();
		    contactusTemp.sendInquiry('visitor/contactinfo');
		    
		});
		
	});
	</script>


	
	

</body>
</html>