   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Dr. Kang's Clinic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
 <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" media="screen">
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>" media="screen">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300italic' rel='stylesheet' type='text/css'>
    

    <link rel="stylesheet" href="<c:url value='/resources/css/custom.min.css'/>">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script type="text/javascript" src="../bower_components/html5shiv/dist/html5shiv.js"></script>
      <script type="text/javascript" src="../bower_components/respond/dest/respond.min.js"></script>
    <![endif]-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/custom.js'/>"></script>






  </head>
  <body>
  
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  
    	
    
      <div class="container-fluid">
      <div class="navbar navbar-default navbar-fixed-top">
      <img src="<c:url value='/resources/img/logo_1.gif'/>" height="70" width="180" style="float: left;margin-right: 20px;margin-left: 5px;" ></img>
        <div class="navbar-header">
          <a href="<c:url value='/home'/>" class="navbar-brand" data-toggle="tooltip" title="Go Back To Homepage"> Dr Kang's Dental Clinic</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="aboutus" >About Us <span class="caret"></span></a>
              <ul class="dropdown-menu" aria-labelledby="aboutus">
               <li><a href="<c:url value='/aboutme'/>" data-toggle="tooltip" title="know about me">About Me</a></li>
                <li class="divider"></li>
                <li><a href="<c:url value='/services'/>" data-toggle="tooltip" title="Our Services">Our Services</a></li>
                <li class="divider"></li>

                
                
                <li><a href="<c:url value='/gallery'/>" data-toggle="tooltip" title="View our gallery">Our Gallery</a></li>
                <li class="divider"></li>


                   <li><a data-toggle="modal" data-target="#officeVideoModal">Getting To The Office</a></li>





            </ul>
            </li>
            <li>
              <a href="<c:url value='/profile/scheduleappointment'/>" data-toggle="tooltip" title="View Appointments">Schedule Appointment</a>
            </li>
            <li><a href="<c:url value='/askme'/>" data-toggle="tooltip" title="Frequently asked questions">FAQ's</a></li>
            <li>
            <a href="<c:url value='/contactus'/>" data-toggle="tooltip" title="Contact us/Help">Contact Us</a>
            </li>
           </ul>

           <!-- dynamic profile menu  -->
      <ul class="nav navbar-nav navbar-right" style="margin-right: 5px;">


 <c:choose>
    <c:when test="${user == null}">
       <li><a href="<c:url value='/login/form'/>" id="signin" ><i class="glyphicon glyphicon-log-in" style="font-size: 13px !important; display: inline !important;"></i>  Sign-In/Sign-Up</a></li>
    </c:when>    
    <c:otherwise>
       <li class="dropdown dropdown-user" id="userprofile">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <span class="username username-hide-on-mobile">
                  <i class="glyphicon glyphicon-user" style="font-size: 13px !important; display: inline !important;"></i>  ${name}
                    </span>
                    <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-default">
    <c:choose>
        <c:when test="${role == 'ROLE_USER'}">
        
                      <li>
                            <a href="<c:url  value="/profile/view?action=profile" />">
                           <i class="fa fa-user"></i>&nbsp;&nbsp;My Profile </a>
                        </li>
                       <li>
                            <a href="<c:url  value="/profile/view?action=messages" />">
                           <i class="fa fa-envelope"></i>&nbsp;&nbsp;Messages </a>
                        </li>
                          <li>
                            <a href="<c:url  value="/profile/view?action=appointments" />">
                           <i class="fa fa-calendar"></i>&nbsp;&nbsp;Appointments </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/profile/view?action=treatments" />">
                           <i class="fa fa-gavel"></i>&nbsp;&nbsp;Treatments </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/profile/view?action=insurance" />">
                           <i class="fa fa-medkit"></i>&nbsp;&nbsp;Insurance </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/profile/view?action=documents" />">
                           <i class="fa fa-file-pdf-o" aria-hidden="true"></i>&nbsp;&nbsp;Documents </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/profile/view?action=settings" />">
                           <i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;&nbsp;Settings </a>
                        </li>
                       
                        <li class="divider">
                        </li>
                        
                        <li>
                            
                            
                          <c:url value="/logout" var="logout" />
                    	<form action="${logout}" method="POST" id="logoutForm">
			            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		                
	                   </form>
	                   <a href="#" onclick="submitLogout()"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout </a>
                        </li>
        
    </c:when>    
    <c:otherwise>
    
                      <li>
                            <a href="#">
                            <i class="glyphicon glyphicon-th-large" style="font-size: 13px !important; display: inline !important;"></i></i> Dashboard </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/profileborrower.html" />">
                           <i class="glyphicon glyphicon-edit" style="font-size: 13px !important; display: inline !important;"></i></i> Borrower Profile </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/profilecosigner.html" />">
                            <i class="glyphicon glyphicon-edit" style="font-size: 13px !important; display: inline !important;"></i></i> Co-Signer Profile </a>
                        </li>
                        
                        
                         <li>
                            <a href="<c:url  value="/displayborrowerloans.html" />">
                           <i class="glyphicon glyphicon-edit" style="font-size: 13px !important; display: inline !important;"></i></i> Check Loans </a>
                        </li>
                        <li>
                            <a href="<c:url  value="/displaycosignerloans.html" />">
                            <i class="glyphicon glyphicon-edit" style="font-size: 13px !important; display: inline !important;"></i></i> Co-Sign Loans </a>
                        </li>
                       
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">
                            <i class="glyphicon glyphicon-cog" style="font-size: 13px !important; display: inline !important;"></i></i> Settings </a>
                        </li>
                        <li>
                            <a href="#">
                            
                            <i class="glyphicon glyphicon-log-out" style="font-size: 13px !important; display: inline !important;"></i></i>   <c:url value="/logout" var="logout" />
                    	<form action="${logout}" method="POST">
			            <input type="hidden" name="${_csrf.parameterName}"
			                               value="${_csrf.token}" />
		                <input type="submit" >Logout </>
	                   </form> </a>
                        </li>
     </c:otherwise>
</c:choose>
                    
                    </ul>
                </li>
    </c:otherwise>
</c:choose>


          </ul>

        </div>
      </div>
     
    </div>
  
  
  
  <!-- Modal Getting To The Office Video-->
  <div class="modal fade" id="officeVideoModal" role="dialog" style="height: 600px;width: 900px;">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Getting To The Office</h4>
        </div>
        <div class="modal-body">
          <video width="320" height="240" controls>
  <source src="<c:url value='/resources/video/gettingtoDrKangsoffice.mov'/>" type="video/webm">
  

</video>
          
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary">Got It.</button>
        </div>
      </div>
      
    </div>
  </div>
<script type="text/javascript">

function submitLogout()
{
	
	/* document.getElementById("logoutForm").action =; */
        
        document.getElementById("logoutForm").submit();
        
	}

</script>


</body>
</html>