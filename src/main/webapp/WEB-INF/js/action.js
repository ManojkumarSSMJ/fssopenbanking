$(window).load(function(){
	// header banner   
    $('.header-slider').flexslider({
    	animation: "slide"
  	});
	// feature slider
 
	$('.thumb-slider').flexslider({
        animation: "slide",
        animationLoop: true,
        itemWidth: 300,
		easing: "swing",
		controlNav: false,
		minItems: 1,
    	maxItems: 3
    });
	// custom scroll bar
   	$(".custom-scroll").mCustomScrollbar();

});


$(document).ready(function() {
	
	// page height
	var PageHeight = $(window).height();
	var PageWidth = $(window).width();
	//alert(PageWidth);
	
	var HeaderHeight = $("header").height();
	var FooterHeight = $("footer").height();
	
	var HeaderandFooterHeight = HeaderHeight + FooterHeight;
	var CurrentBodyHeight = PageHeight - HeaderandFooterHeight;
	$(".body-container").css("min-height",CurrentBodyHeight);
	
	// system date 
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	if(month == 1){
		month = "January";
		}else if(month == 2){
		month = "February";
		}else if(month == 3){
		month = "March";
		}else if(month == 4){
		month = "April";
		}else if(month == 5){
		month = "May";
		}else if(month == 6){
		month = "June";
		}else if(month == 7){
		month = "July";
		}else if(month == 8){
		month = "August";
		}else if(month == 9){
		month = "September";
		}else if(month == 10){
		month = "October";
		}else if(month == 11){
		month = "November";
		}else if(month == 12){
		month = "December";
		}else{}
	var output = ((''+day).length<2 ? '0' : ' ') + day + ((''+month).length<2 ? '0' : ' ') + month + ' ' + d.getFullYear() + ' ';
	
	$(".breadcrumb").next("span").css("text-align","right");
	
	$("#system-date").html(output);
	
	// Header search field
    $("nav.topmenu .search").click(function(){
		$(this).find("input").show();
		$(this).animate({width: '181px'}, 2000, 'easeOutElastic');
	});
	
	// dialog box dynamic width
	$(".modal-wide").on("show.bs.modal", function() {
	  var height = $(window).height() - 200;
	  $(this).find(".modal-body").css("max-height", height);
	});
	
	// wizard tab  
	$('.next').click(function(){
	  var nextId = $(this).parents('.tab-pane').next().attr("id");
	  $('[href=#'+nextId+']').tab('show');
	});
	$('.previous').click(function(){
	  var nextId = $(this).parents('.tab-pane').prev().attr("id");
	  $('[href=#'+nextId+']').tab('show');
	})
	
	// wizard inactive tabs
	/**/$(".inactive-tab-wizard").find('.tab-pane').css({"position":"absolute","top":"-1000px"});
	$(".inactive-tab-wizard").find('.tab-pane:first-child').removeAttr("style");
	$('.next-tab').click(function(){
		var nextId = $(this).parents('.tab-pane').next().attr("id");
		$(".inactive-tab-wizard").find('.'+nextId).tab('show');
		$(".inactive-tab-wizard").find('.tab-pane').css({"position":"absolute","top":"-1000px"});
		$(".inactive-tab-wizard").find('#'+nextId).removeAttr("style");
	});
	$('.previous-tab').click(function(){
		var preId = $(this).parents('.tab-pane').prev().attr("id");
		$(".inactive-tab-wizard").find('.'+preId).tab('show');
		$(".inactive-tab-wizard").find('.tab-pane').css({"position":"absolute","top":"-1000px"});
		$(".inactive-tab-wizard").find('#'+preId).removeAttr("style");
	});
	
	// Image popup	
	$('.screen-thumbnail').click(function(){
		$("#image-viewer").find('.modal-body').empty();
		var title = $(this).parent('a').attr("title");
		var imgpath = $(this).attr('src');
		//alert(title);
		//alert(imgHTML);
		$("#image-viewer").find('.modal-title').html(title);
		
		$("#image-viewer").find('.modal-body').append('<img src='+imgpath+' width="100%">');
		$('#image-viewer').modal({show:true});
	});
		
	// Dashboard action icon section
	/*$("ul.actions a").click(function(){
		$(".sub-items").find("ul").hide();
		$("ul.actions li").removeClass("open");
		
		$(this).parent().toggleClass("open");
		var CurrentLink = $(this).attr("class");
		
		if(CurrentLink == "filter-icon"){
			$(".filter-sub-item").slideToggle(500);
		}else if(CurrentLink == "update-remarks-icon"){
			$(".ur-sub-item").slideToggle(500);
		}else if(CurrentLink == "set-remainder-icon"){
			$(".sr-sub-item").slideToggle(500);
		}else if(CurrentLink == "tickets-close-icon"){
			$(".tc-sub-item").slideToggle(500);
		}else{}
	});*/
	
// FORM CONTROLS
	
	// select
	//if ($("select").hasClass("select_styled")) {
		//cuSel({changedEl: ".select_styled", visRows: 7});
		//cuSelRefresh({refreshEl: ".select_styled", visRows: 7});
		
		//$(".cusel").each(function () {
//    	var w = parseInt ($(this).find(".cuselActive").width()),scrollPanel = $(this).find(".cusel-scroll-pane");
//		//alert(w);
//		var aa = $(this).find(".cuselText").width();
//		alert(aa);
//			if(w >= scrollPanel.width()){
//				$(this).find(".jScrollPaneContainer").width(w);
//				scrollPanel.width(w);
//			};
//		});

	//}
		var params = {
			changedEl: ".select_styled", visRows: 7, scrollArrows: false
		}
		cuSel(params);
		
		var Refreshparams = {
			refreshEl: ".select_styled", visRows: 7, scrollArrows: false
		}
		cuSelRefresh(Refreshparams);
	
	//var comboboxlength = $(".select_styled .cusel-scroll-pane").find("span").length;	
	//alert(comboboxlength);
	// checkbox	
	$('input.checkbox').each(function() {
		$(this).prettyCheckable({
			labelPosition: 'right'
		});
	});

	// radio button
	$('input.radio').each(function() {
		$(this).prettyCheckable({
			labelPosition: 'right'
		});
	});	
		
	//color picker	
	$(".colorpicker").spectrum();
	$(".colorpicker").show();
	
	//Tooltip
    $("[data-toggle='tooltip']").tooltip();
	
	// Range Slider
	$("#slider").rangeSlider();
	
	// accordion
	
	$('.accordion h2').addClass("closed");
	var allPanels = $('.accordion').find('.content').hide();
    $('.accordion h2').click(function() {
    	if($(this).is(".closed")){
			allPanels.slideUp();
			$('.accordion h2').addClass("closed");
			$(this).removeClass("closed").addClass("open").next().slideDown();
			return false;
		} else {
			allPanels.slideUp();
			$('.accordion h2').addClass("closed");
		}
	  });
	  
	// Responsive Tabs 
	$('.tabresp').tabCollapse();
	
	// Data Grid  
	
	$(".datagridwithsearch").dataTable();
	$(".dataTables_paginate").bind("click",".paginate_button",function(){
		$("#datagridwithsearch .prettyradio").each(function(){
 			$(this).find("a").removeClass("checked");
		});
	});	
	$('.modal').on("click", "a.inner-popup", function (e) {
		var target = $(this).attr('href');
		$('.modal-content').html('');
		$('.modal-content').load(target).appendTo($('.modal-content'));
		e.preventDefault();
	});	
	
	
});

//Open lightbox
function lightbox(){
	(function($) {
		var theShadow = $('<div id="lightbox-shadow"/>');
		$('body').append(theShadow);
		$('#lightbox-shadow').show();
		$('body').css("overflow","hidden");
	})(jQuery); 
}

// close lightbox
function closeLightbox(){
	(function($) {
		$('#lightbox-shadow').hide();
		$('body').removeAttr("style","overflow");
	})(jQuery); 
}
