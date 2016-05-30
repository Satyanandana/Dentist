/**
 * 
 */
var contactusTemp = (function() {

	var sendInquiry = function(path) {
		//"here");
		$("#contactus #successContactDiv").hide();
		$("#contactus #error").hide();
		$.ajax({
			url : path,
			method : 'POST',
			data : $('#contactus').serialize(),
			contentType : "application/x-www-form-urlencoded; charset=UTF-8"
		}).then(function(data) {
			
			if (data.Success) {
				$("#contactus #successContactDiv").show();
			}
			if (data.Error) {
				$("#contactus #error").show();
			}
		});
	};
	return {
		sendInquiry : sendInquiry
	};
}());
