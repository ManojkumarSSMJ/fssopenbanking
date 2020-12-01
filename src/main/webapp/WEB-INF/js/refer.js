$(document).ready(function() {
	// Auto Complete
	
	//var source_url = "${pageContext.request.contextPath}/merchantsales/BankersalesNamesautoComplete.action?source="+$("#selectSource").val()+"&q="+$("#bankersalesNames").val();
	//alert(source_url);
	$("#bankersalesNames").typeahead({
	    source: function (query, process) {
	        return $.ajax({
	            url: "${pageContext.request.contextPath}/merchantsales/BankersalesNamesautoComplete.action?source="+$("#selectSource").val()+"&q="+$("#bankersalesNames").val(),
	            type: 'post',
	            data: { query: query },
	            dataType: 'json',
	            success: function (result) {
	                var resultList = result.map(function (item) {
	                    var aItem = { id: item.value, name: item.label };
	                    return JSON.stringify(aItem);
	                });
	                return process(resultList);
	            }
	        });
	    },

	matcher: function (obj) {
	        var item = JSON.parse(obj);
	        return ~item.name.toLowerCase().indexOf(this.query.toLowerCase())
	    },

	    sorter: function (items) {          
	       var beginswith = [], caseSensitive = [], caseInsensitive = [], item;
	        while (aItem = items.shift()) {
	            var item = JSON.parse(aItem);
	            if (!item.name.toLowerCase().indexOf(this.query.toLowerCase())) beginswith.push(JSON.stringify(item));
	            else if (~item.name.indexOf(this.query)) caseSensitive.push(JSON.stringify(item));
	            else caseInsensitive.push(JSON.stringify(item));
	        }

	        return beginswith.concat(caseSensitive, caseInsensitive)

	    },


	    highlighter: function (obj) {
	        var item = JSON.parse(obj);
	        var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
	        return item.name.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
	            return '<strong>' + match + '</strong>'
	        })
	    },

	    updater: function (obj) {
	        var item = JSON.parse(obj);
	        $('#IdControl').attr('value', item.id);
	        return item.name;
	    }
	});

	
	
		//$("#tabs").tabs();
		//$(".button").button();
		//$(".radio").buttonset();
		//$(".datepicker").datepicker("option", "maxDate", '+0d');
		
		/*$("#bankersalesNames").typeahead({
			type: "POST",
			delay: 500,
	        minLength: 1,
	        datatype: "json",
	        source : function (request, response) 
	        {                         
	            var source_url = "${pageContext.request.contextPath}/merchantsales/BankersalesNamesautoComplete.action?source="+$("#selectSource").val()+"&q="+$("#bankersalesNames").val();

	            $.ajax({ 
	                url: source_url,
	                success: function (data) {response(data); },
	                error : function (a,b,c) {HandleLookUpError(a); }
	                });
	        }, 
			focus: function(event, ui) {
				// prevent autocomplete from updating the textbox
				event.preventDefault();
				// manually update the textbox
				$(this).val(ui.item.value+"|"+ui.item.label);
			},
			select: function(event, ui) {
				// prevent autocomplete from updating the textbox
				event.preventDefault();
				// manually update the textbox and hidden field
				$(this).val(ui.item.label);
				$("#bankerSalesSqid").val(ui.item.value);
				$("#bsb").val(ui.item.bsb);
				$("#buid").val(ui.item.buid);
				$("#telephone").val(ui.item.phone);
				$("#emailId").val(ui.item.email);
				
			}
		});*/
	});
