/**
 * 
 */
var requestedappointments = (function() {
    var appointmentsData;
    var displayAppointments = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	 $('#requestedAppointmentsForTemp').empty();
    	 $('#requestedAppointmentsForTemp').append("<tr><td style='border-style: ridge ;' colspan='5' align='center'>You Don't Have Any Requested Appointments . Yo can make one by clicking SCHEDULE NEW . </td></tr>");
    	}
        else
        	{
        
        	 $('#requestedAppointmentsForTemp').empty();
       	  var i=1;
       	  $.each(data, function(key,val) {
              
       		  var template = $('#requestedAppointmentsTemp').html();
       		  
       		  val.counter=i;
       		if(val.status=='ACCEPTED' || val.status=='CANCELLED')
			   {
			    
			   	val.hidden="hidden";
			   }
       		
       		  i++;
       		    var html = Mustache.to_html(template, val);
       		    $('#requestedAppointmentsForTemp').append(html);
       	});
       	 
    }
        
    }
    var getAppointments = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            appointmentsData = data;
            displayAppointments(appointmentsData);
        });
    }
    
    var sendRequestedAppointment = function(path) {
  	  //$('#appointmentRequestForm').serialize());
  	   $.ajax({
             url: path,
             method: 'POST',
             data:$('#appointmentRequestForm').serialize(),
             contentType: "application/x-www-form-urlencoded; charset=UTF-8"
         }).then(function(data) {
      	    
        	 top.location.href = "view?action=appointments";
        	 
              
         });
  }
    var updateRequestedAppointment = function(path) {
    	  //$('#updateRequestedAppointment').serialize());
    	   $.ajax({
               url: path,
               method: 'POST',
               data:$('#updateRequestedAppointment').serialize(),
               contentType: "application/x-www-form-urlencoded; charset=UTF-8"
           }).then(function(data) {
        	   
        	   $('#requestedAppointmentStatusModal').modal('toggle');
        	   showAppointments();
          	 
                
           });
    }
    return {
        getAppointments: getAppointments,
        displayAppointments: displayAppointments,
        sendRequestedAppointment:sendRequestedAppointment,
        updateRequestedAppointment:updateRequestedAppointment
    };
}());
