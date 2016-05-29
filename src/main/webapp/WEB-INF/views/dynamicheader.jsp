<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Dr Kang's Dental</title>

<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge" /> -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" media="screen">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300italic'
	rel='stylesheet' type='text/css'>


<link rel="stylesheet"
	href="<c:url value='/resources/css/custom.min.css'/>">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script type="text/javascript" src="../bower_components/html5shiv/dist/html5shiv.js"></script>
      <script type="text/javascript" src="../bower_components/respond/dest/respond.min.js"></script>
    <![endif]-->







</head>
<body>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

<nav class="navbar navbar-custom navbar-fixed-top" style="margin-bottom: 20px;box-shadow: 1px 5px 12px -2px #828984;">
   <div class="row">
   <div class="col-lg-3">
      <img src="<c:url value='/resources/img/trans_logo.png'/>" 
         style="float: left; margin-right: 20px; margin-left: 5px" align="middle"></img>
      <br/>
   </div>
   <!--  <div class="navbar-collapse collapse" id="navbar-main"-->
   <div class="col-lg-9">
   <div class="row">
   
       <ul class="nav navbar-nav" style="float: left;margin-left: 10px;">
         <li><a href="<c:url value='/home'/>" class="navbar-brand"
            data-toggle="tooltip" title="Go Back To Homepage" style="font-size: 2em;"> Dr Kang's
         Dental</a></li>
      </ul>
      <ul class="nav navbar-nav" style="float: right;margin-right: 25px;">
         <li><a href="tel:(617)%20244-8087"><u><i>CALL NOW : (617) 244-8087</i></u></a></li>
      </ul>
   </div>
   <div class="row">
      <div class="navbar-header">
         
         <button class="navbar-toggle" type="button" data-toggle="collapse"
            data-target="#navbar-main">
         <span class="icon-bar"></span> <span class="icon-bar"></span> <span
            class="icon-bar"></span>
         </button>
      </div>
      <div class="navbar-collapse collapse" id="navbar-main" style="">
         <ul class="nav navbar-nav">
         <li><a href="<c:url value='/'/>"
                     data-toggle="tooltip" title="Go to home page">Home</a></li>
            <li class="dropdown">
               <a class="dropdown-toggle"
                  data-toggle="dropdown" href="#" id="aboutus">About Us<span
                  class="caret"></span></a>
               <ul class="dropdown-menu" aria-labelledby="aboutus">
                  <li><a href="<c:url value='/aboutme'/>"
                     data-toggle="tooltip" title="know about me">About Me</a></li>
                  <li class="divider"></li>
                  <li><a href="<c:url value='/services'/>"
                     data-toggle="tooltip" title="Our Services">Our Services</a></li>
                  <li class="divider"></li>
                  <li><a href="<c:url value='/gallery'/>"
                     data-toggle="tooltip" title="View our gallery">Our Gallery</a></li>
                  <li class="divider"></li>
                  <li><a data-toggle="modal" data-target="#officeVideoModal">Getting
                     To The Office</a>
                  </li>
               </ul>
            </li>
            <li><a href="<c:url value='/profile/scheduleappointment'/>"
               data-toggle="tooltip" title="View Appointments">Schedule
               Appointment</a>
            </li>
            <li><a href="<c:url value='/askme'/>" data-toggle="tooltip"
               title="Frequently asked questions">FAQ's</a></li>
            <li><a href="<c:url value='/contactus'/>"
               data-toggle="tooltip" title="Contact us/Help">Contact Us</a></li>
         </ul>
         <!-- dynamic profile menu  -->
         <ul class="nav navbar-nav navbar-right" style="margin-right: 25px;display: inline-block;">
            <c:choose>
               <c:when test="${user == null}">
                  <li><a href="<c:url value='/login/form'/>" id="signin"><i
                     class="glyphicon glyphicon-log-in"
                     style="font-size: 13px !important; display: inline !important;"></i>
                     Sign-In/Sign-Up</a>
                  </li>
               </c:when>
               <c:otherwise>
                  <li class="dropdown dropdown-user" id="userprofile">
                     <a
                        href="javascript:;" class="dropdown-toggle"
                        data-toggle="dropdown" data-hover="dropdown"
                        data-close-others="true"> <span
                        class="username username-hide-on-mobile"> <i
                        class="glyphicon glyphicon-user"
                        style="font-size: 13px !important; display: inline !important;"></i>
                     ${name}
                     </span> <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu dropdown-menu-default">
                        <c:choose>
                           <c:when test="${role == 'ROLE_USER'}">
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=profile" />
                                 ">
                                 <i class="fa fa-user"></i>&nbsp;&nbsp;My Profile
                                 </a>
                              </li>
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=messages" />
                                 ">
                                 <i class="fa fa-envelope"></i>&nbsp;&nbsp;Messages
                                 </a>
                              </li>
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=appointments" />
                                 ">
                                 <i class="fa fa-calendar"></i>&nbsp;&nbsp;Appointments
                                 </a>
                              </li>
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=treatments" />
                                 ">
                                 <i class="fa fa-gavel"></i>&nbsp;&nbsp;Treatments
                                 </a>
                              </li>
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=insurance" />
                                 ">
                                 <i class="fa fa-medkit"></i>&nbsp;&nbsp;Insurance
                                 </a>
                              </li>
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=documents" />
                                 ">
                                 <i class="fa fa-file-pdf-o" aria-hidden="true"></i>&nbsp;&nbsp;Documents
                                 </a>
                              </li>
                              <li>
                                 <a
                                 href="
                                 <c:url  value="/profile/view?action=settings" />
                                 ">
                                 <i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;&nbsp;Settings
                                 </a>
                              </li>
                              <li class="divider"></li>
                              <li>
                                 <c:url value="/logout" var="logout" />
                                 <form action="${logout}" method="POST" id="logoutForm">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                 </form>
                                 <a href="#" onclick="submitLogout()"><i
                                    class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout </a>
                              </li>
                           </c:when>
                           <c:otherwise>
                              <li>
                                 <a href="
                                 <c:url  value="/admin/dashboard" />
                                 "> <i
                                    class="glyphicon glyphicon-th-large"
                                    style="font-size: 13px !important; display: inline !important;"></i></i>
                                 Dashboard
                                 </a>
                              </li>
                              <li class="divider"></li>
                              <li>
                                 <c:url value="/logout" var="logout" />
                                 <form action="${logout}" method="POST" id="logoutForm">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                 </form>
                                 <a href="#" onclick="submitLogout()"><i
                                    class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout </a>
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
 </div>
</nav>

	
	
			

			
			

	



	<!-- Modal Getting To The Office Video-->
	<div class="modal fade" id="officeVideoModal" role="dialog"
		>
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Getting To The Office</h4>
				</div>
				<div class="modal-body">
					<video width=100% height=30% controls autoplay>
						<source
							src="<c:url value='/resources/video/gettingtoDrKangsoffice.mov'/>"
							type="video/webm">


					</video>


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

<br/>
<br/>
<br/>


</body>
</html>