/**
 * 
 */
var senddocuments = (function() {
    var documentData;
    var displaysenddocuments = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	 $('#mydocuments').empty();
    	 $('#mydocuments').append("<center><p style='border-style: ridge ;'>You are not having senddocuments at this time , please attach new document by clicking Add New . </p></center>");
    	}
        else
        	{
        	 
        $('#mydocuments').empty();
        var counter=1;
  	  $.each(data, function(key,val) {
          
  		  var template = $('#mydocumentsTemp').html();
  		
  		    val.counter=counter++;
  		
  		    var html = Mustache.to_html(template, val);
  		  
  		    $('#mydocuments').append(html);
  		  
  	});
    }
        
    }
    var getsenddocuments = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            documentData = data;
            displaysenddocuments(documentData);
        });
    }
    
    var postdocuments = function(path) {
    	 var oMyForm = new FormData();
          //$("#uploadFileName").val());
    	  oMyForm.append("name",$("#uploadFileName").val());
    	  oMyForm.append("file", file.files[0]);
    	 
    	  
    	 //$('#attachDocumentForm').serialize());
       $.ajax({
            url: path,
            method: 'POST',
            data: oMyForm,
            dataType: 'text',
            processData: false,
            contentType: false
        }).then(function(data) {
        	$('#documentModal').modal('toggle');
        	senddocuments.getsenddocuments("../patient/sentdocuments");
        	
             
        });
    }
    return {
        getsenddocuments: getsenddocuments,
        displaysenddocuments: displaysenddocuments,
        postdocuments: postdocuments
        
    };
}());
