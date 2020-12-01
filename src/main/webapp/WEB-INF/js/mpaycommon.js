$(window).on('load',function() {
	  setTimeout(function(){$('#loading').hide() }, 500);  
 });
function getArrayFromClass(className) {
		 var arr = [];
			$("."+className).each(function(){
				if($(this).is(':checked')) {
					arr.push($(this).val());
				}
			});
			return arr;
	 }
$(document).ready(function(){
	
	
	 $('.mpayTable').DataTable( {
	        "pagingType": "full_numbers",
	         stateSave: true

	    } );
	 
	 $("input[type=text],textarea").bind('keyup keydown keypress',function(e) {
			
		  $(this).attr( 'autocomplete', 'off' );
		  if(e.keyCode == 18) {
			  $(this).val('');
		  }
		});
	 
	 $('input,textarea').bind('drop', function(event) {
		    event.preventDefault();
	 });
	 $(document).on("click", "[data-hide-closest]", function(e) {
		  e.preventDefault();
		  var $this = $(this);
		  $this.closest($this.attr("data-hide-closest")).hide();
		}); 
	 
	 
	 
	 
});