/**
 * 
 */
var receivedmessages = (function() {
    var messagesData;
    var displayMessages = function(data) {
    	
        console.log(data);
      
        if(jQuery.isEmptyObject(data))
        	{
        	$('#receivedmessages').empty();
        	 $('#receivedmessages').append("<center><p style='border-style: ridge ;'>Your Inbox is not having any messages . </p></center>");
        	}
        else
        	{
        $('#receivedmessages').empty();
  	  $.each(data, function(key,val) {
         
  		  var template = $('#receivedMessageTemp').html();
  		  val.firstMessage=val.msg.substring(0,200);
  		  val.secondMessage=val.msg.substring(201);
  		    var html = Mustache.to_html(template, val);
  		    $('#receivedmessages').append(html);
  	});
        	}
        
    }
    var getMessages = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            messagesData = data;
            displayMessages(messagesData);
        });
    }
    return {
        getMessages: getMessages,
        displayMessages: displayMessages
    };
}());
