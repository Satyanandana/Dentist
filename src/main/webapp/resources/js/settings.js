/**
 * 
 */
var settings = (function() {
   
    var postNewPassword = function(path) {
    	 //$('#newPasswordForm').serialize());
    	$('#successDiv').hide();
    	$('#errorDiv').hide();
    	
       $.ajax({
            url: path,
            method: 'POST',
            data: $('#newPasswordForm').serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        }).then(function(data) {
        	if(data.Success)
        	{
        	showSettings();
        	/*$('#newPasswordForm').hide();*/
        	$("#oldPwd").val("");
  		  $("#newPwd").val("");
        	$('#successDiv').show();
        	}else
        		{
        		  $("#oldPwd").val("");
        		  $("#newPwd").val("");
        		$('#errorDiv p').html(data.error);
        		$('#errorDiv').show();
        		
        		}
        	
        	
        	
             
        });
    }
    return {
    	postNewPassword:postNewPassword
        
    };
}());
