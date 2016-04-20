/**
 *
 */
var treatments = (function() {
    var treatmentsData;
    var displayTreatments = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	 $('#treatmentsForTemp1to16').empty();
        	 $('#treatmentsForTemp17to32').empty();
    	 $('#treatmentsForTemp1to16').append("<tr><td style='border-style: ridge ;' colspan='5' align='center'>Something is wrong . Please call us. </td></tr>");
    	}
        else
        	{

        	 $('#treatmentsForTemp1to16').empty();
        	 $('#treatmentsForTemp17to32').empty();

        	 $('#totalPrice').empty();


        	 $('#totalPrice').append("<i class='fa fa-usd' aria-hidden='true'></i>"+data.total);

        	   var temp=data.patientTeethStatus;

			          	  $.each(data.treatmentStatus, function(key,val) {


			          		  var template = $('#requestedTreatmentsTemp').html();

			          		  var template12 = $('#requestedTreatmentsTemp12').html();

			          		   data.counter=key;
			          		   data.treatmentStatus=val;
			          		   data.patientTeethStatus=temp[key];


			          		    var html = Mustache.to_html(template, data);
			          		    var html12 = Mustache.to_html(template12, data);
			          		     if(key<=16)
			          		    	 {


			          		    	 $('#treatmentsForTemp1to16').append(html);
			          		    	 }
			          		     else
			          		    	 {
			          		    	 $('#treatmentsForTemp17to32').append(html12);

			          		    	 }
        	});

    }

    }
    var getTreatments = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            treatmentsData = data;
            displayTreatments(treatmentsData);
        });
    }
    return {
        getTreatments: getTreatments,
        displayTreatments: displayTreatments
    };
}());
