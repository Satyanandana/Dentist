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
    return {
        getAppointments: getAppointments,
        displayAppointments: displayAppointments
    };
}());
