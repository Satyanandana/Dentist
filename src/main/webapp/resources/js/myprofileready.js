/**
 * 
 */

$(document).ready(function(){
	 
	showProfile();
	myprofile.getProfileData(("../patient/personalinfo"));
	 
	
	

  

	/*
	 * All Post Functions below 
	 * 
	 * */
	
	$("#updatePersonal").click(function(){
		document.getElementById('spinner').style.visibility = 'visible';
	    post("rest/patient/personal","p");
	    showProfile();
	    get("rest/patient/personal","p");
	    
	});
	
	$("#updateAddress").click(function(){
		document.getElementById('spinner').style.visibility = 'visible';
	    post("rest/patient/address","a");
	    showProfile();
	    get("rest/patient/address","a");
	    
	});
	
	$("#updateContact").click(function(){
		document.getElementById('spinner').style.visibility = 'visible';
	    post("rest/patient/contact","c");
	    showProfile();
	    get("rest/patient/contact","c");
	    
	});
	
	$("#updateEmergency").click(function(){
		document.getElementById('spinner').style.visibility = 'visible';
	    post("rest/patient/emergency","e");
	    showProfile();
	    get("rest/patient/emergency","e");
	    
	});
    
});

