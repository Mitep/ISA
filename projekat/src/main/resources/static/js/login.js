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
		//	if(data != null){
				if(data == "fanadmin"){
					sessionStorage.setItem("user_type", data);
					alert("Uspjesno ste se ulogovali admine fan zone!");
					top.location.href="podesavanja.html";	
				}else if(data == "USER") {
				sessionStorage.setItem("user_type", data);
				alert("Uspjesno ste se ulogovali!");
				top.location.href="index.html";
				}else if(data == "SYSADMIN") {
					sessionStorage.setItem("user_type", data);
					alert("Uspjesno ste se ulogovali!");
					top.location.href="index.html";	
				}else if(data == "ADMIN") {
					sessionStorage.setItem("user_type", data);
					alert("Uspjesno ste se ulogovali!");
					top.location.href="index.html";	
				}else if(data == "FANADMIN") {
					sessionStorage.setItem("user_type", data);
					alert("Uspjesno ste se ulogovali!");
					top.location.href="index.html";	
				}
				else if("nista"){
					
					alert("Niste se ulogovali!");
					
				}else{
					alert("Niste se ulogovali!");
				}
			//	}
			//}else
			//	alert("Niste se ulogovali!");
			
		},
		error: function(data){
			alert(data)
		}
		
	
	});
}