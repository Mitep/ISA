function logIn(){
	
	var $form = $("#loginForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "user/loginUser",
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste se ulogovali!");
				top.location.href="index.html";
			}else
				alert("Niste se ulogovali!");
			
		}
	
	});
}