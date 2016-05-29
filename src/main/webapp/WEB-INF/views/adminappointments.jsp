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
<script type="text/javascript" src="<c:url value='/resources/js/adminjs/changestatus.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/adminjs/confirmedappointments.js'/>"></script>
</head>
<body>
<%@include file="template.jsp"%>
	<%@include file="dynamicheader.jsp"%>
<div class="container-fluid">
		<br />
		<h1>Admin Panel</h1>
		<br />



<div class="row">
<div class="col-xs-12">

 <div class="thumbnail">
    <center>
      <div class="caption">
        <h3>All Appointments</h3>
        
         
         
      </div>
      </center>
    </div>
  
    
    <table id="appointmentsDetails" class="display" cellspacing="0" width="100%">
        <thead>
 
      
            <tr>
            	<th>Patient ID</th>
    			<th># ID</th>
            	<th>Appointment Start Time</th>
                <th>Expected Amount</th>
                 <th>Amount Paid</th>
                
                <th>view note</th>
                <th>Status</th>
                <th></th>
                
                
                
               
            </tr>
        </thead>
        
        
        <tfoot>
            <tr>
 				<th>Patient ID</th>
    			<th># ID</th>
            	<th>Appointment Start Time</th>
                <th>Expected Amount</th>
                 <th>Amount Paid</th>
                
                <th>view note</th>
                <th>Status</th>
                <th></th>            </tr>
        </tfoot>
    </table>
    
</div>
</div>

</div>

<%@include file="footer.jsp"%>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/mustache.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/admin.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js'/>"></script> --%>






<script type="text/javascript">


$(document).ready(function() {
	
	

    
	$('#appointmentsDetails').DataTable( {
	    ajax: '../appointments/allappointments',
	    columns: [




	              {data : 'patientID',"render": function ( data, type, full, meta ) {
	    	  	      return '<a href="view/'+data+'" target="_blank"> '+data+' </a>';
	    		    }},
	        
	        { data: 'appointmentID' },
	        
	        { data: 'appointmentStartTime' },
	        { data: 'expectedAmount' },
	        { data: 'amountPaid' },
	        
	        
	        
	        
	        {data : 'note',"render": function ( data, type, full, meta ) {
	        	
	  	  	      return '<a href="#" onclick="showNoteModal('+"'"+data+"'"+')"> view </a>';
	  		    }},
	  		  { data: 'status' },
	         {data : 'docID',"render": function ( data, type, full, meta ) {
	        	
  	/*   	      return '<button  class="btn btn-primary btn-xs" id="appointmentsRequesedStatusChange" onclick="postAppointmentStatus('+full.appointmentRequestID+','+ full.patientID+','+"'"+full.note+"'"+',this.value)"  >Change Status</button>'; */
                  var hidden='visible';
  	              if(full.status=="COMPLETED")
  	            	  hidden='hidden';
  	          
  	
  				  return '<button  class="btn btn-primary btn-xs" id="appointmentsStatusChange" onclick="postAppointmentStatus('+full.appointmentID+','+full.patientID+',this.value,'+"'"+full.note+"'"+')"  style="visibility:'+hidden+';">Change Status</button>';
  		    }} 
	        
	       
	       
	    ]
	
	   
	  
	} );
	
	
} );
</script>
<script type="text/javascript">
function showNoteModal(msg)
{
	
	$('#messageData').html(msg);
	
	 $('#receivedmessageModal').modal('show');
}
</script>


	<div class="modal fade" id="receivedmessageModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						
<p id="messageData"></p>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
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
									value="">
									<p id="patientID" style="display: none;"></p>
									 <select class="form-control" name="status"
									id="status">

									<option value="COMPLETED" selected="selected">Completed</option>
									<option value="CANCEL" selected="selected">Cancel</option>




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
					onclick="confirmedappointments.updateConfirmedAppointment('../appointments/update/')">Update</button>
			</div>
		</div>

	</div>

<script type="text/template" id="requestedAppointmentStatusTemp">
 
<div class="form-group" >
<label class="control-label" for="disabledInput">Note</label>
<div>

	<textarea class="form-control" rows="3" id="note" name="note"
		placeholder="type your note here.." maxlength="1000" value="{{note}}"></textarea>
	<span class="help-block">Maximum 1000 characters.</span>
</div>
</div>
<div class="form-group" >
<label class="control-label" for="disabledInput">Amount
	Expected</label> <input class="form-control" id="expectedAmount"
	name="expectedAmount" type="text" placeholder="300.0">
</div>
</script>

</body>
</html>