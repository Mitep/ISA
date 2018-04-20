window.onload = function() {
	var cin_id = sessionStorage.getItem('edit_cinema_projections');

	$.ajax({
		url: "projekcije/sve/" + cin_id,
		type: "GET",
		contentType:"application/json",
		success: function(data){
			console.log(data);
			$("#main_div").empty();
			for(i=0;i<data.length;i++) {
				if(sessionStorage.getItem("user_type") == "ADMIN"){
					$("#main_div").append("<div class='projekcija' ><table><tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
							 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> "+
							 "<tr><td>Datum: </td><td>" + millisToDate( data[i].projectionDateTime ) + "</td></tr> " +
							 "<tr><td>Vreme: </td><td>" + millisToTime( data[i].projectionDateTime ) + "</td></tr>" +
							 "<tr><td>Film: </td><td>" + data[i].moviePerformanceName + "</td></tr>" +
							 "<tr><td>Prosecna ocena filma: </td><td>" + data[i].movieRating + "</td></tr> " +
							 "<tr><td>Sala: </td><td>" + data[i].hallName + "</td></tr>" +
							 "<tr><td>Bioskop: </td><td>" + data[i].theatreCinemaName + "</td></tr>" +
							 "<tr><td>"+
							 "<input type=\"button\" value = \"Izmeni\" onclick = \"izmeniProjekciju("+data[i].id+")\">" +
							 "<input type=\"button\" value = \"Obrisi\" onclick = \"obrisiProjekciju("+data[i].id+")\">" +
							 "</table></div><br>"
							);
				} else {
					$("#main_div").append("<div class='projekcija' ><table><tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
							 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> "+
							 "<tr><td>Datum: </td><td>" + millisToDate( data[i].projectionDateTime ) + "</td></tr> " +
							 "<tr><td>Vreme: </td><td>" + millisToTime( data[i].projectionDateTime ) + "</td></tr>" +
							 "<tr><td>Film: </td><td>" + data[i].moviePerformanceName + "</td></tr>" +
							 "<tr><td>Prosecna ocena filma: </td><td>" + data[i].movieRating + "</td></tr> " +
							 "<tr><td>Sala: </td><td>" + data[i].hallName + "</td></tr>" +
							 "<tr><td>Bioskop: </td><td>" + data[i].theatreCinemaName + "</td></tr>" +
							 "<tr><td>"+
							 "<input type=\"button\" value = \"Rezervacija\" onclick = \"rezervisi("+data[i].id+")\">" +
							 "<input type=\"button\" value = \"Brza rezervacija\" onclick = \"rezervisiBrzu("+data[i].id+")\">" +
							 "</table></div><br>"
							);
				}
					
			}
		},
		error: function() {
		}
	});
	
}

function millisToTime(millis){
	var time = new Date(millis);
	
	return time.getHours() + ":" + time.getMinutes() ;
}

function millisToDate(millis){
	var date = new Date(millis);
	
	return date.getDate() + "." + date.getMonth() + "." + date.getFullYear();
}

function obrisiProjekciju(id) {
	console.log("obrisi " + id);
	var projId = id;
	var cdat = JSON.stringify( projId );
	$.ajax({
		url: "projekcije/ukloni",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data: cdat,
		success: function(data){
			
		},
		error: function() {
		}
	});
	
	top.location.href="projekcije.html";

	
}

function izmeniProjekciju(id) {
	//forma za izmenu projekcije
	sessionStorage.setItem('create_edit_projection_form_type','edit');
	sessionStorage.setItem('edit_projection_id', id);
	top.location.href="createEditProjection.html";
	
}

$(document).ready(function(){
	$("#new_projection_button").click(function(){
		//redirekt na formu za kreiranje nove projekcije
		sessionStorage.setItem('create_edit_projection_form_type','create');	
		top.location.href="createEditProjection.html";
	});
});	
	

