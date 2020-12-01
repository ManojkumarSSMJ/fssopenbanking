<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib  uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib  uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:insertAttribute name="title"/></title>
	<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/obc/favicon.ico" />
    <link href="css/bootstrap.css" rel="stylesheet">
    
    <!--[if IE 8]>
      <script src="js/html5.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->   
   <!--[if IE 7]>   
      <script src="js/respond.min.js"></script>
    <![endif]-->
    
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script src="js/jquery.mousewheel.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.mCustomScrollbar.js"></script>
   
    <!-- flexslider --> 
    <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
    <script defer src="js/jquery.flexslider.js"></script>
    
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.css" />
    
    <!-- Bootstrap Addon CSS -->
	<link href="css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.smartmenus.js"></script>
	<script type="text/javascript" src="js/jquery.smartmenus.bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap-tabcollapse.js"></script>
    
    <!-- Combo, Check box and Radio button -->
	<link href="css/combobox.css" type="text/css" rel="stylesheet"/>
    <link href="css/checkboxradio.css" type="text/css" rel="stylesheet"/>
      <link href="css/mpaycommon.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/combobox.js"></script>
    <script type="text/javascript" src="js/checkboxradio.js"></script>
    
    <!-- Date and time picker -->
	<script src="js/datetimepicker.js"></script>
    
    <!-- Color Picker --->
    <link href="css/colorpicker.css" rel="stylesheet">
	<script src="js/colorpicker.js"></script>
    
    <!-- Range Slider -->
    <link rel="stylesheet" href="css/range-slider.css">
    <script src="js/RangeSliders-min.js"></script>
    
    <!-- Data Grid -->
    <link rel="stylesheet" href="css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/dataTables.responsive.css">
    <script src="js/dataTables.bootstrap.js"></script>
    <script src="js/jquery.dataTables.min.js"></script>
    <script src="js/dataTables.colReorder.js"></script>
    <script src="js/dataTables.colResize.js"></script>
    
    <script src="js/bootstrap3-typeahead.js"></script>
    
    <!-- default CSS -->
    <link href="css/stylesheet.css" rel="stylesheet">  
    <!-- Theme CSS -->
    <link href="css/red-theme.css" rel="stylesheet">
    
     <!--[if IE 8]>
      <link rel="stylesheet" href="css/ie8.css" />
      <script src="js/ie8-action.js"></script>
    <![endif]-->
    <!--[if IE 7]>
      <link rel="stylesheet" href="css/bootstrap-ie7.css" />
      <link rel="stylesheet" href="css/ie7.css" />
    <![endif]-->
    
    <!-- default JS -->
  
    <script src="js/responsive-action.js"></script>
     <script src="js/mpaycommon.js"></script>
     <script src="js/mpayvalidation.js"></script>
       <script src="js/action.js"></script>
     <script type="text/javascript">
     
  
     </script>
    <!-- Responsive --> 
    <link rel="stylesheet" href="css/responsive.css" type="text/css" media="all" />
    
  </head>
<body class="inner">
<div id="loading">
  <img id="loading-image" src="images/common/progress.gif" alt="Loading..." />
  <p id="loading-text">Loading...</p>
</div>
	<div class="wrapper">
	
	<header>
		<div class="row-one">
			<div class="container">
				<nav class="navbar navbar-default topmenu" role="navigation">
				 <div class="container-fluid">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                              </button>
                            </div>
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <span id="system-date" class="gray-txt col-xs-push-3"></span>
                              <ul class="nav navbar-nav">
                                    <li class="home"><a href="<%=request.getContextPath()%>/hubDashboard" class="active"><span class="show"></span><span class="hide">Home</span></a></li>
                                    <li>
                                    Welcome <c:out value="${sessionScope.userDetails.userName}"/>
                                    </li>
    							 <li class="logout">
                                    <a href="<%=request.getContextPath()%>/logout" class="">
                                    <span class="show"></span><span>Logout</span></a></li>
                                 <!--     <li class="top-db-icon"><a href="#" class=""><span class="show"></span><span>Dashboard</span></a></li> -->

                                    
                                    <!-- <li class="contact"><a href="mailto:helpdesk@fss.co.in" class=""><span class="show"></span><span class="hide">Contact</span></a></li> -->
                              </ul>
                            </div><!-- /.navbar-collapse -->
                          </div><!-- /.container-fluid -->
				</nav>
			</div>
		</div>
		<div class="row-two">
			<div class="container">
                	<div class="col-xs-4 logo" style="float: left;margin-left: 20px">
						<a href="#">
						<img src="images/FSSlogo.png" alt="FSS Mobile Banking" border="0" />			
						</a>
					</div>
					<div class="col-xs-3 logo" style="float: left;margin-left: 20px">
						<a href="#">
						<img src="images/openbanking-hub.png" alt="FSS Mobile Banking" border="0" />			
						</a>
					</div>
					<div class="col-xs-1 logo" style="float: right;">
						<a href="#">
						<img src="images/afs.png" alt="FSS Mobile Banking" border="0" />			
						</a>
					</div>
                 <%--    <div class="col-lg-9 col-sm-9 col-xs-12 gray-txt text-right">
                    	<br>                   	
                    	  User Role : <c:out value="${sessionScope.USER_SESSION.userType}"/>
						  Last login date &amp; time: <c:out value="${sessionScope.USER_SESSION.lastLoginTime}"/>								
					</div> --%>
                </div>
		</div>
			<tiles:insertAttribute  name="menu" />						
	</header>
	
	<div class="container">
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
	</div>
  
  </body>
  </html>