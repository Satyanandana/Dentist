<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div id="root">
<c:url value="/" var="root" />
</div>
<head>

 <link rel="stylesheet" media="screen" href= " <c:url value='/resources/css/business-frontpage.css'/>" >


</head>
<body>
  <p id="action" style="visibility: hidden;">${action}</p>

<!-- Page Content -->
<!-- Page Content -->
<%@include file="dynamicheader.jsp" %>
<div class="container">

 <div class="row">
 <div class="col-sm-2">
 </div>
 <div class="col-sm-8">

 
 <div id="login" style="display: none;">
 <h2><span class="glyphicon glyphicon-user"></span>Login</h2>
 
  <div class="form-group">
  <c:url value="/login/process" var="loginUrl" />
  <form method="POST" action="${loginUrl}" >
			    <label for="email">Username</label>
			    <input type="text" class="form-control" id="email" name="email"  placeholder="Enter username" value="" />
			 
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="password" name="password"  placeholder="Password"/>
			  </div>
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  <button type="submit" class="btn btn-primary">Login </button><a   class="forgotlink"><font color="red"> <u>Forgot Password ?</u></font></a><br/>
			  <br/>
</form>
			  
			   <a  class="signuplink" >Don't Have An Account ? <u>Create One</u></a>
               
 </div>
 
  </div>
 <div id="signup" style="display: none;">
 
 
 
 <p>${errorEmail}</p>
		<p>${errorPassword}</p>
		<p>${errorFirstName}</p>
		<p>${errorPhoneNumber}</p>
		
   <h2><span class="glyphicon glyphicon-log-in"></span>  Register</h2>
   <c:url value="/signup/process" var="signupUrl" />
			<form:form method="POST" action="${signupUrl}"  id="signupForm" modelAttribute="patient" >
			<div class="col-sm-4">
			  <div class="form-group">
			    <label for="first-name">First Name</label>
			    
			    
			    <form:input type="text" class="form-control" path="firstName" id="firstName" />
			  </div>
			  </div>
			  <div class="col-sm-4">
			  <div class="form-group">
			    <label for="last-name">Middle Name</label>
			    
			    
			    <form:input type="text" class="form-control"  path="middleName" id="middleName" />
			  </div>
			  </div>
			  <div class="col-sm-4">
			  <div class="form-group">
			    <label for="last-name">Last Name</label>
			    
			    <form:input type="text" class="form-control"  path="lastName" id="lastName" />
			  </div>
			  </div>
			  <div class="col-sm-12">
			  <div class="form-group">
			    <label for="email">Email</label>
			    
			    <form:input type="text" class="form-control"  path="userAuth.userEmail" id="userEmail" />
			  </div>
			  </div>
			  <div class="col-sm-6">
			  <div class="form-group">
			    <label for="password">Password</label>
			    
			    
			    <form:input type="password" class="form-control"  path="userAuth.userPwd" id="userpassword" />
			  </div>
			  </div>
			  <div class="col-sm-6">
			  <div class="form-group">
			    <label for="password">Re-enter Password</label>
			    <input type="password" class="form-control" name="repassword">
			  </div>
			  </div>
			  <div class="col-sm-6">
			  <div class="form-group">
			    <label for="major">Phone Number</label>
			    
			    <form:input type="text" class="form-control"  placeholder="xxx-xxx-xxxx" path="phoneNumber" id="phoneNumber" />
			  </div>
			  </div>
			  <div class="col-sm-6">
			  
			    <div class="form-group">
      <label for="dob12">Date Of Birth</label>
      
        <input type="text" class="form-control" id="dob" placeholder="mm/dd/yyyy" pattern="\d{1,2}/\d{1,2}/\d{4}" name="dob">
        
     
    </div>
			  </div>
			   <div class="col-sm-12">
			    <div class="checkbox">
          <label>
            <input type="checkbox" checked="checked"> I Agree 
          </label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  <button type="submit" class="btn btn-primary">Register</button>
			 
			  </div>
			  
			</form:form>
 <a class="loginlink">Login</a>
 <a class="forgotlink">Forgot</a>
 </div>
 
 <div id="forgot" style="display: none;">
  <h2><i class="fa fa-key"></i> Forgot Password</h2>
  <c:url value="/login/forgotpassword" var="forgotPasswordUrl" />
			<form action="${forgotPasswordUrl}" method="post">
			  <div class="form-group">
			    <label for="first-name">Enter Your Email</label>
			     <input type="text" class="form-control" name="email"/>
			  </div>
			  <button type="submit" class="btn btn-primary">Send Me An Email </button>
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  </form>
			  
			  <a class="loginlink">Login</a>
 <a class="signuplink">SignUp</a>
 </div>
 
 
 </div>
 
 </div>
 
 
 
</div>

<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-datepicker.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
    <script type="text/javascript">
   
	   function login()
	    {
	   	 
	        $("#signup").hide();
	        $("#forgot").hide();
	        $("#login").show();
	        
	    }
	    function signup()
	    {
	    	
	   	 $("#login").hide();
	        
	        $("#forgot").hide();
	        $("#signup").show();
	        
	    }
	    function forgot()
	    {
	   	 $("#login").hide();
	        $("#signup").hide();
	        $("#forgot").show();
	        
	    }
  
   $(document).ready(function(){
	 
	    	 
	  $(".signuplink").on('click',signup);
	  $(".loginlink").on('click',login);
	  $(".forgotlink").on('click',forgot);
	  var action = $("#action");
	  
	  
	  if( action.html()==="" || action.html()=== "login"){
		
		  login();
	  }else if(action.html()==="signup"){
		
		  signup();
	  }else if(action.html()==="forgot"){
		
		  forgot();
	  }
		 
		   
     
     
       
   });
    
    
    
    
    </script>



</body>
</html>