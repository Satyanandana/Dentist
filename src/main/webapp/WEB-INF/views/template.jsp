<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Dr Kang's Dental</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/font/flaticonteeth12.css'/>">

<script type="text/template" id="sentMessageTemp">

<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
<div class="row">
<div class="col-xs-5">
<i class="fa fa-clock-o" aria-hidden="true"></i> Sent Time :  {{sentTime}} 
</div>
<div class="col-xs-5">
 <i class="fa fa-envelope" aria-hidden="true"></i>  {{firstMessage}} 
</div>
<div class="col-xs-2">
<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#panel2{{messageID}}" style="float:right;">View</a></p>
</div>
               </div> 
			</h4>
		</div>
		<div id="panel2{{messageID}}" class="panel-collapse collapse">
			<div class="panel-body">
  {{firstMessage}}{{secondMessage}}
               
			</div>
		</div>
    </div>
    </div> 


  


</script>

<script type="text/template" id="receivedMessageTemp">

<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
<div class="row">
<div class="col-xs-5">
<i class="fa fa-clock-o" aria-hidden="true"></i> Sent Time :  {{receivedTime}} 
</div>
<div class="col-xs-5">
 <i class="fa fa-envelope" aria-hidden="true"></i>  {{firstMessage}} 
</div>
<div class="col-xs-2">
<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#panel1{{messageID}}" style="float:right;">View</a></p>
</div>
               </div> 
			</h4>
		</div>
		<div id="panel1{{messageID}}" class="panel-collapse collapse">
			<div class="panel-body">
  {{firstMessage}}{{secondMessage}}
               
			</div>
		</div>
    </div>
    </div>

</script>

<script type="text/template" id="insurancesTemp">
<div class="col-sm-6">
<ul class="list-group">
    <li class="list-group-item">
     
   
    <p class="list-group-item-text">
   
    
    
    

<div class="form-group">
  <label class="control-label" for="disabledInput">Insurance Provider Name</label>&nbsp;&nbsp;{{insuranceProviderName}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Subscriber ID</label>&nbsp;&nbsp;{{subscriberID}}
  
</div>

<div class="form-group">
  <label class="control-label" for="disabledInput">Insurance Group ID </label>&nbsp;&nbsp;{{insuranceGroupID}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Insurance Group Name </label>&nbsp;&nbsp;{{insuranceGroupName}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Subscriber Full Name</label>&nbsp;&nbsp;{{subscriberFullName}}
  
</div>

<div class="form-group">
  <label class="control-label" for="disabledInput">Date Of Birth</label>&nbsp;&nbsp;{{dateOfBirth}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Status</label>&nbsp;&nbsp;{{status}}
  

</div>

 
        <button type="submit" class="btn btn-primary btn-sm" onclick="editInsurance({{insuranceID}},'{{insuranceGroupID}}','{{insuranceGroupName}}','{{insuranceProviderID}}','{{insuranceProviderName}}','{{subscriberID}}','{{subscriberFullName}}',{{patientID}},'{{dateOfBirth}}','{{status}}')">Edit</button>
     
     
    </li> 
  </ul>
</div>

</script>
<script type="text/template" id="appointmentsTemp">

    <tr>
      <td>{{counter}}</td>
      <td>{{appointmentStartTime}}</td>
      <td>{{note}}</td>
      <td>{{expectedAmount}} </td>
      <td>{{amountPaid}}</td>
      <td>



    {{status}}



  
  
         </td>
<td>
  <button  class="btn btn-primary btn-xs" id="appointmentsStatusChange" onclick="postAppointmentStatus({{appointmentID}},{{patientID}},this.value,'{{note}}')"  style="visibility:{{hidden}};">Change Status</button>
  
</td>
  
</script>

<script type="text/template" id="requestedAppointmentsTemp">

 

    <tr >
      <td>{{counter}}</td>
      <td>{{appointmentStartTime}}</td>
      <td>{{note}}</td>
      <td>{{status}}</td>
     
      
<td>
  <button  class="btn btn-primary btn-xs" id="appointmentsRequesedStatusChange" onclick="postRequestedAppointmentStatus({{appointmentRequestID}},{{patientID}},this.value)"  style="visibility:{{hidden}};">Change Status</button>
  
</td>
     
    </tr>


   
</script>

<script type="text/template" id="mydocumentsTemp">

 

    <tr >
      <td>{{counter}}</td>
      <td>{{fileName}}</td>
      <td>{{sentTime}}</td>

      <td><a href="../doc/download/sent/{{docID}}" target="_blank">View File</a></td>
     

     
    </tr>


   
</script>

<script type="text/template" id="receiveddocumentsTemp">

 

    <tr >
      <td>{{counter}}</td>
      <td>{{fileName}}</td>
      <td>{{receivedTime}}</td>

      <td><a href="../doc/download/received/{{docID}}" target="_blank">View File</a></td>
     

     
    </tr>


   
</script>
<script type="text/template" id="requestedTreatmentsTemp">

     
<td align="center">
<p style="margin-left: 22px;">{{counter}}</p> 
<i class="flaticon-icon-{{patientTeethStatus}}"  style="color:{{treatmentStatus}};margin-left: 20px;" id="" onclick="showMyTeethDetails({{counter}})" data-toggle="tooltip" title="{{patientTeethStatus}}" ></i><span class="tab"></span>  
</td>
   
     
</script>

<script type="text/template" id="requestedTreatmentsTemp12">

  
<td align="center">

<i class="flaticon-icon-{{patientTeethStatus}}" style="color:{{treatmentStatus}};margin-left: 20px;" id="" onclick="showMyTeethDetails({{counter}})" data-toggle="tooltip" title="{{patientTeethStatus}}" ></i><span class="tab"></span>  
<p style="margin-left: 22px;">{{counter}}</p> 
</td>
 
     
</script>

<script type="text/template" id="teethDetailsTemp">
<div class="row">
<div class="col-xs-6">
 <h2 style="float: left;"> <i class="flaticon-icon-91156"></i>Teeth Details Of Teeth:{{teeth.teethID}}</h2>
</div>
<div class="col-xs-6">
<h2> <a href="#" style="float:right;" class="btn btn-success btn-xs" onclick="addNewTreatmentOnTeeth({{teeth.teethID}})">Add New Treatment <i class="fa fa-plus-circle" ></i></a> </h2>
</div>
</div>
 
  
  <table class="table" align="center">
  <thead>
    <tr>
      
      <th>Teeth Name</th>
      <th>Description</th>
      <th>Total Amount Spent</th>
      <th>Status</th>
       <th></th>
     
     
    </tr>
  </thead>
  
  <tbody >
    <tr>
    

      
      <td>{{teeth.teethName}}</td>
      <td>{{teeth.description}}</td>
<td>{{total}}</td>
<td>{{status}}</td>
<td></td>
<td><button  class="btn btn-primary btn-xs" id="" onclick="changeTeethStatus({{teeth.teethID}})" style="float:right;" >Change Status</button></p></td>
	  

    </tr>
   </tbody>
  </table>

</script>
<script type="text/template" id="teethDetailsTreatmentTemp">

<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
<div class="row">
<div class="col-xs-5">
Created On : {{treatmentInsertedTime}}
</div>
<div class="col-xs-5">
Status : {{status}}
</div>
<div class="col-xs-2">
 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#panel{{treatmentID}}" style="float:right;">View</a>
</div>

</div>
        
                
			</h4>
		</div>
		<div id="panel{{treatmentID}}" class="panel-collapse collapse">
			<div class="panel-body">
<div class="row" style="margin-bottom:20px;">
<div class="col-xs-5">
Treatment Expected : {{treatmentExpectedTime}} 
</div>
<div class="col-xs-5">
Treatment Done : {{treatmentDoneTime}} 
</div>
</div>

<div class="row" style="margin-bottom:20px;">
<div class="col-xs-5">
Amount Expected : {{amountExpected}}  
</div>
<div class="col-xs-5">
Amount Paid : {{amountPaid}} 
</div>
</div>

<p>NOTE :</p><p> {{note}}</p>
<p>    <button  class="btn btn-primary btn-xs" id="" onclick="changestatusOfTeethTreatment({{teethID}},{{treatmentID}})" style="float:right;visibility:{{hidden}}" >Change Status</button></p> 
			</div>
		</div>
    </div>
    </div> 

</script>

<script type="text/template" id="newTreatmentTempForStatus">
<div class="form-group">
  <label class="control-label" for="disabledInput">Treatment Done Date</label>
  <input class="form-control" id="treatmentDoneTime" name="treatmentDoneTime" type="text" placeholder="mm/dd/yyyy" >
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Amount Paid</label>
  <input class="form-control" id="amountPaid" name="amountPaid" type="text" placeholder="00" >
</div> 
</script>
<script type="text/template" id="adminAllPatientsTemp">
 
            <tr>
                <th>{{userID}}</th>
                <th>{{firstName}}</th>
                <th>{{middleName}}</th>
                <th>{{lastName}}</th>
                <th>{{dateOfBirth}}</th>
                <th>{{phoneNumber}}</th>
                <th>{{email}}</th>
                <th>{{homeAddress.address1}} {{homeAddress.address2}} {{homeAddress.city}} {{homeAddress.state}} {{homeAddress.zipcode}}</th>
            </tr>
        
</script>
</head>

</html>