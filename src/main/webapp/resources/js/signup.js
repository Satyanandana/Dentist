/**
 * 
 */


$(document).ready(function(){
	
	
	
	var loginForm = $("#signupForm");
	loginForm.submit(function(event){
		event.preventDefault();
		
		console.log("I am in ajax call");
		$.ajax({
			url : "\process",
			type : 'Post',
			datatype : 'json',
			data : loginForm.serialize(),
			success : function(data) {
				      console.error(data);
							},
			error : function() {
				alert("ajax request failed");
			}
			
		});
		
	});
	
});