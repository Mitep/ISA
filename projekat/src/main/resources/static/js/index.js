$(document).ready(function(){
	$("#home_div").show();
	$("#friends_div").hide();
	$("#log_div").hide();
	$("#profile_div").hide();
		
	$('a[href="#log"]').click(function(){
		$("#home_div").hide();
		$("#friends_div").hide();
		$("#log_div").show();
		$("#profile_div").hide();
	});
		
});

function getFormData($form){
	
	 var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	
}