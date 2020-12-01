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
	<link rel="shortcut icon" type="image/x-icon" href="<c:out value="${iconUrl}"/>" />
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
    <script src="js/action.js"></script>
    <script src="js/responsive-action.js"></script>
     <script src="js/mpaycommon.js"></script>
     <script src="js/mpayvalidation.js"></script>
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
		
						
	</header>
	
	<div class="container">
	 <div class="loginImg">
  <img src="<c:out value="${logoUrl}"/>"/>
  </div>
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
	</div>
  
  </body>
  </html>