
window.onload = function(){
	
	$.ajax({
		url: "user/getUser" ,
		type:"GET",
		
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			if (data!= null) {
				renderUser(data);
			}else{	
			}
		},
		error: function(jqxhr,textStatus,errorThrown){
			alert(errorThrown);
		}
	});
	

}


	

function renderUser(user){
	
	$("#userName").empty();
	$("#userName").append("Korisnik : " + user.userName);
	
	$("#email").empty();
	$("#email").append(user.email);
	
	$("#userPassword").empty();
	$("#userPassword").append(user.userPassword);
	
	$("#userPasswordConf").empty();
	$("#userPasswordConf").append(user.userPasswordConf);
	
	$("#userIme").empty();
	$("#userIme").append(user.userName);
	
	$("#userSurname").empty();
	$("#userSurname").append(user.userSurname);
	

	$("#city").empty();
	$("#city").append(user.city);
	
	
	$("#mobileNumber").empty();
	$("#mobileNumber").append(user.mobileNumber);
		
}
