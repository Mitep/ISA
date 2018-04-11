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


function formaZaIzmjenu(){
	
	window.location.href = "formaZaIzmjenu.html"
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

function searchUsers(){
	

	var userName = $("#name").val();
	var userSurname = $("#surname").val();

	if(userName == ""){
		userName = "nista";
	}
	if(userSurname == ""){
		userSurname = "nista";
	}
	
	$.ajax({	
	url: "user/searchUsers/"+userName+"/"+userSurname,
	type: "GET",
	success: function(data){
		 $(".users").empty();
		 for(i=0;i<data.length;i++){
				 $(".users").append(
						 "<tr><td>Ime:</td><td>" + data[i].userName + "</td></tr>"+
						 "<tr><td>Prezime:</td><td>" + data[i].userSurname + "</td></tr>"+
						 "<tr><td>Email:</td><td>" + data[i].email + "</td></tr>"+
						 "<tr><td>Grad:</td><td>" + data[i].city + "</td></tr>"+
						 "<tr><td>Broj telefona:</td><td>" + data[i].mobileNumber + "</td></tr>+" +
						 "<tr><td><input type=\"button\" value = \"Dodaj prijatelja\" onclick = \"dodajPrijatelja("+data[i].userId+")\"></td><tr>");
	
		 	}
	 	}
	});
}

function dodajPrijatelja(userId){
	
	$.ajax({	
		url: "user/dodajPrijatelja/"+userId,
		type: "GET",
		success: function(data){
			alert("Uspjesno ste poslali zahtjev");
		}
	
		});
	
}





