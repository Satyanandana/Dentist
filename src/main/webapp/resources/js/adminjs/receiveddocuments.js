/**
 * receiveddocuments  receiveddocumentsTemp
 */
var receiveddocuments = (function() {
    var documentData;
    var displayreceiveddocuments = function(data) {
        console.log(data);
        if(jQuery.isEmptyObject(data))
    	{
        	 $('#receiveddocuments').empty();
    	 $('#receiveddocuments').append("<tr><td colspan='6'><center><p style='border-style: ridge ;'>You are not having senddocuments at this time , please attach new document by clicking Add New . </p></center></td></tr>");
    	}
        else
        	{
        	 
        $('#receiveddocuments').empty();
        var counter=1;
  	  $.each(data, function(key,val) {
          
  		  var template = $('#receiveddocumentsTemp').html();
  		
  		    val.counter=counter++;
  		
  		    var html = Mustache.to_html(template, val);
  		  
  		    $('#receiveddocuments').append(html);
  		  
  	});
    }
        
    }
    var getreceiveddocuments = function(path) {
        $.ajax({
            url: path,
            method: 'GET'
        }).then(function(data) {
            documentData = data;
            displayreceiveddocuments(documentData);
        });
    }
    
    var postdocuments = function(path) {
    	 var oMyForm = new FormData();
    
    	  oMyForm.append("name",name);
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
        	receiveddocuments.getreceiveddocuments("../patient/receiveddocuments");
        	
             
        });
    }
    return {
    	getreceiveddocuments: getreceiveddocuments,
        displayreceiveddocuments: displayreceiveddocuments,
        postdocuments: postdocuments
        
    };
}());
