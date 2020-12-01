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
                      <li><a href="#"><span>Account Access</span></a>
                      <ul class="dropdown-menu">
							  <li><a href="tppAccounts">Accounts</a></li>
							  <!-- <li><a href="tppBalanceDirect">Balance</a></li> -->
							  <li><a href="tppAllTransactions">Transactions</a></li>
							  <!-- <li><a href="tppBeneficiaries">Beneficiaries</a></li> -->
							  <li><a href="tppAllDirectDebits">Direct-Debits</a></li>
							  <li><a href="tppAllStandingOrders">Standing-Orders</a></li>
							<!--   <li><a href="tppProducts">Products</a></li> 
							  <li><a href="tppOffers">Offers</a></li>  -->
                       </ul>   
                            </li>
                         <li><a href="#"><span>Payment Initiation</span></a>
                      <ul class="dropdown-menu">
							  <li><a href="tppPayments">Single Immediate Payment</a></li>
							 <!--  <li><a href="tppPayments">Payment Submission</a></li> -->
                       </ul>   
                            </li>
                            <li><a href="#"><span>Open Data</span></a>
                      <ul class="dropdown-menu">
							 <li><a href="tppAtm">ATM</a></li>
							 <li><a href="tppBranch">Branches</a></li>
                       </ul>   
                            </li>
                            <li><a href="#"><span>Settings</span></a>
                      <ul class="dropdown-menu">
							 <li><a href="showBanks">Link Bank / Merchants</a></li>
							 <li><a href="fetchConsent">Update Consent</a></li>
							  <li><a href="fetchActivities">Activity</a></li>
                       </ul>   
                            </li>
                    </ul>
                  </div>
             </div>
</div>
