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
    return {
        getAppointments: getAppointments,
        displayAppointments: displayAppointments
    };
}());
