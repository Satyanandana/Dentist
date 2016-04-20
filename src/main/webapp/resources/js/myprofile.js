/**
 * 
 */
var myprofile = (function() {
    var myprofiledata;
    var displayProfileData = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	alert("SOMETHING WENT WRONG !");
    	}
        else
        	{
        
        	 
	    	 $("#showPersonalForm #firstName").html(data.firstName);
	    	 $("#showPersonalForm #middleName").html(data.middleName);
	    	 $("#showPersonalForm #lastName").html(data.lastName);
	    	 $("#showPersonalForm #dateOfBirth").html(data.dateOfBirth);
	    	 
	    	
	    	
	    	 $("#hidePersonalForm #firstName").attr("placeholder", data.firstName);
	    	 $("#hidePersonalForm #middleName").attr("placeholder", data.middleName);
	    	 $("#hidePersonalForm #lastName").attr("placeholder", data.lastName);
	    	 $("#hidePersonalForm #dateOfBirth").attr("placeholder", data.dateOfBirth);
	    	 
	    	 $("#showAddressForm #address1").html(data.homeAddress.address1);
	    	 $("#showAddressForm #address2").html(data.homeAddress.address2);
	    	 $("#showAddressForm #city").html(data.homeAddress.city);
	    	 $("#showAddressForm #state").html(data.homeAddress.state);
	    	 $("#showAddressForm #zipcode").html(data.homeAddress.zipcode);
	    	
	    	 $("#hideAddressForm #address1").attr("placeholder", data.homeAddress.address1);
	    	 $("#hideAddressForm #address2").attr("placeholder", data.homeAddress.address2);
	    	 $("#hideAddressForm #city").attr("placeholder", data.homeAddress.city);
	    	 $("#hideAddressForm #state").attr("placeholder", data.homeAddress.state);
	    	 $("#hideAddressForm #zipcode").attr("placeholder", data.homeAddress.zipcode);
	    	 
	    	 $("#showContactForm #email").html(data.email);
	    	 $("#showContactForm #phoneNumber").html(data.phoneNumber);
	    	 
	    	 $("#hideContactForm #email").attr("placeholder", data.email);
	    	 $("#hideContactForm #phoneNumber").attr("placeholder", data.phoneNumber);
	    	 
	    	 $("#showEmergencyForm #name").html(data.EmergencyContact.name);
	    	 $("#showEmergencyForm #phoneNumber").html(data.EmergencyContact.phoneNumber);
	    	 $("#showEmergencyForm #relation").html(data.EmergencyContact.relation);
	    	 
	    	
	    	 
	    	 $("#hideEmergencyForm #name").attr("placeholder", data.EmergencyContact.name);
	    	 $("#hideEmergencyForm #phoneNumber").attr("placeholder", data.EmergencyContact.phoneNumber);
	    	 $("#hideEmergencyForm #relation").attr("placeholder", data.EmergencyContact.relation);
    }
        
    }
    var getProfileData = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            myprofiledata = data;
            displayProfileData(myprofiledata);
        });
    }
    
    var sendProfileData = function(path) {
    	 
    	alert("not working..");
    	
    	
    	
    	
    	
      /*  $.ajax({
            url: path,
            method: 'POST'
        }).then(function(data) {
            myprofiledata = data;
            displayProfileData(myprofiledata);
        });*/
    }
    return {
        getProfileData: getProfileData,
        displayProfileData: displayProfileData,
        sendProfileData: sendProfileData
        
    };
}());
