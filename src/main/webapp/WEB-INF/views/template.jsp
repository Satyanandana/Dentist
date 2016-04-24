<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<c:url value='/resources/css/font/flaticonteeth12.css'/>">

<script type="text/template" id="sentMessageTemp">



<li class="list-group-item"><p class="list-group-item-text">{{firstMessage}}<a class="read-more-show hide" href="#">Read More</a> <span class="read-more-content">{{secondMessage}} <a class="read-more-hide hide" href="#">Read Less</a></span></p><p><small style="float: right;">{{sentTime}}</small></p></li>
<script type="text/javascript" src="<c:url value='/resources/js/readmore.js'/>" />  


</script>

<script type="text/template" id="receivedMessageTemp">

  <li class="list-group-item"><p class="list-group-item-text">{{firstMessage}}<a class="read-more-show hide" href="#">Read More</a> <span class="read-more-content">{{secondMessage}} <a class="read-more-hide hide" href="#">Read Less</a></span></p><p><small style="float: right;">{{receivedTime}}</small></p></li>
  <script type="text/javascript" src="<c:url value='/resources/js/readmore.js'/>" />


</script>

<script type="text/template" id="insurancesTemp">
<div class="col-sm-6">
<ul class="list-group">
    <li class="list-group-item">
     
   
    <p class="list-group-item-text">
   
    
    
    
<div class="form-group">
  <label class="control-label" for="disabledInput">Insurance Provider ID </label>&nbsp;&nbsp;{{insuranceProviderID}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Insurance Provider Name</label>&nbsp;&nbsp;{{insuranceProviderName}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Subscriber ID</label>&nbsp;&nbsp;{{subscriberID}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Subscriber Name</label>&nbsp;&nbsp;{{subscriberFullName}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Patient Name</label>&nbsp;&nbsp;{{patientID}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Date Of Birth</label>&nbsp;&nbsp;{{dateOfBirth}}
  
</div>
<div class="form-group">
  <label class="control-label" for="disabledInput">Status</label>&nbsp;&nbsp;{{status}}
  

</div>

 
        <button type="submit" class="btn btn-primary btn-sm" onclick="editInsurance({{insuranceID}})">Edit</button>
        <button type="reset" class="btn btn-default btn-sm">Delete</button>
     
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
  <button  class="btn btn-primary btn-xs" id="appointmentsStatusChange" onclick="postAppointmentStatus({{appointmentID}},{{patientID}},this.value)"  style="visibility:{{hidden}};">Change Status</button>
  
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

      <td>{{teeth.teethID}}</td>
      <td>{{teeth.teethName}}</td>
      <td>{{teeth.description}}</td>
<td>{{total}}</td>

<td><a href="#" class="btn btn-success btn-xs" onclick="addNewTreatmentOnTeeth({{teeth.teethID}})">Add New Treatment <i class="fa fa-plus-circle" ></i></a></td>
	  


</script>
<script type="text/template" id="teethDetailsTreatmentTemp">

 

<tr>

<td>
{{treatmentInsertedTime}}
</td>
<td>
{{treatmentDoneTime}}
</td>
<td>
{{treatmentExpectedTime}}
</td>
<td>
{{amountPaid}}
</td>
<td>
{{amountExpected}}
</td>
<td>
{{status}}
<p><button  class="btn btn-primary btn-xs" id="treatmentsStatusChange" onclick="changestatusOfTeethTreatment({{teethID}},{{treatmentID}})"  style="visibility:{{hidden}};">Change Status</button></p>
</td>

  
  

</tr>
<tr>
<td colspan="6">
NOTE : {{note}}
</td>
</tr>
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
</head>

</html>