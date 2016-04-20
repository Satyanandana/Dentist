/**
 * 
 */
function postAppointmentStatus(id,patientId,value)
{
	
		  alert("you haven't gave URL on changestatus.js function postStatus" + id+"Patient ID is" +  patientId);
		  $.post(url,
				  {"id":id ,"value":value},
				  
				  function(data){
		    		
					  showAppointments();
			  	
		    			  }
		    			  
		    		
		       
		    );
		  
}

function postRequestedAppointmentStatus(appointmentId,patientId,value)
{
	
		  alert("you haven't gave URL on changestatus.js function postStatus" + appointmentId + "Patient ID is " +patientId +"Value " +  value);
		  $.post(url,
				  {"appointmentId":appointmentId ,"value":value},
				  
				  function(data){
		    		
					  showAppointments();
			  	
		    			  }
		    			  
		    		
		       
		    );
		  
}