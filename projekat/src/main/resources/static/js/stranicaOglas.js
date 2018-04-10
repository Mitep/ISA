function dodajOglas(){
	
	var $form = $("#oglasForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "oglas/addOglas",
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste dodali oglas!");
				top.location.href="fanZona.html";
			}else
				alert("Niste dodali oglas!");
			
		}
	
	});
	
}

window.onload = function() {
$.ajax({
		
		url: "oglas/prikaziOglas",
		type: "GET",
		success: function(data){
			$(".prikaz").empty();
			for(i=0;i<data.length;i++) {
				$(".prikaz").append("<tr><td>Naziv: </td><td>" + data[i].nazivOglasa + "</td></tr> "+
						 "<tr><td>Opis: </td><td>" + data[i].opisOglasa + "</td></tr> "+
						 "<tr><td>Slika: </td><td>" + data[i].imageOglasa + "</td></tr> "+
						 "<tr><td>Datum: </td><td>" + data[i].datumOglasa + "</td></tr> "+
						"<tr><td><input type=\"button\" value = \"Ukloni\" onclick = \"ukloniOglas("+data[i].oglasId+")\">" +
								"<input type=\"button\" value = \"Izmijeni\" onclick = \"izmijeniOglas("+data[i].oglasId+")\"></td></tr>"
						);

				
			}
			
		}
	
	});
	
}

function ukloniOglas(oglasId){
	console.log(oglasId)
	$.ajax({
		
		url: "oglas/deleteOglas/" + oglasId,
		type: "GET",
		success: function(data){
			if(data != null){
				alert("Uspjesno ste izbrisali oglas!");
				top.location.href="fanZona.html";
			}else
				alert("Niste izbrisali oglas!");
			
		}
	
	});
	
}