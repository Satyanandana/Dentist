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
	

<title>Insert title here</title>
</head>
<body>
<%@include file="template.jsp"%>
	<%@include file="dynamicheader.jsp"%>
<div class="container-fluid">
		<br />
		<h1>Admin Panel</h1>
		<br />
<div class="row">
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
</div>

<div class="row">
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
</div>

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
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/mustache.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/admin.js'/>"></script>





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
</body>
</html>