$(document).ready(function(){
	$("#home_div").show();
	$("#friends_div").hide();
	$("#log_div").hide();
	$("#profile_div").hide();
	
	$('a[href="#home"]').click(function(){
		$("#home_div").show();
		$("#friends_div").hide();
		$("#log_div").hide();
		$("#profile_div").hide();
	});
	
	$('a[href="#friends"]').click(function(){
		$("#home_div").hide();
		$("#friends_div").show();
		$("#log_div").hide();
		$("#profile_div").hide();
	});
		
	$('a[href="#log"]').click(function(){
		$("#home_div").hide();
		$("#friends_div").hide();
		$("#log_div").show();
		$("#profile_div").hide();
	});
	
	$('a[href="#profile"]').click(function(){
		$("#home_div").hide();
		$("#friends_div").hide();
		$("#log_div").hide();
		$("#profile_div").show();
	});
		
});