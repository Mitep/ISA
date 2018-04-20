window.onload = function() {

	var theCinId = sessionStorage.getItem("fast_reserv_the_cin_id");
	
	$.ajax({
		url: "bioskop/BrzeKarte/"+theCinId,
		type: "GET",
		contentType: "application/json",
		success: function(data){
			console.log(data);
			console.log(sessionStorage.getItem("user_type"));
			//$("#table_div_fast_reservations").empty();
			for(i=0;i<data.length;i++) {
				if(sessionStorage.getItem("user_type") == "USER") {
					$("#table_div_fast_reservations").append("<tr>" +
							"<td>"+data[i].movieName+"</td>" +
							"<td>"+data[i].projectionName+"</td>" +
							"<td>"+millisToTime(data[i].projectionTime)+"</td>" +
							"<td>"+millisToDate(data[i].projectionDate)+"</td>" +
							"<td>"+data[i].seat_row+" "+data[i].seat_col+"</td>" +
							"<td>"+data[i].segmentName+"</td>" +
							"<td>"+data[i].hallName+"</td>" +
							"<td>"+data[i].price+"</td>" +
							"<td>"+data[i].discount+"</td>" +
							"<td><input type='button' onclick='brzaRezervacija("+data[i].id+")' value ='Rezervisi' ></td>" +
							"</tr>");		
				} 
				
			}
		},
		error: function() {
		}
	});
}

function brzaRezervacija(id) {
	//console.log("rezervisi brzu kartu sa idjem " + id);
	var ticketId = id;
	var cdat = JSON.stringify( ticketId );
	
	console.log(cdat);
	$.ajax({
		url: "projekcije/rezervisiBrzuKartu",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data: cdat,
		success: function(data){
	
			top.location.href="brzeRezervacije.html";
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
	//yyyy-MM-dd
	//return date.getDate() + "." + date.getMonth() + "." + date.getFullYear();

	var day = ("0" + date.getDate()).slice(-2);
	var month = ("0" + (date.getMonth() + 1)).slice(-2);

	return date.getFullYear()+"-"+(month)+"-"+(day) ;
}