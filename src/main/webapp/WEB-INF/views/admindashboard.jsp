<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<c:url value='https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css'/>" media="screen">
	

<title>Dr Kang's Dental</title>
</head>
<body>
<%@include file="template.jsp"%>
	<%@include file="dynamicheader.jsp"%>
<div class="container-fluid">
		<br />
		<br />
		<br />
		<br />
		<br />
		<h1>Admin Panel <a data-toggle="modal" data-target="#changePasswordModal"><small>(Change Password)</small></a></h1>
		<br />
<div class="row">
<a href="<c:url value='/admin/appointments'/>" target="_blank">
<div class="col-xs-6">

<div class="thumbnail">
    <center>
      <div class="caption">
        <h3>Appointments</h3>
        <i class="fa fa-check" style="color:lightblue;font-size: 100px;"></i>
        
      
        
      </div>
      </center>
    </div>
    
</div>
</a>
<a href="<c:url value='/admin/appointmentrequests'/>" target="_blank">
<div class="col-xs-6">
<div class="thumbnail">
    <center>
      <div class="caption">
        <h3>Appointment Requests</h3>
        <i class="fa fa-exclamation-triangle" style="color:lightblue;font-size: 100px;"></i>
        
        
      </div>
      </center>
    </div>
</div>
</a>
</div>

<div class="row">
<a href="<c:url value='/admin/receivedmessages'/>" target="_blank">
<div class="col-xs-6">
 
    <div class="thumbnail">
    <center>
      <div class="caption">
        <h3>Received Messages</h3>
        
          <i class="fa fa-reply" style="color:lightblue;font-size: 100px;"></i>
        
      </div>
      </center>
    </div>
 
</div>
</a>
<a href="<c:url value='/admin/receiveddocuments'/>" target="_blank">
<div class="col-xs-6">
 
    <div class="thumbnail">
    <center>
      <div class="caption">
        <h3>Received Documents</h3>
        
        <i class="fa fa-file" aria-hidden="true" style="color:lightblue;font-size: 100px;"></i>
        
      </div>
      </center>
    </div>
 
</div>
</a>
</div>
<br/>
<br/>
<br/>
<div class="row">
<div class="col-xs-12">

 <div class="thumbnail">
    <center>
      <div class="caption">
        <h3>All Patient Details</h3>
        
        <span class="glyphicon glyphicon-user" style="color:lightblue;font-size: 100px;"></span> 
         
      </div>
      </center>
    </div>
  
    
    <table id="patientDetails" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>User ID</th>
                <th>First Name</th>
                <th>Middle Name</th>
                <th>Last Name</th>
                <th>Date Of Birth</th>
                <th>Phone Number</th>
                <th>Email</th>
                 <th></th>
            </tr>
        </thead>
        
        
        <tfoot>
            <tr>
                <th>User ID</th>
                <th>First Name</th>
                <th>Middle Name</th>
                <th>Last Name</th>
                <th>Date Of Birth</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th></th>
               
            </tr>
        </tfoot>
    </table>
    
</div>
</div>



<%@include file="footer.jsp"%>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/mustache.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/admin.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/adminjs/settings.js'/>"></script>





<script type="text/javascript">

$(document).ready(function() {
	$('#patientDetails').DataTable( {
	    ajax: '../patient/allpatients',
	    columns: [
	        { data: 'userID',  },
	        { data: 'firstName' },
	        { data: 'middleName' },
	        { data: 'lastName' },
	        { data: 'dateOfBirth' },
	        { data: 'phoneNumber' },
	        { data: 'email' },
	        {data : 'userID',"render": function ( data, type, full, meta ) {
	  	      return '<a href="view/'+data+'" target="_blank">view</a>';
		    }}
	       
	    ]
	
	   
	  
	} );
	
	
} );
</script>

	<!-- Modal change password -->
	<div class="modal fade" id="changePasswordModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Your Password (Atleast one upper case,one lowercase,one number and the length between 6 to 10)</h4>
				</div>
				<div class="modal-body">
				<div class="alert alert-dismissible alert-danger" id="errorMessage" style="display: none;">
  
  
</div>
				
					<form name="changePasswordAdmin" id="changePasswordAdmin">
							<div class="form-group">
										<label for="first-name">Your Old Password</label> <input
											type="password" class="form-control" name="oldPwd"
											id="oldPwd" value="" />
									</div>
									<div class="form-group">
										<label for="first-name">New Password</label> <input
											type="password" class="form-control" name="newPwd"
											id="newPwd" value="" />
									</div>

					
					
					
												<input name="${_csrf.parameterName}" type="hidden"
							value="${_csrf.token}">
					</form>
				
				
				</div>
				<div class="modal-footer">
						<div class="form-group">
					<button type="submit" class="btn btn-primary"
									onclick="settings.postNewPassword('../settings/updateadminpassword')">Change
									Password</button>
									</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>