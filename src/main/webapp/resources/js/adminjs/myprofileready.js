/**
 * 
 */

$(document).ready(function(){
	 
	 
	   
	
	
	 
	
	
 	 

	  var action = $("#action");
	  
	  
	  if( action.html()==="" || action.html()=== "profile"){
		 
		  showProfile();
		  
	  }else if(action.html()==="messages"){
		 
		  showMessages();
	  }else if(action.html()==="appointments"){
		 
		  showAppointments();
	  }else if(action.html()==="treatments"){
		 
		  showTreatments();
	  }else if(action.html()==="insurance"){
		 
		  showInsurance();
	  }else if(action.html()==="documents"){
		 
		  showPayment();
	  }else if(action.html()==="settings"){
		 
		  showSettings();
	  }
	  
	  
  

	
    
});

