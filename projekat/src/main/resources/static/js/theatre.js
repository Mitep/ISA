window.onload = function() {
	$.ajax({
			
			url: "pozoriste/prikaziPozoriste",
			type: "GET",
			success: function(data){
				
				$(".prikaz").empty();
				for(i=0;i<data.length;i++) {
					console.log(data[i].id);
					if(data[i].type == "THEATRE") {
					$(".prikaz").append("<tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
							 "<tr><td>Adresa: </td><td>" + data[i].adress + "</td></tr> "+
							 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> " +
							 "<tr><td><input type=\"button\" value = \"Ukloni\" onclick = \"ukloniPozoriste("+data[i].id+")\">" +
								"<input type=\"button\" value = \"Izmijeni\" onclick = \"izmijeniPozoriste("+data[i].id+")\">"+
								"<input type=\"button\" onclick = \"repertoar("+data[i].id+")\" value = \"Repertoar\"></td></tr>"
							);
					}
					
				}
	}
		
		});
	
	
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
		url: "user/getAdminiPozorista/" + userId ,
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

function dodajPozoriste() {
	var $form = $("#pozoristeForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	var userId = sessionStorage.getItem('admin');
	console.log(userId);
	$.ajax({
		
		url: "pozoriste/addPozoriste/"+userId,
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste dodali pozoriste!");
				top.location.href="theatre.html";
			}else
				alert("Samo administrator sistema moze da dodaje pozorista!");
	},
	error: function() {
		alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
	}
	
	});
	
}

function repertoar(theatreId){
	
	$.ajax({
		
		url: "pozoriste/prikaziPozoriste/"+theatreId,
		type: "GET",
		success: function(data){
			$(".prikaz").empty();
			$("#adminiKorisnici").empty();
				if(data.type == "THEATRE") {
				$(".prikaz").append("<tr><td>Naziv: </td><td>" + data.name + "</td></tr> "+
						 "<tr><td>Adresa: </td><td>" + data.adress + "</td></tr> "+
						 "<tr><td>Opis: </td><td>" + data.description + "</td></tr> " +
						 "<tr><td><input type=\"button\" value = \"Ukloni\">" +
							"<input type=\"button\" value = \"Izmijeni\">" +
							"<input type=\"button\" onclick=\"nazad()\" value = \"Nazad\"></td></tr>");
				}
		},
		error:function(data){
			alert(data)
		}
	});

}

function nazad(){
	
	top.location.href="theatre.html";
}

function ukloniPozoriste(id){
	console.log(id)
	$.ajax({
		
		url: "pozoriste/deletePozoriste/" + id,
		type: "GET",
		success: function(data){
			if(data){
				alert("Uspjesno ste uklonili pozoriste!");
				top.location.href="theatre.html";
				
			}else
				alert("Samo administrator sistema moze da ukloni pozoriste!");
			
		}
	
	});

}

function izmijeniPozoriste(id) {
	
	sessionStorage.setItem('Tid',id);
	top.location.href="formaZaIzmjenuPozorista.html";
	

}

function izmijeniPozoriste2() {
	
	id = sessionStorage.getItem('Tid');
	var $form = $("#izmjenaPozoristaForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	$.ajax({
		
		url: "pozoriste/izmijeniPozoriste/"+id,
		type: "PUT",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste izmijenili pozoriste!");
				top.location.href="theatre.html";
			}else
				alert("Samo administrator sistema moze da izmijeni pozoriste!");
			
		}
	
	});
	
}