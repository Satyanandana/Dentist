/**
 * 
 */
var settings = (function() {

	var postNewPassword = function(path) {
		// $('#changePasswordAdmin').serialize());
		$('#changePasswordModal #errorMessage').css("display", "none");
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#changePasswordAdmin').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"
		}).then(function(data) {
			if (data.Success) {

				$('#changePasswordModal').modal('toggle');

			}
			if (data.error) {
				/* alert(data.error); */
				/*
				 * $('#changePasswordModal errorMessage').html(data.error); //
				 * $('#successDiv p').html(data.noaccount);
				 */
				$('#changePasswordModal #errorMessage').html(data.error);
				$('#changePasswordModal #errorMessage').show();

			}

		});
	}
	return {
		postNewPassword : postNewPassword

	};
}());
