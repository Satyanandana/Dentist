/**
 * 
 */
var insurances = (function() {
    var insurancesData;
    var displayInsurances = function(data) {
    	
        console.log(data);
      
        if(jQuery.isEmptyObject(data))
        	{
        	$('#myinsurances').empty();
        	 $('#myinsurances').append("<center><p style='border-style: ridge ;'>You Don't Have Any Insurance Added , Please Add insurance by Clicking Add New  . </p></center>");
        	}
        else
        	{

        	
        	 $('#myinsurances').empty();
       	  $.each(data, function(key,val) {
              
       		  var template = $('#insurancesTemp').html();
       		
       		    var html = Mustache.to_html(template, val);
       		    $('#myinsurances').append(html);
       	});
        	
        	
        	}
        
    }
    var getInsurances = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            insurancesData = data;
            displayInsurances(insurancesData);
        });
    }
    var addInsurance = function(path) {
  	  //$('#addNewInsurance').serialize());
  	   $.ajax({
             url: path,
             method: 'POST',
             data:$('#addNewInsurance').serialize(),
             contentType: "application/x-www-form-urlencoded; charset=UTF-8"
         }).then(function(data) {
        	 
        	 $('#insuranceModal').modal('toggle');
        	 showInsurance();
         	
              
         });
  }
    
    var updateInsurance = function(path) {
    	  //$('#updateInsurance').serialize());
    	   $.ajax({
               url: path,
               method: 'POST',
               data:$('#updateInsurance').serialize(),
               contentType: "application/x-www-form-urlencoded; charset=UTF-8"
           }).then(function(data) {
          	 
          	 $('#updateInsuranceModal').modal('toggle');
          	 showInsurance();
           	
                
           });
    }
    return {
        getInsurances: getInsurances,
        displayInsurances: displayInsurances,
        addInsurance:addInsurance,
        updateInsurance:updateInsurance
    };
}());
