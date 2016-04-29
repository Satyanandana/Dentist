/**
 * 
 */







function post(url,type)
{
	if(type=="p")
	    {
		
		  $.post(url,
				  $("#hidePersonalForm1").serialize(),
				  
				  function(data){
		    		
			  		document.getElementById('spinner').style.visibility = 'hidden';
			  	
		    			  }
		    			  
		    		
		       
		    );
	    }
	
	if(type=="a")
    {
	
	  $.post(url,
			  $("#hideAddressForm1").serialize(),
			  
			  function(data){
	    		
		  		document.getElementById('spinner').style.visibility = 'hidden';
		  	
	    			  }
	    			  
	    		
	       
	    );
    }
	
	if(type=="c")
    {
	
	  $.post(url,
			  $("#hideContactForm1").serialize(),
			  
			  function(data){
	    		
		  		document.getElementById('spinner').style.visibility = 'hidden';
		  	
	    			  }
	    			  
	    		
	       
	    );
    }
	  
	
	
	if(type=="e")
    {
	
	  $.post(url,
			  $("#hideEmergencyForm1").serialize(),
			  
			  function(data){
	    		
		  		document.getElementById('spinner').style.visibility = 'hidden';
		  	
	    			  }
	    			  
	    		
	       
	    );
    }
	  
	  
	  
}
	

