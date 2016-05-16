/**
 * 
 */
var contactusTemp = (function() {

	var sendInquiry = function(path) {
		alert("here");
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#contactus').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"
		}).then(function(data) {
			if (data.SUCCESS) {
				$("#success").css("display", "visible");
			}
			if (data.ERROR) {
				$("#error").css("display", "visible");
			}
		});
	};
	return {
		sendInquiry : sendInquiry
	};
}());
