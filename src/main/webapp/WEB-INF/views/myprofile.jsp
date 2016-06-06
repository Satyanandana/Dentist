<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dr Kang's Dental</title>
<script type="text/javascript"
	src="<c:url value='/resources/js/mustache.js'/>"></script>




<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/myprofilefunctions.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/myprofileready.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/ajax.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/changestatus.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/sentmessages.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/receivedmessages.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/confirmedappointments.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/requestedappointments.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/treatments.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/insurances.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/myprofile.js'/>"></script>

<script type="text/javascript"
	src="<c:url value='/resources/js/teethTreatments.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/senddocuments.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/receiveddocuments.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/settings.js'/>"></script>


<%@include file="template.jsp"%>




<style>
.hide {
	display: none;
}
</style>

</head>
<body>





	<%@include file="dynamicheader.jsp"%>
	
<head>
	<link rel="stylesheet"
	href="<c:url value='/resources/css/unresponsive.css'/>" media="screen">
	</head>  
 
	<p id="action" style="visibility: hidden;">${action}</p>
	<div class="container" style="width: 100%;">

		<br />
		<h1>My Dash Board</h1>
		<br />
		<div class="alert alert-dismissible alert-info">


			<p>We've provided All informations of you , If you have any
				difficulties , please call us at (617) 244-8087. We look forward to
				resolving all of your dental concerns.</p>
		</div>
		<!-- <div id="filters">
<div class="row">
<div class="col-xs-3">
<form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
       </form>
      </div>
      <div class="col-xs-9">
          	<ul style="float: right;" >
          	<br/>
          	<li class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Sort By <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
      <li><a href="#">By Date</a></li>
      <li><a href="#">By Time</a></li>
      <li><a href="#">By A-Z</a></li>
     
    </ul>
  </li>
  </ul>
  </div>
</div>
</div> -->
		<div class="row" style="width: 100%;">

			<div class="col-xs-3">
				<div class="list-group table-of-contents">
					<a class="list-group-item" onclick="showProfile()"
						href="javascript:void(0);">My Profile</a> <a
						class="list-group-item" onclick="showMessages()"
						href="javascript:void(0);">Messages</a> <a class="list-group-item"
						onclick="showAppointments()" href="javascript:void(0);">Appointments</a>
					<a class="list-group-item" onclick="showTreatments()"
						href="javascript:void(0);">Treatments</a> <a
						class="list-group-item" onclick="showInsurance()"
						href="javascript:void(0);">Insurance</a> <a
						class="list-group-item" onclick="showPayment()"
						href="javascript:void(0);">Documents</a> <a
						class="list-group-item" onclick="showSettings()"
						href="javascript:void(0);">Settings</a>
				</div>
			</div>

			<!-- All Forms Needed In Patient Profile start -->

			<div class="col-xs-9">


				<div id="MyProfile">
					<div id="showPersonalForm">
						<form class="form-horizontal" id="showPersonalForm">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-info"></i>&nbsp; Personal Information
								</h2>



								<div class="form-group">
									<label class="control-label" for="disabledInput">First
										Name</label>&nbsp;&nbsp;
									<div id="firstName" style="display: inline;"></div>

								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Middle
										Name</label>&nbsp;&nbsp;
									<div id="middleName" style="display: inline;"></div>

								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Last
										Name</label>&nbsp;&nbsp;
									<div id="lastName" style="display: inline;"></div>

								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Date
										Of Birth</label>&nbsp;&nbsp;
									<div id="dateOfBirth" style="display: inline;"></div>

								</div>


								<div class="form-group">
									<a onclick="showPersonalForm()" href="javascript:void(0);"><i
										class="fa fa-plus-circle"
										style="font-size: 30px; color: #2c3e50;"></i></a>
								</div>

							</div>
						</form>
					</div>
					<div id="hidePersonalForm" class="form-group">
						<form class="form-horizontal" id="hidePersonalForm1"
							name="hidePersonalForm1" class="hidePersonalForm1">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-info"></i>&nbsp; Personal Information
								</h2>


								<div class="form-group">
									<label class="control-label" for="disabledInput">First
										Name</label> <input class="form-control" id="firstName" type="text"
										placeholder="" name="firstName" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Middle
										Name</label> <input class="form-control" id="middleName" type="text"
										placeholder="" name="middleName" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Last
										Name</label> <input class="form-control" id="lastName" type="text"
										placeholder="" name="lastName" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Date
										Of Birth</label> <input class="form-control" id="dob" type="text"
										placeholder="mm/dd/yyyy" name="dob" required="required" pattern="\d{1,2}[-/]\d{1,2}[-/]\d{4}" title="date should be in mm/dd/yyyy format">
								</div>


								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									<div class="form-group">
							<button class="btn btn-primary" id="updatePersonal">Update</button>
							&nbsp;&nbsp;<i class="fa fa-spinner fa-pulse"
								style="font-size: 30px; visibility: hidden;" id="spinner"></i>
						</div>
							</div>
						</form>
						
					</div>


					<div id="showAddressForm">
						<form class="form-horizontal">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-map-marker"></i>&nbsp; Address Information
								</h2>




								<div class="form-group">
									<label class="control-label" for="disabledInput">Address
										1</label>&nbsp;&nbsp;
									<div id="address1" style="display: inline;"></div>

								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Address
										2</label>&nbsp;&nbsp;
									<div id="address2" style="display: inline;"></div>

								</div>

								<div class="form-group">
									<label class="control-label" for="disabledInput">City</label>&nbsp;&nbsp;
									<div id="city" style="display: inline;"></div>
									&nbsp;&nbsp; <label class="control-label" for="disabledInput">State</label>&nbsp;&nbsp;
									<div id="state" style="display: inline;"></div>
									&nbsp;&nbsp; <label class="control-label" for="disabledInput">Zip
										Code</label>&nbsp;&nbsp;
									<div id="zipcode" style="display: inline;"></div>
									&nbsp;&nbsp;

								</div>


								<div class="form-group">
									<a onclick="showAddressForm()" href="javascript:void(0);"><i
										class="fa fa-plus-circle"
										style="font-size: 30px; color: #2c3e50;"></i></a>
								</div>

							</div>
						</form>
					</div>
					<div id="hideAddressForm" class="form-group">
						<form class="form-horizontal" id="hideAddressForm1"
							name="hideAddressForm1" class="hideAddressForm1">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-map-marker"></i>&nbsp; Address Information
								</h2>



								<div class="form-group">
									<label class="control-label" for="disabledInput">Address
										1</label> <input class="form-control" id="address1" name="address1"
										type="text" placeholder="" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Address
										2</label> <input class="form-control" id="address2" name="address2"
										type="text" placeholder="" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">City</label> <input
										class="form-control" id="city" name="city" type="text"
										placeholder="" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">State</label>
									<input class="form-control" id="state" name="state" type="text"
										placeholder="" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Zip
										Code</label> <input class="form-control" id="zipcode" name="zipcode"
										type="number" placeholder="" required="required">
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									
									<div class="form-group">
							<button class="btn btn-primary" id="updateAddress">Update</button>
							&nbsp;&nbsp;<i class="fa fa-spinner fa-pulse"
								style="font-size: 30px; visibility: hidden;" id="spinner"></i>
						</div>


							</div>
						</form>
						
					</div>


					<div id="showContactForm">
						<form class="form-horizontal">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-phone"></i> Contact Information
								</h2>


								<div class="form-group">
									<label class="control-label" for="disabledInput">Email</label>&nbsp;&nbsp;
									<div id="email" style="display: inline;"></div>

								</div>

								<div class="form-group">
									<label class="control-label" for="disabledInput">Number</label>&nbsp;&nbsp;
									<div id="phoneNumber" style="display: inline;"></div>

								</div>



								<div class="form-group">
									<a onclick="showContactForm()" href="javascript:void(0);"><i
										class="fa fa-plus-circle"
										style="font-size: 30px; color: #2c3e50;"></i></a>
								</div>

							</div>
						</form>
					</div>
					<div id="hideContactForm" class="form-group">


						<div class="col-xs-12"
							style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
							<form class="form-horizontal" id="hideContactForm1"
								name="hideContactForm1" class="hideContactForm1">
								<h2>
									<i class="fa fa-phone"></i> Contact Information
								</h2>


								<div class="form-group">
									<label class="control-label" for="disabledInput">Email</label>
									<input class="form-control" id="email" name="email" type="text"
										placeholder="" disabled="">
								</div>

								<div class="form-group">
									<label class="control-label" for="disabledInput">Number</label>
									<input class="form-control" id="phoneNumber" name="phoneNumber"
										type="text" placeholder=""  required="required" pattern="^[0-9]+$" maxlength="10">
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									<div class="form-group">
								<button class="btn btn-primary" id="updateContact">Update</button>
								&nbsp;&nbsp;<i class="fa fa-spinner fa-pulse"
									style="font-size: 30px; visibility: hidden;" id="spinner"></i>
							</div>
							</form>
							
						</div>



					</div>

					<div id="showEmergencyForm">
						<form class="form-horizontal">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-exclamation-triangle"></i>&nbsp; Emergency
									Information
								</h2>


								<div class="form-group">
									<label class="control-label" for="disabledInput">Emergency
										Contact Name</label>&nbsp;&nbsp;
									<div id="name" style="display: inline;"></div>

								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Emergency
										Contact Number</label>&nbsp;&nbsp;
									<div id="phoneNumber" style="display: inline;"></div>
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Relation
									</label>&nbsp;&nbsp;
									<div id="relation" style="display: inline;"></div>
								</div>

								<div class="form-group">
									<a onclick="showEmergencyForm(event)" href="javascript:void(0);"><i
										class="fa fa-plus-circle"
										style="font-size: 30px; color: #2c3e50;"></i></a>
								</div>

							</div>
						</form>
					</div>
					<div id="hideEmergencyForm" class="form-group">
						<form class="form-horizontal" id="hideEmergencyForm1"
							name="hideEmergencyForm1" class="hideEmergencyForm1">

							<div class="col-xs-12"
								style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
								<h2>
									<i class="fa fa-exclamation-triangle"></i>&nbsp; Emergency
									Information
								</h2>


								<div class="form-group">
									<label class="control-label" for="disabledInput">Emergency
										Contact Name</label> <input class="form-control"
										id="emergencyContactName" name="emergencyContactName"
										type="text" placeholder="" required="required">
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Emergency
										Contact Number</label> <input class="form-control"
										id="emergencyContactNumber" name="emergencyContactNumber"
										type="text" placeholder="" required="required"  pattern="^[0-9]+$" maxlength="10">
								</div>
								<div class="form-group">
									<label for="relation">Relation</label> <select
										class="form-control" id="emergencyContactRelation"
										name="emergencyContactRelation">
										<option value="father">father</option>
										<option value="mother">mother</option>
										<option value="sister">sister</option>
										<option value="brother">brother</option>
										<option value="cousin">cousin</option>
										<option value="friend">friend</option>
										<option value="daughter">daughter</option>
										<option value="son">son</option>
										<option value="other">other</option>
									</select>
								</div>



							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
								<div class="form-group">
							<button class="btn btn-primary" id="updateEmergency"
								>Update</button>
							&nbsp;&nbsp;<i class="fa fa-spinner fa-pulse"
								style="font-size: 30px; visibility: hidden;" id="spinner"></i>
						</div>
						</form>
						
					</div>




				</div>







				<div id="Messages">
					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group"">


							<h2>
								<i class="fa fa-share"></i> Sent Messages <a href=""
									style="float: right;" data-toggle="modal"
									data-target="#messageModal"><i class="fa fa-plus-circle">Compose</i></a>
							</h2>
							<br />

							<ul class="list-group">
								<div id="sentmessages1"></div>
							</ul>





						</div>

					</div>



					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group">

							<h2>
								<i class="fa fa-reply"></i> Received Messages
							</h2>
							<br />



							<ul class="list-group">
								<div id="receivedmessages"></div>
							</ul>

						</div>
					</div>
				</div>


				<div id="Appointments">

					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
						<h2>
							<i class="fa fa-exclamation-triangle"></i> Requested Appointments
							<a href="<c:url value='/profile/scheduleappointment'/>"
								style="float: right;"><i class="fa fa-plus-circle">Schedule
									New</i></a>
						</h2>
						<br />



						<div class="list-group"
							style="overflow: auto; max-height: 400px; padding-top: 5px;">
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>Appointment Start Time</th>
										<th>Note</th>
										<th>Status</th>
										<th>Change Status</th>
									</tr>
								</thead>

								<tbody id="requestedAppointmentsForTemp">



								</tbody>

							</table>





						</div>

					</div>
					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group"
							style="overflow: auto; max-height: 400px; padding-top: 5px;">

							<h2>
								<i class="fa fa-check"></i> Confirmed
							</h2>
							<br />
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>Appointment Start Time</th>
										<th>Note</th>
										<th>Expected Amount</th>
										<th>Amount Paid</th>
										<th>Status</th>
										<th>Change Status</th>
									</tr>
								</thead>








								<tbody id="appointmentsForTemp">

								</tbody>
							</table>



						</div>
					</div>
				</div>


				<div id="Treatments" style="width: 100%;">

					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group">

							<h2>
								<i class="fa fa-gavel"></i> My Treatments</h2>
								
								<i class="flaticon-icon-Normal" style="color: green;">Normal</i>&nbsp;&nbsp;
								<i class="flaticon-icon-Extracted" style="color: green;">Extracted</i>&nbsp;&nbsp;
								<i class="flaticon-icon-Normal" style="color: red;">Treatment Pending</i>&nbsp;&nbsp;
								
								
								<h2> 
								<div id="" style="float: right; font-size: 70%;">
									Total Amount Paid
									<p id="totalPrice"></p>
								</div>
							</h2>
							<br />

							<div>
								<table>

									<tbody>

										<tr id="treatmentsForTemp1to16">
										</tr>
										<tr>
											<td><br/></td>
										</tr>
										<tr id="treatmentsForTemp17to32">
										</tr> 


									</tbody>
								</table>
							</div>
							<br /> <br />
							<div id="teethTreatment">

								<div class="col-xs-12"
									style="background-color: white; margin-bottom: 20px;">

									<div class="list-group" id="teethDetails"></div>
								</div>
								<div class="col-xs-12"
									style="background-color: white; margin-bottom: 20px;">

									<div class="list-group">

										<h2 style="float: left;">
											<i class="flaticon-medical-23"></i>Treatment Details
										</h2>
										<br /> <br /> <br />
										<div id="teethTreatmentDetails"></div>


									</div>
								</div>
							</div>
						</div>
					</div>



				</div>


				<div id="Insurance">


					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group"">

							<h2>
								<i class="fa fa-medkit"></i> My Insurances <a href=""
									style="float: right;" data-toggle="modal"
									data-target="#insuranceModal"><i class="fa fa-plus-circle">Add
										New</i></a>
							</h2>
							<br />

							<div id="myinsurances"></div>
						</div>
					</div>
				</div>

				<div id="Payment">

					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group"">

							<h2>
								<i class="fa fa-file-pdf-o" aria-hidden="true"></i> My Documents
								<a href="" style="float: right;" data-toggle="modal"
									data-target="#documentModal"><i class="fa fa-plus-circle">Add
										New</i></a>
							</h2>
							<br />
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>File Name</th>
										<th>Uploaded Date</th>
										<th>View File</th>

									</tr>
								</thead>

								<tbody id="mydocuments">

								</tbody>
							</table>



						</div>
					</div>
					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">
						<div class="list-group">

							<h2>
								<i class="fa fa-file-pdf-o" aria-hidden="true"></i> Received
								Documents
							</h2>
							<br />
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>File Name</th>
										<th>Received Date</th>
										<th>View File</th>

									</tr>
								</thead>

								<tbody id="receiveddocuments">

								</tbody>
							</table>


						</div>
					</div>
				</div>

				<div id="Settings">

					<div class="col-xs-12"
						style="background-color: #F0FFFF; margin-bottom: 20px; padding-left: 30px;">

						<div class="list-group"">

							<h2>
								<i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;&nbsp; My
								Settings
							</h2>
							<br />
							<div class="col-xs-6">
								<div class="alert alert-dismissible alert-success"
									id="successDiv" style="display: none;">

									<strong>Well done!</strong> You successfully changed your
									password.</a>.
								</div>
								<div class="alert alert-dismissible alert-danger" id="errorDiv"
									style="display: none;">

									<strong><p></p></strong>
								</div>
								<form name="newPasswordForm" id="newPasswordForm">


									<div class="form-group">
										<label for="first-name">Your Old Password</label> <input
											type="password" class="form-control" name="oldPwd"
											id="oldPwd" value="" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,10}" title="Atleast one upper case,one lowercase,on number and the length between 6 to 10" required="required" />
									</div>
									<div class="form-group">
										<label for="first-name">New Password</label> <input
											type="password" class="form-control" name="newPwd"
											id="newPwd" value="" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{6,10}" title="Atleast one upper case,one lowercase,on number and the length between 6 to 10" required="required"/>
									</div>

									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								
								<button type="submit" class="btn btn-primary"
									>Change
									Password</button><small>Atleast one upper case,one lowercase,one number and the length between 6 to 10</small>
									</form>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>

		<!-- All Forms Needed In Patient Profile  Ends-->




	</div>
<%@include file="footer.jsp"%>

	<div class="modal fade" id="messageModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Send New Message</h4>
				</div>
				<form id="sendMessage1" name="sendMessage1" class="sendMessage1">
				<div class="modal-body">
					<div class="form-group">
						
							<div>

								<textarea class="form-control" rows="3" id="msg" name="msg"
									placeholder="type your message here.." required="required" ></textarea>
								
							</div>



							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						>Send</button>
				</div>
				</form>
			</div>

		</div>
	</div>

	<!-- Modal new insurance -->
	<div class="modal fade" id="insuranceModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add New Insurance</h4>
				</div>
				<form name="addNewInsurance" id="addNewInsurance"
						class="addNewInsurance">
				
				<div class="modal-body">


					
						<div class="form-group">
							<label class="control-label" for="disabledInput">Insurance
								Provider Name</label> <input class="form-control"
								id="insuranceProviderName" name="insuranceProviderName"
								type="text"
								placeholder="Enter Insurance Provider Name With State Name " required="required">
							<span class="help-block">eg : Aetna MA</span>
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Subscriber
								ID</label> <input class="form-control" id="subscriberID"
								name="subscriberID" type="text"
								placeholder="Enter Subscriber ID Here " required="required">
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Insurance
								Group ID</label> <input class="form-control" id="insuranceGroupID"
								name="insuranceGroupID" type="text"
								placeholder="Enter Insurance Group ID Here " required="required">
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Insurance
								Group Name</label> <input class="form-control" id="insuranceGroupName"
								name="insuranceGroupName" type="text"
								placeholder="Enter Insurance Group Name Here " required="required">


						</div>

						<div class="form-group">
							<label class="control-label" for="disabledInput">Subscriber
								Full Name</label> <input class="form-control" id="subscriberFullName"
								name="subscriberFullName" type="text"
								placeholder="Enter Subscriber Full Name Here" required="required">

						</div>


						<div class="form-group">
							<label class="control-label" for="disabledInput">Date Of
								Birth</label> <input class="form-control" id="dob" name="dob"
								type="text"
								placeholder="Enter Date Of Birth in mm/dd/yyyy format" required="required" pattern="\d{1,2}/\d{1,2}/\d{4}" name="dob" required="required" title="date should be in mm/dd/yyyy format">
						</div>

						<div class="form-group">
							<label for="select">status</label>
							<div>
								<select class="form-control" id="status" name="status">
									<option selected="selected" value="ACTIVE">Active</option>
									<option value="EXPIRED">Expired</option>

								</select>

							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

				


				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						>Add</button>
				</div>
					</form>
			</div>

		</div>
	</div>

	<!-- Modal Update insurance -->
	<div class="modal fade" id="updateInsuranceModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update Your Insurance</h4>
				</div>
				<div class="modal-body">
					<form name="updateInsurance" id="updateInsurance"
						class="updateInsurance">
						<input type="hidden" name="insuranceID" id="insuranceID" value="">
						<div class="form-group">
							<label class="control-label" for="disabledInput">Insurance
								Provider Name</label> <input class="form-control"
								id="insuranceProviderName" name="insuranceProviderName"
								type="text"
								placeholder="Enter Insurance Provider Name With State Name " required="required">
							<span class="help-block">eg : Aetna MA</span>
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Subscriber
								ID</label> <input class="form-control" id="subscriberID"
								name="subscriberID" type="text"
								placeholder="Enter Subscriber ID Here " required="required">
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Insurance
								Group ID</label> <input class="form-control" id="insuranceGroupID"
								name="insuranceGroupID" type="text"
								placeholder="Enter Insurance Group ID Here " required="required">
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Insurance
								Group Name</label> <input class="form-control" id="insuranceGroupName"
								name="insuranceGroupName" type="text"
								placeholder="Enter Insurance Group Name Here " required="required">


						</div>

						<div class="form-group">
							<label class="control-label" for="disabledInput">Subscriber
								Full Name</label> <input class="form-control" id="subscriberFullName"
								name="subscriberFullName" type="text"
								placeholder="Enter Subscriber Full Name Here" required="required">

						</div>


						<div class="form-group">
							<label class="control-label" for="disabledInput">Date Of
								Birth</label> <input class="form-control" id="dob" name="dob"
								type="text"
								placeholder="Enter Date Of Birth in mm/dd/yyyy format" required="required" pattern="\d{1,2}/\d{1,2}/\d{4}" name="dob" required="required" title="date should be in mm/dd/yyyy format">
						</div>

						<div class="form-group">
							<label for="select">status</label>
							<div>
								<select class="form-control" id="status" name="status">
									<option selected="selected" value="ACTIVE">Active</option>
									<option value="EXPIRED">Expired</option>

								</select>

							</div>
						</div>
						
												<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
							
							<input type="hidden" name="insuranceID" id="#insuranceID"
							 />

					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="insurances.updateInsurance('../insurances/update')">Update</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal new treatment -->
	<div class="modal fade" id="treatmentModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add New Treatment</h4>
				</div>
				<div class="modal-body">
					<form id="addNewTreatment" name="addNewTreatment"
						class="addNewTreatment">
						<input type="hidden" name="teethID" id="teethID" value="">
						<div class="form-group">
							<label for="select">Note</label>

							<div>
								<textarea class="form-control" rows="3" id="note" name="note"
									placeholder="type your note here.." maxlength="255"></textarea>
								<span class="help-block">Maximum 255 characters.</span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label" for="disabledInput">Treatment
								Expected Date</label> <input class="form-control"
								id="treatmentExpectedTime" name="treatmentExpectedTime"
								type="text" placeholder="mm/dd/yyyy" required="required" pattern="\d{1,2}[-/]\d{1,2}[-/]\d{4}" title="date should be in mm/dd/yyyy format">
						</div>
						<!-- <div class="form-group">
  <label class="control-label" for="disabledInput">Treatment Done Time</label>
  <input class="form-control" id="treatmentDoneTime" name="treatmentDoneTime" type="text" placeholder="mm/dd/yyyy" >
</div> -->
						<!-- <div class="form-group">
  <label class="control-label" for="disabledInput">Amount Paid</label>
  <input class="form-control" id="amountPaid" name="amountPaid" type="text" placeholder="mm/dd/yyyy" >
</div> -->
						<div class="form-group">
							<label class="control-label" for="disabledInput">Amount
								Expected</label> <input class="form-control" id="amountExpected"
								name="amountExpected" type="text" placeholder="200">
						</div>
						<!-- <div class="form-group">
  <label class="control-label" for="disabledInput">Amount Paid</label>
  <input class="form-control" id="amountPaid" name="amountPaid" type="text" placeholder="mm/dd/yyyy" >
</div> -->
						<div class="form-group">
							<label for="select">Treatment Status</label>
							<div>
								<select class="form-control" id="newTreatementStatusChange"
									name="status" onchange="newTreatmentStatus()">
									<option value="COMPLETED">Completed</option>
									<option selected="selected" value="PENDING">Pending</option>

								</select>

							</div>
						</div>
						<!-- 
        text hidden teethID
			        text note
					date treatmentExpectedTime
			        date treatmentDoneTime
					text amountExpected
			        text amountPaid	
					select status=COMPLETED
if status=PENDING
Request Parameters: text hidden teethID
			        text note
					date treatmentExpectedTime
			        text amountExpected
					select status=PENDING
         -->


						<div id="addNewTreatmentDiv"></div>





						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="treatments.sendTreatment('../treatments/create')">Add
						Treatment</button>
				</div>
			</div>

		</div>
	</div>



	<!-- Modal Change Status for requested Appointments -->
	<div class="modal fade" id="requestedAppointmentStatusModal"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Your Status</h4>
				</div>
				<div class="modal-body" id="statusCompleted">

					<div class="form-group">
						<label for="select">Treatment Status</label>
						<form id="updateRequestedAppointment"
							name="updateRequestedAppointment">
							<div>
								<input type="hidden" name="appointmentRequestID"
									id="appointmentRequestID" value=""> <select
									class="form-control" name="status" id="status">

									<option value="CANCEL" selected="selected">Cancel</option>



								</select> <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form>

					</div>
				</div>








			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="requestedappointments.updateRequestedAppointment('../appointmentrequests/update')">Update</button>
			</div>
		</div>

	</div>


	</div>
	</div>




	<!-- Modal new status for completed -->
	<div class="modal fade" id="appendStatusCompleted" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Please Provide Some More Information !</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="control-label" for="disabledInput">Amount
							Paid</label> <input class="form-control" id="disabledInput" type="text"
							placeholder="200.0">
					</div>
					<div class="form-group">
						<label class="control-label" for="disabledInput">Amount
							Expected</label> <input class="form-control" id="disabledInput"
							type="text" placeholder="300.0">
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Send</button>
				</div>
			</div>

		</div>
	</div>


	<!-- Modal new message -->
	<div class="modal fade" id="documentModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add New Document</h4>
				</div>
				<div class="modal-body">
					<form name="attachDocumentForm" id="attachDocumentForm"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label" for="disabledInput">File
								Name</label> <input class="form-control" id="uploadFileName" name="name"
								placeholder="document" type="text">
						</div>

						<div class="form-group">
							<label class="control-label" for="disabledInput">(.pdf ,
								.docx , .txt , .jpg etc)</label> <input class="form-control" type="file"
								name="file" id="file">
						</div>
						<input name="${_csrf.parameterName}" type="hidden"
							value="${_csrf.token}">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="senddocuments.postdocuments('../doc/upload')">Attach</button>
				</div>
			</div>

		</div>
	</div>
	<!-- Modal to Change Status of teeth -->
	<div class="modal fade" id="changeTeethStatusModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Your Teeth Status</h4>
				</div>
				<div class="modal-body" id="teethStatus">

					<div class="form-group">
						<label for="select">Teeth Status</label>
						<form id="updateTeethStatus" name="updateTeethStatus">

							<input type="hidden" name="teethID" id="teethStatusTeethID"
								value=""> <select class="form-control" name="status"
								id="status">

								<option value="NORMAL" selected="selected">Normal</option>
								<option value="EXTRACTED">Extracted</option>




							</select> <input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

						</form>

					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="treatments.updateTeethStatus('../teeth/update/status')">Update</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Change Status for teeth treatment -->

	<div class="modal fade" id="teethTreatmentsChangeStatusModal"
		name="teethTreatmentsChangeStatusModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->


			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal" type="button">&times;</button>

					<h4 class="modal-title">Change Your Status</h4>
				</div>


				<div class="modal-body" id="statusCompleted">
					<div class="form-group">
						<label for="select">Treatment Status</label>

						<form id="updateTeethTreatment" name="updateTeethTreatment">
							<div>
								<input id="treatmentUpdateTeethID" name="teethID" type="hidden"
									value=""> <input id="treatmentID" name="treatmentID"
									type="hidden" value=""> <select class="form-control"
									id="status" name="status">
									<option selected="selected" value="COMPLETED">
										Completed</option>
								</select>

								<div class="form-group">
									<label class="control-label" for="disabledInput">Note</label>

									<div>
										<textarea class="form-control" id="note" maxlength="1000"
											name="note" placeholder="type your note here.." rows="3"></textarea>
										<span class="help-block">Maximum 1000 characters.</span>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label" for="disabledInput">Treatment
										Done Date</label> <input class="form-control" id="treatmentDoneTime"
										name="treatmentDoneTime" placeholder="mm/dd/yyyy" type="text">
								</div>


								<div class="form-group">
									<label class="control-label" for="disabledInput">Amount
										Paid</label> <input class="form-control" id="amountPaid"
										name="amountPaid" placeholder="00" type="text">
								</div>
								<input name="${_csrf.parameterName}" type="hidden"
									value="${_csrf.token}">
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary"
						onclick="teethTreatments.updateTeethTreatment('../treatments/update')"
						type="button">Update</button>
				</div>
			</div>



		</div>
	</div>


	<!-- Modal Change Status for Confirmed Appointments -->
	<div class="modal fade" id="confirmededAppointmentStatusModal"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Your Status</h4>
				</div>
				<div class="modal-body" id="statusCompleted">

					<div class="form-group">
						<label for="select">Treatment Status</label>
						<form id="updateConfirmedAppointment"
							name="updateConfirmedAppointment">
							<div>
								<input type="hidden" name="appointmentID" id="appointmentID"
									value=""> <select class="form-control" name="status"
									id="status">

									<option value="COMPLETED" selected="selected">Completed</option>




								</select>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Note</label>
									<div>

										<textarea class="form-control" rows="3" id="note" name="note"
											placeholder="type your note here.." maxlength="1000" value=""></textarea>
										<span class="help-block">Maximum 1000 characters.</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label" for="disabledInput">Amount
										Paid</label> <input class="form-control" id="amountPaid"
										name="amountPaid" type="text" placeholder="300.0">
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form>

					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="confirmedappointments.updateConfirmedAppointment('../appointments/update')">Update</button>
			</div>
		</div>
		

	</div>




	


</body>
</html>