/**
 * 
 */
function postAppointmentStatus(appointmentID, patientId, value) {

	$('#appointmentID').attr("value", appointmentID);
	$('#patientID').html(patientId);
	$('#confirmededAppointmentStatusModal').modal('show');

}

function postRequestedAppointmentStatus(appointmentRequestID, patientId, note,
		value) {

	$('#appointmentRequestID').attr("value", appointmentRequestID);

	$('#noteTemp').attr("value", note);

	$('#patientID').html(patientId);
	$('#requestedAppointmentStatusModal').modal('show');
}

function changestatusOfTeethTreatment(teethID, treatmentId) {

	$('#treatmentUpdateTeethID').attr("value", teethID);
	$('#treatmentID').attr("value", treatmentId);
	$('#teethTreatmentsChangeStatusModal').modal('show');

}

function changeTeethStatus(teethID) {

	$('#teethStatusTeethID').attr("value", teethID);

	$('#changeTeethStatusModal').modal('show');

}

function newTreatmentStatus() {

	var status = $('#newTreatementStatusChange').val();
	if (status == 'COMPLETED') {

		var template = $('#newTreatmentTempForStatus').html();

		$('#addNewTreatmentDiv').append(template);

	} else {
		$('#addNewTreatmentDiv').empty();

	}
}

function requestedAppointmentStatus(note) {

	var status = $('#requestedAppointmentStatusValue').val();

	if (status == 'ACCEPT') {

		var template = $('#requestedAppointmentStatusTemp').html();

		$('#appendAcceptedStatusFields').append(template);
		$('#updateRequestedAppointment textarea').val(note);

	} else {
		$('#appendAcceptedStatusFields').empty();

	}
}