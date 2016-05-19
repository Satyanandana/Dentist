/**
 * 
 */
var teethTreatments = (function() {
    var treatmentsDataForTeeth;
    var displayTreatmentsForTeeth = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data.treatments))
    	{
        	
        	$("#teethTreatment").show();
        	 $('#teethDetails').empty();
        	 $('#teethTreatmentDetails').empty();
        	 var template = $('#teethDetailsTemp').html();
    			console.log(data);
    			    var html = Mustache.to_html(template, data);
    			    $('#teethDetails').append(html);
        	 
    	
    	 $('#teethTreatmentDetails').append("<tr><td style='border-style: ridge ;' colspan='5' align='center'>You not have any treatment done on this teeth. Please Add New Treatment By CLicking add treatment   </td></tr>");
    	 
    	}
        else
        	{
        
        	 
       	 $("#teethTreatment").show();
       
       		 $('#teethDetails').empty();
       		 
       		 $('#teethTreatmentDetails').empty();
       	       
       			  var template = $('#teethDetailsTemp').html();
       			console.log(data);
       			    var html = Mustache.to_html(template, data);
       			    $('#teethDetails').append(html);
       			    
       			    $.each(data.treatments, function(key,val) {
       			         
       			    	
       			    	if(val.status=='COMPLETED')
       				   {
       				    
       				   	val.hidden="hidden";
       				   
       				   }
       			 	      val.teethID=data.teeth.teethID;
       					  var template = $('#teethDetailsTreatmentTemp').html();
       					
       					    var html = Mustache.to_html(template, val);
       					    $('#teethTreatmentDetails').append(html);
       					    
       				});
       			    
       			    
       			    
       		
       	
       	 
        	
        	
       	 
    }
        
    }
    var getTreatmentsForTeeth = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            treatmentsDataForTeeth = data;
            displayTreatmentsForTeeth(treatmentsDataForTeeth);
        });
    }
    
    var updateTeethTreatment = function(path) {
  	  //$('#updateTeethTreatment').serialize());
  	   $.ajax({
             url: path,
             method: 'POST',
             data:$('#updateTeethTreatment').serialize(),
             contentType: "application/x-www-form-urlencoded; charset=UTF-8"
         }).then(function(data) {
      	   
      	   $('#teethTreatmentsChangeStatusModal').modal('toggle');
      	   showMyTeethDetails(data.id);
        	 
              
         });
  }
    return {
        getTreatmentsForTeeth: getTreatmentsForTeeth,
        displayTreatmentsForTeeth: displayTreatmentsForTeeth,
        updateTeethTreatment:updateTeethTreatment
    };
}());
