window.onload = function(){
	
	$.ajax({
		url: "medalje/getUserNaSkali",
		type:"GET",
		success : function(data){
				
		},
		error: function(){
		}
		});
	
}

function postaviSkalu(){
	
	var $form = $("#medaljeForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "medalje/postaviSkalu",
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste postavili bodovnu skalu!");
				top.location.href = "medalje.html"
			}else
				alert("Samo administrator sistema moze da postavlja bodovnu skalu!");
			
		}
	
	});
}

function getFormData($form){
	
	 var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	
}