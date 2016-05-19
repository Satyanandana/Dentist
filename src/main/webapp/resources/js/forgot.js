/**
 * 
 */
var forgotPassword= (function() {
   
    var sendEmailPassword = function(path) {
    	 //$('#forgotPasswordForm').serialize());
       $.ajax({
            url: path,
            method: 'POST',
            data: $('#forgotPasswordForm').serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        }).then(function(data) {
        	if(data.success)
        	{
        	
        	
        	$("#email").val("");
        	$('#successDiv p').html("");
        	$('#successDiv p').html(data.success);
        	$('#successDiv').show();
        	}if(data.noaccount)
        		{
        		$('#successDiv p').html("");
        		  
        		  $("#email").val("");
        		  $('#successDiv p').html(data.noaccount);
              	$('#successDiv').show();
        		
        		}
        	if(data.invalid)
        		{
        		$('#successDiv p').html("");
        		  $("#email").val("");
        		  $('#successDiv p').html(data.invalid);
              	$('#successDiv').show();
        		
        		}
        	
        	
        	
             
        });
    };
  
    return {
    	sendEmailPassword:sendEmailPassword
        
    };
}());