<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<c:url value='https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css'/>" media="screen">
	

<title>Dr Kang's Dental</title>
</head>
<body>
<%@include file="template.jsp"%>
	<%@include file="dynamicheader.jsp"%>
<div class="container-fluid">
		<br />
		<h1>Admin Panel</h1>
		<br />



<div class="row">
<div class="col-xs-12">

 <div class="thumbnail">
    <center>
      <div class="caption">
        <h3>All Received Documents</h3>
        
         
         
      </div>
      </center>
    </div>
  
    
    <table id="receivedDocumentDetails" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
            
    			<th>Patient ID</th>
            	<th>Document ID</th>
                <th>File Name</th>
                <th>Received Time</th>
                <th>download</th>
                
                
                
               
            </tr>
        </thead>
        
        
        <tfoot>
            <tr>
            	<th>Patient ID</th>
            	<th>Document ID</th>
                <th>File Name</th>
                <th>Received Time</th>
                <th>download</th>
            
               
            </tr>
        </tfoot>
    </table>
    
</div>
</div>

</div>

<%@include file="footer.jsp"%>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/mustache.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/admin.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js'/>"></script> --%>






<script type="text/javascript">


$(document).ready(function() {
	$('#receivedDocumentDetails').DataTable( {
	    ajax: '../doc/allreceiveddocuments',
	    columns: [


	              {data : 'senderID',"render": function ( data, type, full, meta ) {
	    	  	      return '<a href="view/'+data+'" target="_blank"> '+data+' </a>';
	    		    }},
	        
	        { data: 'docID' },
	        { data: 'fileName' },
	        { data: 'sentTime' },
	        {data : 'docID',"render": function ( data, type, full, meta ) {
	        	
  	  	      return '<a target="_blank" href="../doc/download/received/'+full.docID+'/'+full.senderID+'"'+ '> Download </a>';
  		    }}
	        
	       
	       
	    ]
	
	   
	  
	} );
	
	
} );
</script>

</body>
</html>