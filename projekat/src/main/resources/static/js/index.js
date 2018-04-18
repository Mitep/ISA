$(document).ready(function(){

	$("#home_div > div.pozoriste").click(function(){
		 //prikazuje pozorista
		top.location.href="theatre.html";
	});
	
	$("#home_div > div.bioskop").click(function(){
		//prikazuje bioskope		
		top.location.href="cinema.html";

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