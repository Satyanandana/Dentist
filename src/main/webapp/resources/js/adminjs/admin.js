/**
 * 
 */
var admin = (function() {
	var adminPatientsData;
	var displayPatients = function(data) {
		console.log(data);

		$('#appendPatients').empty();
		$.each(data, function(key, val) {

			var template = $('#adminAllPatientsTemp').html();

			var html = Mustache.to_html(template, val);

			$('#appendPatients').append(html);

		});

	}
	var getPatients = function(path) {
		$.ajax({
			url : path,
			method : 'GET'
		}).then(function(data) {
			adminPatientsData = data;
			displayPatients(adminPatientsData);
		});
	}

	return {
		getPatients : getPatients,
		displayPatients : displayPatients,

	};
}());
