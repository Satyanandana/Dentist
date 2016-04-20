/**
 * 
 */
var teethTreatments = (function() {
    var treatmentsDataForTeeth;
    var displayTreatmentsForTeeth = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data.treatments))
    	{
        	alert("here");
        	$("#teethTreatment").show();
        	 $('#teethDetails').empty();
        	 $('#teethTreatmentDetails').empty();
        	 var template = $('#teethDetailsTemp').html();
    			console.log(data);
    			    var html = Mustache.to_html(template, data.teeth);
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
       			    var html = Mustache.to_html(template, data.teeth);
       			    $('#teethDetails').append(html);
       			    $.each(data.treatments, function(key,val) {
       			        
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
    return {
        getTreatmentsForTeeth: getTreatmentsForTeeth,
        displayTreatmentsForTeeth: displayTreatmentsForTeeth
    };
}());
