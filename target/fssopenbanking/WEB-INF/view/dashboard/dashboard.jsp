<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>

<link href="css/dashboardTwo.css" rel="stylesheet">

<!-- FusionChart files Start-->
    <script type="text/javascript" src="js/fusioncharts.js"></script>
    <script type="text/javascript" src="js/fusioncharts.theme.carbon.js"></script>
    <script type="text/javascript" src="js/fusioncharts.theme.ocean.js"></script>
    <script type="text/javascript" src="js/fusioncharts.theme.zune.js"></script>
    <!-- FusionChart files End-->

<script>
var chart = [];
var data = [];

userStatistics();
monthlyWiseApiStatistics();
bankWiseUserStatistics();
bankWiseConsentStatistics();

function userStatistics() {
        var chartObject = new Object();
       
        chartObject.bgColor = '#ffffff';
        chartObject.rotateLabels = '0';
       
        chartObject.showpercentvalues = '0';
       
        chartObject.showlegend = '1';
        chartObject.theme = 'zune';
       
        chartObject.useRoundEdges = '0';
        chartObject.labelDisplay = 'auto';
           
        var dataArr = [];
        var lcvObject = new Object(); 
        '<c:forEach items="${apiStatisticsList}" var="apiStatisticsObject">'
             lcvObject = new Object(); 
             lcvObject.label = '<c:out value="${apiStatisticsObject.label}"/>';
             lcvObject.color = '<c:out value="${apiStatisticsObject.color}"/>';
             lcvObject.value = '<c:out value="${apiStatisticsObject.value}"/>';
             dataArr.push(lcvObject);
        '</c:forEach>'
        
            FusionCharts.ready(function () {
                chart[0] = new FusionCharts({
                    type: 'pie2d',
                    renderAt: 'apiStatisticsChart',
                    width: '100%',
                    height: '100%',
                    dataFormat: 'json',
                    dataSource: {
                    "chart": chartObject,
                    "data": dataArr
                    }
            });

                chart[0].render();
        });
    }
function monthlyWiseApiStatistics() {

		var labelArr = [];
		var dataArr = [];
		var dataSetArr = [];
		var labelObject;
		var valueObject;
		var seriousObject;

		'<c:forEach var="monthsObject" items="${months}">'
		    labelObject = new Object();
		    labelObject.label='${monthsObject}';
		    labelArr.push(labelObject);
		'</c:forEach>'
		
		'<c:forEach var="valueList" items="${monthWiseApiStatisticsList[0].valueList}">'
			     valueObject = new Object();
			     valueObject.value = '${valueList}';
			     dataArr.push(valueObject);
		 '</c:forEach>'
		 
		 dataSetArr.push(dataArr);
		 dataArr = [];
		 
		 '<c:forEach var="valueList" items="${monthWiseApiStatisticsList[1].valueList}">'
			     valueObject = new Object();
			     valueObject.value = '${valueList}';
			     dataArr.push(valueObject);
		 '</c:forEach>'
		 
		dataSetArr.push(dataArr);

        const dataSource = {
	        chart: {
	           /*  caption: "App Publishing Trend",
	            subcaption: "2012-2016", */
	            xaxisname: "Months",
	            yaxisname: "Transaction Count",
	            formatnumberscale: "1",
	            plottooltext:
	              "<b>$dataValue</b> transactions were available on <b>$seriesName</b> in $label",
	            theme: "light2",
	            drawcrossline: "1"
	          },
	          categories:  [
	   	                {
	     	              category: labelArr
	     	            }
	     	          ],
	          dataset: [
		        	  {
		        		  seriesname: '${monthWiseApiStatisticsList[0].label}',
		   	              color: '${monthWiseApiStatisticsList[0].color}',
		   	              data: dataSetArr[0]
		   	          },
		   	          {
		   	        	seriesname: '${monthWiseApiStatisticsList[1].label}',
		   	              color: '${monthWiseApiStatisticsList[1].color}',
		   	              data: dataSetArr[1]
		   	          },
		   	          
		          ] 
        };

        FusionCharts.ready(function() {
        	   chart[1] = new FusionCharts({
        	    type: "mscolumn2d",
        	    renderAt: "monthlyWiseApiStatisticsChart",
        	    width: "100%",
        	    height: "100%",
        	    dataFormat: "json",
        	    dataSource
        	  });
        	   chart[1].render();
        	});
}

function bankWiseUserStatistics() {

	var labelArr = [];
	var dataArr = [];
	var labelObject;
	var valueObject;

	 '<c:forEach var="bankWiseUserStatisticsObject" items="${bankWiseUserStatisticsList}">'
	     labelObject = new Object();
	     labelObject.label='${bankWiseUserStatisticsObject.bankName}';
	     labelArr.push(labelObject);
     '</c:forEach>'
     

      '<c:forEach var="bankWiseUserStatisticsObject" items="${bankWiseUserStatisticsList}">'
	      valueObject = new Object();
	      valueObject.value = '${bankWiseUserStatisticsObject.count}';
	      dataArr.push(valueObject);
      '</c:forEach>'
      
       const dataSource = {
   	        chart: {
   	            xaxisname: "Bank Name",
   	            yaxisname: "Count",
   	            formatnumberscale: "1",
   	            plottooltext:
   	              "<b>$dataValue</b> counts were available on <b>$seriesName</b> in $label",
   	            theme: "light2",
   	            drawcrossline: "1"
   	          },
   	          categories: [
   	            {
   	              category: labelArr
   	            }
   	          ],
   	          dataset: [
   	            {
   	              color: '#008000',
   	              data: dataArr
   	            }
             ]
           };

    FusionCharts.ready(function() {
    	    chart[2] = new FusionCharts({
    	    type: "mscolumn2d",
    	    renderAt: "bankWiseUserStatisticsChart",
    	    width: "100%",
    	    height: "100%",
    	    dataFormat: "json",
    	    dataSource
    	  });
    	   chart[2].render();
    	});
}

function bankWiseConsentStatistics() {

	var labelArr = [];
	var dataArr = [];
	var labelObject;
	var valueObject;

	 '<c:forEach var="bankWiseConsentStatisticsObject" items="${bankWiseConsentStatisticsList}">'
	     labelObject = new Object();
	     labelObject.label='${bankWiseConsentStatisticsObject.bankName}';
	     labelArr.push(labelObject);
     '</c:forEach>'
     

      '<c:forEach var="bankWiseConsentStatisticsObject" items="${bankWiseConsentStatisticsList}">'
	      valueObject = new Object();
	      valueObject.value = '${bankWiseConsentStatisticsObject.count}';
	      dataArr.push(valueObject);
      '</c:forEach>'
      
       const dataSource = {
   	        chart: {
   	            xaxisname: "Bank Name",
   	            yaxisname: "Count",
   	            formatnumberscale: "1",
   	            plottooltext:
   	              "<b>$dataValue</b> counts were available on <b>$seriesName</b> in $label",
   	            theme: "light2",
   	            drawcrossline: "1"
   	          },
   	          categories: [
   	            {
   	              category: labelArr
   	            }
   	          ],
   	          dataset: [
   	            {
   	              color: '#008000',
   	              data: dataArr
   	            }
             ]
           };

    FusionCharts.ready(function() {
    	    chart[2] = new FusionCharts({
    	    type: "mscolumn2d",
    	    renderAt: "bankWiseConsentStatisticsChart",
    	    width: "100%",
    	    height: "100%",
    	    dataFormat: "json",
    	    dataSource
    	  });
    	   chart[2].render();
    	});
}

//This function is to maximize and minimize the charts
function resizeChart(resizeFlagId, priority, totalCharts)
{
    if(resizeFlagId.value == "min")
    {
        for(i = 1; i <= totalCharts; i++)
        {
            
            if(i != priority)
            {
                document.getElementById("list"+i).style.display = 'none';
            }
        }
        
        resizeFlagId.value = 'max';
        var HeaderHeight = $("header").height();
        if(chart[priority] != undefined)
        {
            chartHeight = chart[priority].height;
            chart[priority].height = $(window).height() - HeaderHeight;
            $("#container"+priority).css('height','100%');
            $("#ulId").find('li').removeClass().addClass('col-lg-12');
            
        }
        
        if($('#ulId').hasClass("widgets-three-cols"))
        {
            $('#ulId').removeClass('widgets-three-cols');
            appliedClass = 'widgets-three-cols';
        }
        else if($('#ulId').hasClass("widgets-two-cols"))
        {
            $('#ulId').removeClass('widgets-two-cols');
            appliedClass = 'widgets-two-cols';
        }
        
        if(grid[priority] != undefined)
        {
            grid[priority].setGridWidth($("#container"+priority).width());
        }
        
        
        
    }
    else if(resizeFlagId.value == "max")
    {
    
        for(i = 1; i <= totalCharts; i++)
        {
            
            if(i != priority)
            {
                document.getElementById("list"+i).style.display = '';
            }
            
        }
        resizeFlagId.value = 'min';
        if(chart[priority] != undefined)
        {
            chart[priority].height = chartHeight;
            $("#container"+priority).css('height','370px');
            $("#ulId").find('li#list1').addClass('col-lg-8');
        } else {
            $("#container"+priority).css('height','330px');            
        }

        $('#ulId').addClass(appliedClass);
        
        if(grid[priority] != undefined)
        {
            grid[priority].setGridWidth($("#container"+priority).width()); 
        }
    }
}


</script>           
<input id="resizeFlag1" type="hidden" value="min">
<input id="resizeFlag2" type="hidden" value="min">
<input id="resizeFlag3" type="hidden" value="min">
<input id="resizeFlag4" type="hidden" value="min">
        <div class="container">  
	          <section class="content-container">
	              <article class="col-lg-12">
	                <ul id="ulId" class="widgets widgets-three-cols">
						<li id="list1" class="col-lg-8">
						      <h2>
						          <span>API Statistics</span>
						          <span class="icon">
						                  <a href="javascript:resizeChart(resizeFlag1, 1, 4)" class="restore-small-icon"></a>
						          </span>
						      </h2>
						   
						      <div class="box" id="container1">
						      	<div id="apiStatisticsChart" style="height:100%;padding: 1%;"></div>
						      </div>
						  
						</li>
						<li id="list2" class="col-lg-4">
						     <h2>
						         <span>Monthly Wise API Statistics</span>
						         <span class="icon">
						                 <a href="javascript:resizeChart(resizeFlag2, 2, 4)" class="restore-small-icon"></a>
						         </span>
						     </h2>
						    
						     <div class="box" id="container2">
						     	<div id="monthlyWiseApiStatisticsChart" style="height:100%;padding: 1%;"></div>
						   </div>
						 </li>
						<li id="list3" class="col-lg-4">
						    <h2>
						        <span>Bank Wise User Statistics</span>
						        <span class="icon">
						                <a href="javascript:resizeChart(resizeFlag3, 3, 4)" class="restore-small-icon"></a>
						        </span>
						    </h2>
						   
						    <div class="box" id="container3">
						    	<div id="bankWiseUserStatisticsChart" style="height:100%;padding: 1%;"></div>
						      </div>
						</li>
						<li id="list4" class="col-lg-4">
						     <h2>
						         <span>Bank Wise Consent Statistics</span>
						         <span class="icon">
						                 <a href="javascript:resizeChart(resizeFlag4, 4, 4)" class="restore-small-icon"></a>
						         </span>
						     </h2>
						    
						     <div class="box" id="container4">
						     	<div id="bankWiseConsentStatisticsChart" style="height:100%"></div>
						    </div>
						</li>
	                        
	                	</ul>
	           		 </article>
	          </section>
        </div>