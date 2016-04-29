/**
 * 
 */
var messages = (function() {
    var messagesData;
    var displayMessages = function(data) {
        console.log(data);
        console.log("populate the dom using jquery and mustache js");
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
messages.getMessages("http://jsonplaceholder.typicode.com/posts?userId=1");