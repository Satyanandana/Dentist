/**
 * 
 */
function postAppointmentStatus(appointmentID,patientId,value,note)
{
	
	
	
	$('#appointmentID').attr("value",appointmentID);
	$('#updateConfirmedAppointment textarea').val(note);
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

function changeTeethStatus(teethID)
{
	  
	  $('#teethStatusTeethID').attr("value",teethID);
	  
		 $('#changeTeethStatusModal').modal('show');
	 
		  
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