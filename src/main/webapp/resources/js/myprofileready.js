/**
 * 
 */

$(document).ready(function(){
	 
	 
	   
	
	
	 
	$("#hidePersonalForm1").on("submit", function (e) {
	    e.preventDefault();
	    myprofile.sendPersonalProfileData('../patient/update/personalinfo');
	    
	});
	$("#hideAddressForm1").on("submit", function (e) {
	    e.preventDefault();
	    myprofile.sendAddressProfileData('../patient/update/addressinfo')
	    
	});
	$("#hideContactForm1").on("submit", function (e) {
	    e.preventDefault();
	    myprofile.sendContactProfileData('../patient/update/contactinfo')
	    
	});
	$("#hideEmergencyForm1").on("submit", function (e) {
	    e.preventDefault();
	    myprofile.sendEmergencyProfileData('../patient/update/emergencycontactinfo');
	    
	});
	
	$("#sendMessage1").on("submit", function (e) {
	    e.preventDefault();
	    sentmessages.sendMessage('../sentmessages/sendtodoc');
	    
	    
	});
	
	$("#newPasswordForm").on("submit", function (e) {
	    e.preventDefault();
	    settings.postNewPassword('../settings/updatepassword');
	    
	    
	});
	
		$("#addNewInsurance").on("submit", function (e) {
	    e.preventDefault();
	    insurances.addInsurance('../insurances/create');
	    
	    
	});

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

