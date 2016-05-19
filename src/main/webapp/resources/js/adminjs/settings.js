/**
 * 
 */
var settings = (function() {
   
    var postNewPassword = function(path) {
    	 //$('#changePasswordAdmin').serialize());
       $.ajax({
            url: path,
            method: 'POST',
            data: $('#changePasswordAdmin').serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        }).then(function(data) {
        	if(data.Success)
        	{
        	
        	//"Password Changed Successfully . ");
        	
        
        	}else
        		{
        			
        		//"Something went wrong . try again .");
        		}
        	
        	
        	
             
        });
    }
    return {
    	postNewPassword:postNewPassword
        
    };
}());
