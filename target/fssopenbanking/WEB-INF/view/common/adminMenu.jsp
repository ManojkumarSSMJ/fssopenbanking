<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-inverse mainmenu" role="navigation">
            	<div class="container">
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".mm-resp">
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                  </div>
                  <div class="navbar-collapse collapse mm-resp">
                    <ul class="nav navbar-nav">
                      <li><a href="#"><span>Consent Details</span></a>
	                      <ul class="dropdown-menu">
								  <li><a href="showConsent">Show Consent</a></li>
	                      </ul>   
                       </li>
                      <li><a href="#"><span>Settings</span></a>
	                      <ul class="dropdown-menu">
								  <li><a href="dashboard">Dashboard</a></li>
	                      </ul>   
                       </li>
                    </ul>
                  </div>
             </div>
</div>
