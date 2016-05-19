/**
 * 
 */
var confirmedappointments = (function() {
    var appointmentsData;
    var displayAppointments = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	 $('#appointmentsForTemp').empty();
    	 $('#appointmentsForTemp').append("<tr><td style='border-style: ridge ;' colspan='5' align='center'>You Don't Have Any Confirmed Appointments . </td></tr>");
    	}
        else
        	{
        
        	 $('#appointmentsForTemp').empty();
       	  var i=1;
       	  $.each(data, function(key,val) {
              
       		  var template = $('#appointmentsTemp').html();
       		  val.counter=i;
       		   if(val.status=='COMPLETED')
       			   {
       			    
       			   	val.hidden="hidden";
       			   }
       		  
       		  i++;
       		 
       		    var html = Mustache.to_html(template, val);
       		    $('#appointmentsForTemp').append(html);
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
    
    var updateConfirmedAppointment = function(path) {
  	  //$('#updateConfirmedAppointment').serialize());
  	   $.ajax({
             url: path,
             method: 'POST',
             data:$('#updateConfirmedAppointment').serialize(),
             contentType: "application/x-www-form-urlencoded; charset=UTF-8"
         }).then(function(data) {
      	   
      	   $('#confirmededAppointmentStatusModal').modal('toggle');
      	   showAppointments();
        	 
              
         });
  }
    return {
        getAppointments: getAppointments,
        displayAppointments: displayAppointments,
        updateConfirmedAppointment:updateConfirmedAppointment
    };
}());
