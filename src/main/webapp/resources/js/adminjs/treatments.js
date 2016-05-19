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


        	 $('#totalPrice').append("<i class='fa fa-usd' aria-hidden='true'></i> &nbsp;&nbsp;"+data.total);

        	 

			          		  var template = $('#requestedTreatmentsTemp').html();

			          		  var template12 = $('#requestedTreatmentsTemp12').html();
			          		  
			          		
			          		var i;
			          		var temp=data.patientTeethStatus;
			         	   var treatments=data.treatmentStatus;
			          		for (i = 1; i <= 16; i++) {
			          		    
			          			data.treatmentStatus=treatments[i];
			          			
			          			data.counter=i;
			          		   
			          		   data.patientTeethStatus=temp[i];
			          		 
			          		   
			          		 var html = Mustache.to_html(template, data);
			          		$('#treatmentsForTemp1to16').append(html);
			          			
			          		}
			          		
			          		var j;
			          			for (j = 32;j>= 17; j--) {
			          		    
			          			data.treatmentStatus=treatments[j];
			          			
			          			data.counter=j;
			          		   
			          		   data.patientTeethStatus=temp[j];
			          		 
			          		   
			          		 var html12= Mustache.to_html(template12, data);
			          		$('#treatmentsForTemp17to32').append(html12);
			          			
			          		}
			          		
			          	
			          		 
			          		


			          		   
			          		    

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
    var sendTreatment = function(path) {
    	  //$('#addNewTreatment').serialize());
    	   $.ajax({
               url: path,
               method: 'POST',
               data:$('#addNewTreatment').serialize(),
               contentType: "application/x-www-form-urlencoded; charset=UTF-8"
           }).then(function(data) {
        	    
        	   $('#treatmentModal').modal('toggle');
        	   showTreatments();
                
           });
    }
    var updateTeethStatus = function(path) {
  	  //$('#updateTeethStatus').serialize());
  	   $.ajax({
             url: path,
             method: 'POST',
             data:$('#updateTeethStatus').serialize(),
             contentType: "application/x-www-form-urlencoded; charset=UTF-8"
         }).then(function(data) {
      	    
      	   $('#changeTeethStatusModal').modal('toggle');
      	 treatments.getTreatments("../treatments/status");
              
         });
  }
    return {
        getTreatments: getTreatments,
        displayTreatments: displayTreatments,
        sendTreatment:sendTreatment,
        updateTeethStatus:updateTeethStatus
    };
}());
