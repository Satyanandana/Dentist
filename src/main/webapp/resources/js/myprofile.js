/**
 *  
 */
var myprofile = (function() {
	var myprofiledata;
	var displayProfileData = function(data) {
		console.log(data);
		if (jQuery.isEmptyObject(data)) {
			//"SOMETHING WENT WRONG !");
		} else {

			$("#showPersonalForm #firstName").html(data.firstName);
			$("#showPersonalForm #middleName").html(data.middleName);
			$("#showPersonalForm #lastName").html(data.lastName);
			$("#showPersonalForm #dateOfBirth").html(data.dateOfBirth);

			$("#hidePersonalForm #firstName").attr("value", data.firstName);
			$("#hidePersonalForm #middleName").attr("value", data.middleName);
			$("#hidePersonalForm #lastName").attr("value", data.lastName);
			$("#hidePersonalForm #dob").attr("value", data.dateOfBirth);

			$("#showAddressForm #address1").html(data.homeAddress.address1);
			$("#showAddressForm #address2").html(data.homeAddress.address2);
			$("#showAddressForm #city").html(data.homeAddress.city);
			$("#showAddressForm #state").html(data.homeAddress.state);
			$("#showAddressForm #zipcode").html(data.homeAddress.zipcode);

			$("#hideAddressForm #address1").attr("value",
					data.homeAddress.address1);
			$("#hideAddressForm #address2").attr("value",
					data.homeAddress.address2);
			$("#hideAddressForm #city").attr("value", data.homeAddress.city);
			$("#hideAddressForm #state").attr("value", data.homeAddress.state);
			$("#hideAddressForm #zipcode").attr("value",
					data.homeAddress.zipcode);

			$("#showContactForm #email").html(data.email);
			$("#showContactForm #phoneNumber").html(data.phoneNumber);

			$("#hideContactForm #email").attr("value", data.email);
			$("#hideContactForm #phoneNumber").attr("value", data.phoneNumber);

			$("#showEmergencyForm #name").html(data.EmergencyContact.name);
			$("#showEmergencyForm #phoneNumber").html(
					data.EmergencyContact.phoneNumber);
			$("#showEmergencyForm #relation").html(
					data.EmergencyContact.relation);

			$("#hideEmergencyForm #emergencyContactName").attr("value",
					data.EmergencyContact.name);
			$("#hideEmergencyForm #emergencyContactNumber").attr("value",
					data.EmergencyContact.phoneNumber);
			$("#hideEmergencyForm #emergencyContactRelation").attr("value",
					data.EmergencyContact.relation);
		}

	}
	var getProfileData = function(path) {
		$.ajax({
			url : path,
			method : 'GET'
		}).then(function(data) {
			myprofiledata = data;
			displayProfileData(myprofiledata);
		});
	}

	var sendPersonalProfileData = function(path) {
		//$('#hidePersonalForm1').serialize());
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#hidePersonalForm1').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"
		}).then(function(data) {
			showProfile();
			myprofile.getProfileData(("../patient/personalinfo"));

		});
	}
	var sendAddressProfileData = function(path) {
		//$('#hideAddressForm1').serialize());
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#hideAddressForm1').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"

		}).then(function(data) {
			showProfile();
			myprofile.getProfileData(("../patient/personalinfo"));

		});
	}
	var sendContactProfileData = function(path) {
		//$('#hideContactForm1').serialize());
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#hideContactForm1').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"

		}).then(function(data) {
			showProfile();
			myprofile.getProfileData(("../patient/personalinfo"));

		});
	}
	var sendEmergencyProfileData = function(path) {
		//$('#hideEmergencyForm1').serialize());
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#hideEmergencyForm1').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"

		}).then(function(data) {
			showProfile();
			myprofile.getProfileData(("../patient/personalinfo"));

		});
	}
	return {
		getProfileData : getProfileData,
		displayProfileData : displayProfileData,
		sendPersonalProfileData : sendPersonalProfileData,
		sendAddressProfileData : sendAddressProfileData,
		sendContactProfileData : sendContactProfileData,
		sendEmergencyProfileData : sendEmergencyProfileData

	};
}());
