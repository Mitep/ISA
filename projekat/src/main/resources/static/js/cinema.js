window.onload = function() {

		$.ajax({
				url: "bioskop/sviBioskopi",
				type: "GET",
				success: function(data){
					console.log(data);
					console.log(sessionStorage.getItem("user_type"));
					$(".prikaz").empty();
					for(i=0;i<data.length;i++) {
						if(sessionStorage.getItem("user_type") == "ADMIN") {
							$(".prikaz").append("<tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
									 "<tr><td>Adresa: </td><td>" + data[i].adress + "</td></tr> "+
									 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> " +
									 "<tr><td>Prosecna ocena: </td><td>" + data[i].rating + "</td></tr> " +
									 "<tr><td>"+
									 "<input type=\"button\" value = \"Izmijeni\" onclick = \"izmijeniBioskop("+data[i].id+")\">"+
									 "<input type=\"button\" onclick = \"repertoar("+data[i].id+")\" value = \"Pogledaj\"></td></tr>"
									);
						} else if(sessionStorage.getItem("user_type") == "SYSADMIN"){
							$(".prikaz").append("<tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
									 "<tr><td>Adresa: </td><td>" + data[i].adress + "</td></tr> "+
									 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> " +
									 "<tr><td>Prosecna ocena: </td><td>" + data[i].rating + "</td></tr> " +
									 "<tr><td><input type=\"button\" value = \"Ukloni\" onclick = \"ukloniBioskop("+data[i].id+")\">" +
									 "<input type=\"button\" onclick = \"repertoar("+data[i].id+")\" value = \"Pogledaj\"></td></tr>"
									);
						} else {
							$(".prikaz").append("<tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
									 "<tr><td>Adresa: </td><td>" + data[i].adress + "</td></tr> "+
									 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> " +
									 "<tr><td>Prosecna ocena: </td><td>" + data[i].rating + "</td></tr> " +
									 "<tr><td>" +
									 "<input type=\"button\" onclick = \"repertoar("+data[i].id+")\" value = \"Pogledaj\"></td></tr>");
						}	
					}
					
				},
				error: function() {
					alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
				}
			});

	$("#dodaj_bioskop_div_sysadmin").hide();
	if(sessionStorage.getItem("user_type") == "SYSADMIN") {
		
		$.ajax({
			url: "user/getAdmine",
			type:"GET",
			contentType:"application/json",
			dataType:"json",
			success : function(data){
				$("#adminiKorisnici").empty();
				for(i=0;i<data.length;i++) {
					$("#adminiKorisnici").append("<tr>" +
							"<td>" + data[i].userName + "</td>" +
							"<td>" + data[i].userSurname + "</td>" +
							"<td>" + data[i].city + "</td>" +
							"<td>" + data[i].mobileNumber + "</td>" +
							"<td>" + data[i].email + "</td>" +
							"<td>" + data[i].userRole + "</td>" +
							"<td><a href=\"javascript:;\" onclick=\"dodajAdmina("+data[i].userId+")\">Dodaj	</a>" +
							"</td></tr>");
					}
					
			},
			error: function(){
				alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
			}
			});
	}
}
	
	
	



function getFormData($form){
	
	 var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	
}

function dodajAdmina(userId){
	
	$.ajax({
		url: "user/getAdminiBioskopa/" + userId ,
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			sessionStorage.setItem('admin',data.userId);
		},
		error: function(){
		}
		});
	
	
}

function dodajBioskop() {
	var $form = $("#bioskopForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	var userId = sessionStorage.getItem('admin');
	console.log(userId);
	$.ajax({
		
		url: "bioskop/addBioskop/"+userId,
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste dodali bioskop!");
				top.location.href="cinema.html";
			}else
				alert("Samo administrator sistema moze da dodaje bioskope!");
			
		},
		error: function() {
			alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
		}
	
	});
	
}

function repertoar(cinemaId){
	
	$.ajax({
		
		url: "bioskop/prikaziBioskope/"+cinemaId,
		type: "GET",
		success: function(data){
			$(".prikaz").empty();
			$("#adminiKorisnici").empty();
				if(data.type == "CINEMA") {
				$(".prikaz").append("<tr><td>Naziv: </td><td>" + data.name + "</td></tr> "+
						 "<tr><td>Adresa: </td><td>" + data.adress + "</td></tr> "+
						 "<tr><td>Opis: </td><td>" + data.description + "</td></tr> " +
						 "<tr><td>" +
							"<input type=\"button\" onclick=\"nazadBioskop()\" value = \"Nazad\"></td></tr>");
				}
		},
		error:function(data){
			alert(data)
		}
	});

}

function nazadBioskop(){
	
	top.location.href="cinema.html";
}

function ukloniBioskop(id){
	console.log(id)
	$.ajax({
		
		url: "bioskop/deleteBioskop/" + id,
		type: "GET",
		success: function(data){
			if(data){
				alert("Uspjesno ste uklonili bioskop!");
				top.location.href="cinema.html";
				
			}else
				alert("Samo administrator sistema moze da ukloni bioskop!");
			
		}
	
	});

}

function izmijeniBioskop(id) {
	
	sessionStorage.setItem('edit_cinema_id',id);
	top.location.href="formaZaIzmjenuBioskopa.html";

}

function izmijeniBioskop2() {
	
	id = sessionStorage.getItem('Cid');
	var $form = $("#izmjenaBioskopaForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	$.ajax({
		
		url: "bioskop/izmijeniBioskop/"+id,
		type: "PUT",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste izmijenili bioskop!");
				top.location.href="cinema.html";
			}else
				alert("Samo administrator sistema moze da izmijeni bioskop!");
			
		}
	
	});
	
}