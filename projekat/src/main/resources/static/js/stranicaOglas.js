var idOglasa

function noviOglas(){
	window.location.href="stranicaOglas.html";
	
		}

function dodajOglas(){

	
	var $form = $("#oglasForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	var movieId = sessionStorage.getItem('film');
	console.log(s);
	$.ajax({
		
		url: "oglas/addOglas/" + movieId,
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
$.ajax({
	
	url: "oglas/prikaziOglasDrugih",
	type: "GET",
	success: function(data){
		$(".prikazDrugih").empty();
		for(i=0;i<data.length;i++) {
			$(".prikazDrugih").append("<tr><td>Naziv: </td><td>" + data[i].nazivOglasa + "</td></tr> "+
					 "<tr><td>Opis: </td><td>" + data[i].opisOglasa + "</td></tr> "+
					 "<tr><td>Slika: </td><td>" + data[i].imageOglasa + "</td></tr> "+
					 "<tr><td>Datum: </td><td>" + data[i].datumOglasa + "</td></tr> " +
							"<tr><td><input type=\"button\" value = \"Ponudi\" onclick = \"ponudi("+data[i].oglasId+")\"></td></tr>"
					);

			
		}
		
	}

});

$.ajax({
	
	url: "oglas/prikaziPonude",
	type: "GET",
	success: function(data){
		$(".prikazPonuda").empty();
		for(i=0;i<data.length;i++) {
			$(".prikazPonuda").append("<tr><td>Ponuda za "+data[i].ponudaOglas.nazivOglasa +" oglas: </td><td>" + data[i].ponuda + "</td></tr> "+
					"<tr><td><input type=\"button\" value = \"Ukloni\" onclick = \"ukloniPonudu("+data[i].offerId+")\">" +
							"<input type=\"button\" value = \"Izmijeni\" onclick = \"izmijeniPonudu("+data[i].offerId+")\"></td></tr>"
					);

			
		}
		
	}

});


$.ajax({
	
	url: "oglas/prikaziPonudeDrugih",
	type: "GET",
	success: function(data){
		console.log(data)
		$(".prikazPonudaDrugih").empty();
		for(i=0;i<data.length;i++) {
			$(".prikazPonudaDrugih").append("<tr><td>Ponuda: </td><td>" + data[i].ponuda + "</td></tr> "+
							"<tr><td><input type=\"button\" value = \"Prihvati\" onclick = \"prihvatiPonudu("+data[i].offerId+")\"></td></tr>"
					);

			
		}
		
	}

});



$.ajax({
	url: "movie/prikaziFilmove",
	type:"GET",
	contentType:"application/json",
	dataType:"json",
	success : function(data){
		console.log(data)
		$("#sviFilmovi").empty();
		for(i=0;i<data.length;i++) {
			$("#sviFilmovi").append("<tr>" +
					"<td>" + data[i].type + "</td>" +
					"<td>" + data[i].name + "</td>" +
					"<td>" + data[i].director + "</td>" +
					"<td>" + data[i].genre + "</td>" +
					"<td>" + data[i].length + "</td>" +
					"<td>" + data[i].poster + "</td>" +
					"<td><a href=\"javascript:;\" onclick=\"dodajFilmuOglas('"+data[i].movieId+"')\">Dodaj	</a>" +
					"</td></tr>");
			}
			
	},
	error: function(){
		alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
	}
	});

$.ajax({
	
	url: "oglas/getOglasRequest",
	type: "GET",
	success: function(data){
		console.log(data)
		$(".oglasRequest").empty();
		for(i=0;i<data.length;i++) {
			
			$(".oglasRequest").append("<tr><td>Naziv: </td><td>" + data[i].nazivOglasa + "</td></tr> "+
					 "<tr><td>Opis: </td><td>" + data[i].opisOglasa + "</td></tr> "+
					 "<tr><td>Slika: </td><td>" + data[i].imageOglasa + "</td></tr> "+
					 "<tr><td>Datum: </td><td>" + data[i].datumOglasa + "</td></tr> "+
					 "<tr><td><input type=\"button\" value = \"Prihvati\" onclick = \"prihvatiOglas("+data[i].oglasId+")\">" +
							"<input type=\"button\" value = \"Odbij\" onclick = \"odbijOglas("+data[i].oglasId+")\"></td></tr>"
					);

			
		}
		
	}

});
}

function dodajFilmuOglas(movieId) {
$.ajax({
		
		url: "movie/getFilmOglasa/" + movieId,
		type: "GET",
		contentType:"application/json",
		dataType: "json",
		success: function(data){
			sessionStorage.setItem('film',data.movieId);
		},
		error: function() {
			
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
	


function izmijeniOglas(oglasId) {
				
	sessionStorage.setItem('id',oglasId);
	top.location.href="formaZaIzmjenuOglasa.html";
	

}

function izmijeniOglas2() {
	
	idOglasa = sessionStorage.getItem('id');
	console.log(idOglasa);
	var $form = $("#izmjenaForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	$.ajax({
		
		url: "oglas/izmijeniOglas/"+idOglasa,
		type: "PUT",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste izmijenili oglas!");
				top.location.href="fanZona.html";
			}else
				alert("Niste izmjenili oglas!");
			
		}
	
	});
	
}

function getFilmovi() {
	$.ajax({
		url: "movie/prikaziFilmove",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			console.log(data)
			$("#sviFilmovi").empty();
			for(i=0;i<data.length;i++) {
				$("#sviFilmovi").append("<tr>" +
						"<td>" + data[i].type + "</td>" +
						"<td>" + data[i].name + "</td>" +
						"<td>" + data[i].director + "</td>" +
						"<td>" + data[i].genre + "</td>" +
						"<td>" + data[i].length + "</td>" +
						"<td>" + data[i].poster + "</td>" +
						"<td><a href=\"javascript:;\" onclick=\"dodajFilmuOglas('"+data[i].id+"')\">Dodaj	</a>" +
						"</td></tr>");
				}
				
		},
		error: function(){
			alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
		}
		});
}
	

function prihvatiOglas(oglasId) {
	$.ajax({
			
			url: "oglas/prihvatiOglas/" + oglasId,
			type: "GET",
			contentType:"application/json",
			dataType: "json",
			success: function(data){
				
				alert("Prihvatili ste zahtjev za oglas!");
				top.location.href = "fanZona.html"
			},
			error: function() {
				alert("Doslo je do greske");
			}
		
		});
		
	}


function odbijOglas(oglasId) {
	$.ajax({
			
			url: "oglas/odbijOglas/" + oglasId,
			type: "GET",
			contentType:"application/json",
			dataType: "json",
			success: function(data){
				
				alert("Odbili ste zahtjev za oglas!");
				top.location.href = "fanZona.html"
			},
			error: function() {
				alert("Doslo je do greske");
			}
		
		});
		
	}

function ponudi(oglasId) {
	
	sessionStorage.setItem('idOgl',oglasId);
	top.location.href="formaZaPonudu.html";
}

function ponudi2() {
	
	var idOglas = sessionStorage.getItem('idOgl');
	var $form = $("#ponudaForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "oglas/addPonuda/" + idOglas,
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste dodali ponudu!");
				top.location.href="fanZona.html";
			}else
				alert("Niste dodali ponudu!");
			
		}
	
	});
	
	
}

function prihvatiPonudu(offerId){
	
	$.ajax({
		
		url: "oglas/prihvatiPonudu/" + offerId,
		type: "GET",
		contentType:"application/json",
		dataType: "text",
		success: function(data){
			if(data == "nesto"){
			alert("Prihvatili ste ponudu!");
			top.location.href = "fanZona.html"
			}
			
		},
		error: function() {
			alert("Doslo je do greske");
		}
	
	});
	
}
	
	