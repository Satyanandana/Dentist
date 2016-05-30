/**
 * 
 */

function showProfile() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#Settings").hide();
	$("#MyProfile").show();
	$("#showContactForm").show();
	$("#showPersonalForm").show();
	$("#showEmergencyForm").show();

	$("#showAddressForm").show();
	$("#hideContactForm").hide();
	$("#hidePersonalForm").hide();
	$("#hideEmergencyForm").hide();

	$("#hideAddressForm").hide();
	myprofile.getProfileData(("../patient/personalinfo"));

}

function showMessages() {
	$("#Messages").show();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#MyProfile").hide();
	$("#Settings").hide();

	sentmessages.getMessages("../patient/sentmessages");
	receivedmessages.getMessages("../patient/receivedmessages");
}
function showAppointments() {

	$("#Messages").hide();
	$("#Appointments").show();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#MyProfile").hide();
	$("#Settings").hide();
	confirmedappointments.getAppointments("../patient/appointments");

	requestedappointments.getAppointments("../patient/appointmentrequests");

}
function showTreatments() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").show();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#MyProfile").hide();

	$("#Settings").hide();

	treatments.getTreatments("../treatments/status");
	$("#teethTreatment").hide();

}
function showInsurance() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").show();
	$("#Payment").hide();
	$("#MyProfile").hide();
	$("#Settings").hide();
	insurances.getInsurances("../patient/insurances");

}
function showContactForm() {

	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#Settings").hide();
	$("#MyProfile").show();
	$("#hideContactForm").show();
	$("#showContactForm").hide("slow");
	$("#showPersonalForm").show();
	$("#hidePersonalForm").hide();
	$("#showEmergencyForm").show();
	$("#hideEmergencyForm").hide();

	$("#showAddressForm").show();
	$("#hideAddressForm").hide();

}
function showPersonalForm() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#Settings").hide();
	$("#MyProfile").show();
	$("#hideContactForm").hide();
	$("#showContactForm").show();
	$("#showPersonalForm").hide("slow");
	$("#hidePersonalForm").show();
	$("#showEmergencyForm").show();
	$("#hideEmergencyForm").hide();

	$("#showAddressForm").show();
	$("#hideAddressForm").hide();
}

function showEmergencyForm(event) {

	event.stopImmediatePropagation();
	event.preventDefault();
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Settings").hide();
	$("#Payment").hide();
	$("#MyProfile").show();
	$("#hideContactForm").hide();
	$("#showContactForm").show();
	$("#showEmergencyForm").hide("slow");
	$("#hideEmergencyForm").show();
	$("#showPersonalForm").show();
	$("#hidePersonalForm").hide();

	$("#showAddressForm").show();
	$("#hideAddressForm").hide();
}

function showAddressForm() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#Settings").hide();
	$("#MyProfile").show();
	$("#hideContactForm").hide();
	$("#showContactForm").show();
	$("#showEmergencyForm").show();
	$("#hideEmergencyForm").hide();
	$("#showPersonalForm").show();
	$("#hidePersonalForm").hide();

	$("#showAddressForm").hide("slow");
	$("#hideAddressForm").show();
}
function showPayment() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").show();
	$("#Settings").hide();
	$("#MyProfile").hide();

	senddocuments.getsenddocuments("../patient/sentdocuments");
	receiveddocuments.getreceiveddocuments("../patient/receiveddocuments");
}

function showSettings() {
	$("#Messages").hide();
	$("#Appointments").hide();
	$("#Treatments").hide();
	$("#Insurance").hide();
	$("#Payment").hide();
	$("#MyProfile").hide();
	$("#Settings").show();

}

function showMyTeethDetails(id) {

	teethTreatments.getTreatmentsForTeeth("../teeth/" + id);

}

function statusChanged() {

	if ($('#selectStatus').val() == "Completed") {
		$('#statusModal').modal('hide');
		$('#appendStatusCompleted').modal('show');
	} else {
		//"send post request ..");
	}
}

function addNewTreatmentOnTeeth(teethID) {

	$('#teethID').attr("value", teethID);
	$('#treatmentModal').modal('show');

}
function editInsurance(insuranceID,groupdID,groupName,insuranceProviderID, insuranceProviderName,
		subscriberID, subscriberFullName, patientID, dateOfBirth, status) {

	$('#updateInsurance #insuranceID').attr("value", insuranceID);
	
	$('#updateInsurance #insuranceProviderID').val(insuranceProviderID);
	$('#updateInsurance #insuranceGroupID').val(groupdID);
	$('#updateInsurance #insuranceGroupName').val(groupName);
	$('#updateInsurance #insuranceProviderName').val(insuranceProviderName);
	$('#updateInsurance #subscriberID').val(subscriberID);
	$('#updateInsurance #subscriberFullName').val(subscriberFullName);
	$('#updateInsurance #dob').val(dateOfBirth);
	$('#updateInsurance #status').val(status);
	$('#updateInsuranceModal').modal('show');
}
