/**
 * 
 */
var sentmessages = (function() {
    var messagesData;
    var displayMessages = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	 $('#sentmessages1').empty();
    	 $('#sentmessages1').append("<center><p style='border-style: ridge ;'>Your sent messages folder is empty , please send new message by clicking COMPOSE . </p></center>");
    	}
        else
        	{
        
        $('#sentmessages1').empty();
  	  $.each(data, function(key,val) {
          
  		  var template = $('#sentMessageTemp').html();
  		
  		  val.firstMessage=val.msg.substring(0,20);
  		
  		  val.secondMessage=val.msg.substring(20);
  		
  		    var html = Mustache.to_html(template, val);
  		  
  		    $('#sentmessages1').append(html);
  		  
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
    
    var sendMessage = function(path) {
    	 //"here");
       $.ajax({
            url: path,
            method: 'POST',
            data: $('#sendMessage1').serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        }).then(function(data) {
        	$('#messageModal').modal('toggle');
        	sentmessages.getMessages("../patient/sentmessages");
        	
             
        });
    }
    return {
        getMessages: getMessages,
        displayMessages: displayMessages,
        sendMessage: sendMessage
        
    };
}());
