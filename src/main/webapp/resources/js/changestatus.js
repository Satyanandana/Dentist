/**
 * 
 */
function postAppointmentStatus(appointmentID,patientId,value)
{
	
	
	$('#appointmentID').attr("value",appointmentID);
	 $('#confirmededAppointmentStatusModal').modal('show');
		  
		  
}

function postRequestedAppointmentStatus(appointmentRequestID,patientId,value)
{
	
		 
		   
		  $('#appointmentRequestID').attr("value",appointmentRequestID);
			 $('#requestedAppointmentStatusModal').modal('show');
}

function changestatusOfTeethTreatment(teethID,treatmentId)
{
	  
	  $('#treatmentUpdateTeethID').attr("value",teethID);
	  $('#treatmentID').attr("value",treatmentId);
		 $('#teethTreatmentsChangeStatusModal').modal('show');
	 
		  
}

function newTreatmentStatus()
{
	
		 
		   
		  
		  
		  var status=$('#newTreatementStatusChange').val();
		  if(status=='COMPLETED')
			  {
			  
			  
			  var template = $('#newTreatmentTempForStatus').html();
     		    
     		    $('#addNewTreatmentDiv').append(template);
			    
     		    
			  }
		  else
			  {
			  $('#addNewTreatmentDiv').empty();
			    
			  }
}