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
        <h3>All Received Messages</h3>
        
         
         
      </div>
      </center>
    </div>
  
    
    <table id="receivedMessageDetails" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th>Patient ID</th>
                <th>Message ID</th>
                <th>Received Time</th>
                <th>Message</th>
                
                
                
               
            </tr>
        </thead>
        
        
        <tfoot>
            <tr>
                <th>Patient ID</th>
                <th>Message ID</th>
                <th>Received Time</th>
                <th>Message</th>
               
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
	$('#receivedMessageDetails').DataTable( {
	    ajax: '../sentmessages/allmessagestodoc',
	    columns: [
	              {data : 'senderID',"render": function ( data, type, full, meta ) {
	    	  	      return '<a href="view/'+data+'" target="_blank"> '+data+' </a>';
	    		    }},
	        
	        { data: 'messageID' },
	        { data: 'sentTime' },
	        {data : 'msg',"render": function ( data, type, full, meta ) {
	        	
  	  	      return '<a href="#" onclick="showMessageModal('+"'"+data+"'"+')"> view </a>';
  		    }}
	        
	       
	       
	    ]
	
	   
	  
	} );
	
	
} );
</script>
<script type="text/javascript">
function showMessageModal(msg)
{
	
	$('#messageData').html(msg);
	
	 $('#receivedmessageModal').modal('show');
}
</script>


	<div class="modal fade" id="receivedmessageModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Message</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						
<p id="messageData"></p>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

</body>
</html>