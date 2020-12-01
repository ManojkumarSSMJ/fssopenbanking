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
<meta http-equiv="Content-Security-Policy" content="default-src gap://ready file://* *; style-src 'self' http://* https://* 'unsafe-inline'; script-src 'self' http://* https://* 'unsafe-inline' 'unsafe-eval'">
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
    <script src="js/action.js"></script>
    <script src="js/responsive-action.js"></script>
     <script src="js/mpaycommon.js"></script>
    <!-- Responsive --> 
    <link rel="stylesheet" href="css/responsive.css" type="text/css" media="all" />
    
  </head>
 <body class="prelogin">
	<div id="loading">
  <img id="loading-image" src="images/common/progress.gif" alt="Loading..." />
</div>
			<tiles:insertAttribute name="header"/>
			<tiles:insertAttribute name="body"/>
			<tiles:insertAttribute name="footer"/>

		<!-- Modal -->
		
		<c:set var="responseMessage" value="${responseMessage}" />
		<script>
		
     $(document).ready(function() {
    	var message = "${responseMessage}";
    
	    if(message != "" && message != null){
	     	$('#myModal').modal("show");
	     	
	     	$('#myModal').find(".modal-content").load("<%=request.getContextPath()%>/login", function(){
	     		$("#loginfailtext").text(message);
	     		$("#loginfaildiv").css('display','block');
	     	});
	     
	    }
	  
	 
    
    });
    </script>

	
<!-- Modal -->
    <div class="modal modal-wide fade" id="myModal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
               
            </div>
        </div>
</div>		
<!-- Image viewer -->		
<div id="image-viewer" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog">
  <div class="modal-dialog">
  <div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3 class="modal-title">Heading</h3>
	</div>
	<div class="modal-body">
		
	</div>
	<div class="modal-footer">
	
		<button class="btn btn-default gray-btn" data-dismiss="modal">Close</button>
	</div>
   </div>
  </div>
</div>

	</body>
	
</html>