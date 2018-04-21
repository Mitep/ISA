window.onload = function() {
	
	var cin_id = sessionStorage.getItem('edit_cinema_id');
	$.ajax({
		url: "bioskop/"+cin_id,
		type:"GET",
		contentType:"application/json",
		success : function(data){
			$("#input_name").val(data.name);
			$("#input_adress").val(data.adress);
			$("#input_description").val(data.description);
			$("#edit_cinema").val(data.id);
			$("#edit_basic_info_button_click").val(cin_id);
			$("#add_new_hall_button").val(cin_id);
			
			for(var i = 0; i < data.halls.length; i++){
				$("#sale").append("<div id="+data.halls[i].id+" class='sala'>ID sale:"+data.halls[i].id+"<br>" +
						"Naziv sale: "+data.halls[i].name+
				"<form accept-charset='UTF-8' >"+
			   	   "<input placeholder='Novi naziv sale' type='text' class='edit_hall_name_value' >"+
				   "<button class='edit_hall_name_button' value="+data.halls[i].id+"> Izmeni ime sale</button></form>" +
				   "<button class='delete_hall_button' value="+data.halls[i].id+"> Obrisi salu</button><br>" +
				   "Segmenti<br>" +
				   "<input placeholder='Novi naziv segmenta' type='text' class='new_segment_name' >" +
				   "<button class='add_new_segment' value="+data.halls[i].id+"> Dodaj novi segment</button><br><hr>");		
				if(data.halls[i].segments != null){
					var segments = data.halls[i].segments;
					for(var j = 0; j < segments.length; j++){
						$("#"+data.halls[i].id+".sala").append("Id segmenta: "+segments[j].id+"<br>Naziv segmenta: "+segments[j].name);	
						$("#"+data.halls[i].id+".sala").append("<form accept-charset='UTF-8' >"+
			   	   "<input placeholder='Novo ime segmenta' type='text' >"+
				   "<button class='edit_segment_name_button' value="+segments[j].id+" >Izmeni ime segmenta</button></form>" +
				   "<button class='delete_segment_button' value="+segments[j].id+" > Obrisi segment</button>");
						$("#"+data.halls[i].id+".sala").append("<br>Sedista<br>" +
								"Dodaj sediste na poziciju: " +
								"<input placeholder='red' type='text' >" +
								"<input placeholder='kolona' type='text' >" +
								"<button class='add_seat_button' value="+data.halls[i].id+" > Dodaj sediste</button><br><br>" +
						"<table id="+segments[j].id+" class='sedista_tabela'></table><br>" +
								"<button class='edit_seats_button' value="+segments[j].id+" >Sacuvaj izmene sedista</button><hr>");
						//prvo pravimo praznu tabelu sa mxn kolona i redova pa posle dodajemo sedisa u tu tabelu
						for(var m = 0; m < segments[j].rowNum; m++){
							$("#"+segments[j].id+".sedista_tabela").append("<tr class="+(m+1)+">");
							for(var n = 0; n < segments[j].colNum; n++){
								var tooltip = String((m+1) + " red, " + (n+1) + " kolona");
								
								//ajax poziv koji stavlja prazno,sediste,ili rezervisano
								//ako je rezervisano onda ga disablujemo
								var row = (m+1);
								var col = (n+1);
								var rdata = {row, col};
								s = JSON.stringify(rdata);
								
								var button_state;
								$.ajax({
									url: "hall/seatState",
									type:"POST",
									contentType:"application/json",
									dataType:"text",
									data: s,
									success : function(data){
										console.log(data);
										button_state = data;
									},	
								});
								

								$("#"+segments[j].id+".sedista_tabela").append("<td class="+(n+1)+">"+
										"<button class='edit_one_seat_button' title=' "+tooltip+" '>prazno</button></td>");
								
							
							}
							$("#"+segments[j].id+".sedista_tabela").append("</tr>");
						}
						if(segments[j].rowNum  == 0){
							$("#"+segments[j].id+".sedista_tabela").append("Segment bez sedista. <br>");
						}
						
					}		
				}
				$("div.sala").append("</div>");
				$("#sale").append("<br>");
			}	
		},
		error: function(){
		}
		});
}

$(document).ready(function(){
		
	$("#edit_basic_info_button_click").click(function( event ){			
		
		
//		console.log("edituj osnovne informacije");
//		console.log($('#input_name').val());
//		console.log($('#input_adress').val());
//		console.log($('#input_description').val());

		console.log($(this).val());
		
		var id = $(this).val();
		var name = $('#input_name').val();
		var adress = $('#input_adress').val();
		var description = $('#input_description').val();
		
		var a = { id, name, adress, description };
			
		var dto = JSON.stringify( a );
		
		$.ajax({
			url: "bioskop/izmeniOsnovneInformacije",
			type: "POST",
			contentType:"application/json",
			dataType:"application/json",
			data : dto,
			success: function(data){
				top.location.href="formaZaIzmjenuBioskopa.html";
			},
			error: function() {
			}
		});
		//top.location.href="formaZaIzmjenuBiosokpa.html";
		
	});

	$("#add_new_hall_button").click(function( event ){			
//		console.log("dodaj novu salu");
//		console.log();
//		console.log($(this).val());

		var theatreCinemaId = $(this).val();
		var hallName = $('#add_new_hall_name').val();
		
		var a = { hallName, theatreCinemaId };
		var dto = JSON.stringify( a );
		
		$.ajax({
			url: "/hall/dodaj",
			type: "POST",
			contentType:"application/json",
			dataType:"application/json",
			data : dto,
			success: function(data){
			    //location.reload();
				top.location.href="formaZaIzmjenuBioskopa.html";
			},
			error: function() {
			}
		});
	});
});

$(document).on("click", "button.edit_hall_name_button", function( event ){
//	event.preventDefault();
//	console.log("dodavj novi segmen sa id");
//	console.log(this.value);
//	console.log("vrednost");
//	console.log($(this).prev().val());
	var theatreCinemaId = this.value;
	var hallName = $(this).prev().val();
	var a = { hallName, theatreCinemaId };
	var dto = JSON.stringify( a );
	$.ajax({
		url: "/hall/izmeni",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data : dto,
		success: function(data){
		    //location.reload();
			top.location.href="formaZaIzmjenuBioskopa.html";
		},
		error: function() {
		}
	});
	
});

$(document).on("click", "button.delete_hall_button", function(){
	
	//console.log("doso");
	//console.log(this.value);
	
	var hallId = this.value;
	var cdat = JSON.stringify( hallId );
	$.ajax({
		url: "hall/ukloni",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data: cdat,
		success: function(data){
	
			top.location.href="formaZaIzmjenuBioskopa.html";
		},
		error: function() {
		}
	});
	
	
	
});

$(document).on("click", "button.edit_segment_name_button", function( event ){
	 
	var segmentId = this.value;
	var segmentName = $(this).prev().val();
	
	var a = { segmentName, segmentId };
	var dto = JSON.stringify( a );
	
	$.ajax({
		url: "/segment/izmeni",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data : dto,
		success: function(data){
		    //location.reload();
			top.location.href="formaZaIzmjenuBioskopa.html";
		},
		error: function() {
		}
	});
	
	
	
});

$(document).on("click", "button.delete_segment_button", function(){
	
	var segmentId = this.value;
	
	var a = { segmentId };
	var dto = JSON.stringify( a );
	
	$.ajax({
		url: "/segment/obrisi",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data : dto,
		success: function(data){
		    //location.reload();
			top.location.href="formaZaIzmjenuBioskopa.html";
		},
		error: function() {
		}
	});
	
	
});

$(document).on("click", "button.add_seat_button", function( event ){
	 
	//event.preventDefault();

	console.log("id segmenta");
	console.log(this.value);
	console.log("red i kolona");
	console.log($(this).prev().val() + $(this).prev().prev().val());
//	
//	$.ajax({
//		url: "/segment/obrisi",
//		type: "POST",
//		contentType:"application/json",
//		dataType:"application/json",
//		data : dto,
//		success: function(data){
//		    //location.reload();
//			top.location.href="formaZaIzmjenuBioskopa.html";
//		},
//		error: function() {
//		}
//	});
	
});

$(document).on("click", "button.edit_seats_button", function(){
	
	
	
	
	
	console.log("id segmenta");
	console.log(this.value);
	
});

$(document).on("click", "button.edit_one_seat_button", function(){
	if($(this).html() == "prazno"){
		$(this).empty().append("sediste");
	} else {
		$(this).empty().append("prazno");
	}
});

$(document).on("click", "button.add_new_segment", function(  ){
	//event.preventDefault();
	console.log("dodavj novi segmen sa id");
	console.log(this.value);
	console.log("vrednost");
	console.log($(this).prev().val());

	var hallId = this.value;
	var segmentName = $(this).prev().val();
	
	var a = { segmentName, hallId };
	var dto = JSON.stringify( a );
	
	console.log(dto);
	$.ajax({
		url: "/segment/dodaj",
		type: "POST",
		contentType:"application/json",
		dataType:"application/json",
		data : dto,
		success: function(data){
		    //location.reload();
			top.location.href="formaZaIzmjenuBioskopa.html";
		},
		error: function() {
		}
	});

	
});
