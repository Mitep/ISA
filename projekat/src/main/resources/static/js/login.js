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
		dataType: "text",
		success: function(data){
			console.log(data)
			if(data == "admin"){
				
				alert("Uspjesno ste se ulogovali sistemski admine!");
				top.location.href="podesavanja.html";
				
			}else if(data =="logovao"){
				
				alert("Uspjesno ste se ulogovali!");
				top.location.href="homePage.html";
				
			}else
				alert("Niste se ulogovali!");
			
		},
		error: function(data){
			alert(data)
		}
		
	
	});
}