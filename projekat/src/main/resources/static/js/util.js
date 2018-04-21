/**
 * 	ovde se nalaze sve metode koje koristimo na vecini stranica 
 */

//funkcija u zavisnosti od tipa korisnika menja toolbar
$(document).ready(function(){

	if(sessionStorage.getItem("user_type") != null){
		$("#home_tb").show();
		$("#user_tb").show();
		$("#users_tb").show();
		$("#theatres_tb").show();
		$("#cinemas_tb").show();
		$("#friends_tb").show();
		$("#reservations_tb").show();
		$("#medalje_tb").show();
		$("#settings_tb").show();
		$("#fan_zone_tb").show();
		$("#login_tb").hide();
		$("#register_tb").hide();
		$("#logout_tb").show();
	} else {
		$("#home_tb").show();
		$("#user_tb").hide();
		$("#users_tb").hide();
		$("#theatres_tb").show();
		$("#cinemas_tb").show();
		$("#friends_tb").hide();
		$("#reservations_tb").hide();
		$("#medalje_tb").hide();
		$("#settings_tb").hide();
		$("#fan_zone_tb").hide();
		$("#login_tb").show();
		$("#register_tb").show();
		$("#logout_tb").hide();
	}
	
	
});

function getFormData($form){
	
	 var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	
}


function logout(){
	
	
	
	$.ajax({
		url: "user/logOut",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			if (data == true) {
				alert("Uspjesno ste se odjavili sa sistema.");

				sessionStorage.removeItem("user_type")

				top.location.href = "login.html";
			}else{
				
				
			}
		},
		error: function(jqxhr,textStatus,errorThrown){
			alert(errorThrown);
		}
	});
	
	
}
