window.onload = function() {
	$.ajax({
			
			url: "bioskop/prikaziBioskopZaHome",
			type: "GET",
			success: function(data){
				$("#bioskop").empty();
				for(i = 0; i < data.length;i++){
					if(data[i].type == "CINEMA") {
				    $('#bioskop').append($('<option>', {
				    	id : data[i].id,
				        value: data[i].name,
				        text : data[i].name 
				    }));
					}
				};

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("AJAX ERROR: " + errorThrown);
			}

	});
	
	$.ajax({
		url: "user/getFriends",
		 method: "GET",
		 success: function(data){
			 $(".friends").empty();
			 $(".friendsPozoriste").empty();
			 for(i=0;i<data.length;i++){
				 $(".friends").append("<tr><td>" + data[i].userName + "</td><td>" + data[i].userSurname + "</td><td>" + data[i].email + "</td><td>" + data[i].city + "</td><td>" + data[i].mobileNumber + "</td><td><input type='checkbox' class = \"checkboxFriends\"  title=' "+data[i].userId+" ' ></td></tr>");
				 $(".friendsPozoriste").append("<tr><td>" + data[i].userName + "</td><td>" + data[i].userSurname + "</td><td>" + data[i].email + "</td><td>" + data[i].city + "</td><td>" + data[i].mobileNumber + "</td><td><input type='checkbox' class = \"checkboxFriendsPozoriste\"  title=' "+data[i].userId+" ' ></td></tr>");
			 }
		 },
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
	
	
	$.ajax({
		
		url: "pozoriste/prikaziPozoristeZaHome",
		type: "GET",
		success: function(data){
			$("#pozoriste").empty();
			for(i = 0; i < data.length;i++){
				if(data[i].type == "THEATRE") {
			    $('#pozoriste').append($('<option>', { 
			        id : data[i].id,
			    	value: data[i].name,
			        text : data[i].name 
			    }));
				}
			};

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}

function daljeBioskop(){
	
	var conceptName = $('#bioskop').find(":selected").attr('id');
	$.ajax({
		
		url: "bioskop/prikaziProjekcijuBioskopa/"+conceptName,
		type: "GET",
		success: function(data){
			
			$("#daljeBioskop").empty();
			$("#bioskopProjekcija").empty();
			$("#daljeSala").empty();
			$("#zauzetaMjesta").empty();
			for(i = 0; i < data.length;i++){
				$('#bioskopProjekcija').append($('<option>', { 
			        id : data[i].id,
			    	value: data[i].name,
			        text : data[i].name 
			    }));
				
			
				
				
			};
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}

function daljeProjekcijaBioskop(){
	
	
	var conceptName = $('#bioskop').find(":selected").attr('id');
	var conceptNameDrugi = $('#bioskopProjekcija').find(":selected").index();
	var idBioskopa = $('#bioskopProjekcija').find(":selected").attr("id");
	sessionStorage.setItem("concept",idBioskopa);
	
	
	
$.ajax({
		
		url: "bioskop/prikaziProjekcijuBioskopa/"+conceptName,
		type: "GET",
		success: function(data){
			console.log(data)
			$("#daljeProjekcijaBioskop").empty();
			$("#daljeSala").empty();
			$("#datumBioskopProjekcija").empty();
			$("#sale").empty();
			$("#zauzetaMjesta").append("<input type=\"button\" onclick=\"zauzetaMjesta()\" value=\"Zauzmi\">");
			console.log(conceptNameDrugi)
			
			var d = new Date(data[conceptNameDrugi].projectionDateTime);
			$("#datumBioskopProjekcija").append(d.toUTCString())
			$("#sale").append(data[conceptNameDrugi].hall.name);
			
		
			if(data[conceptNameDrugi].hall.segments != null){
					var segments = data[conceptNameDrugi].hall.segments;
					for(var j = 0; j < segments.length; j++){
						$("#segments").append("Id segmenta: "+segments[j].id+"<br>Naziv segmenta: "+segments[j].name);
						
						$("#segments").append("<br>Sedista<br>" +
						"<table id="+segments[j].id+" class='sedista_tabela'></table><hr>");
						
						//prvo pravimo praznu tabelu sa mxn kolona i redova pa posle dodajemo sedisa u tu tabelu
						for(var m = 0; m < segments[j].rowNum; m++){
							$("#"+segments[j].id+".sedista_tabela").append("<tr class="+(m+1)+">");
							for(var n = 0; n < segments[j].colNum; n++){
								var tooltip = String((m+1) + "," + (n+1));
								console.log(tooltip);
								$("#"+segments[j].id+".sedista_tabela").append("<td class="+(n+1)+">"+
								"<input type='checkbox' class = \"checkbox\"  title=' "+tooltip+" ' ></td>");
							
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
		
		
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


function zauzetaMjesta(){
	
	var inputElements = document.getElementsByClassName('checkbox');
	console.log(inputElements)
	var nesto= ""
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked){
	           checkedValue = inputElements[i].title;
	           nesto = nesto + checkedValue;
	      }
	}

$.ajax({
		
		url: "bioskop/postaviZauzetaSjedista/"+nesto+"/"+sessionStorage.getItem("concept"),
		type: "GET",
		success: function(data){
			$("#zauzetaMjesta").empty();
			$("#daljeSala").append("<input type=\"button\" onclick=\"daljeSala()\" value=\"Dalje\">");
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


function daljeSala(friendId){
	
	var inputElements = document.getElementsByClassName('checkboxFriends');
	console.log(inputElements)
	var nesto1= ""
	if(inputElements.length != 0){
		for(var i=0; inputElements[i]; ++i){
		      if(inputElements[i].checked){
		           checkedValue = inputElements[i].title;
		           nesto1 = nesto1 + checkedValue;
		      }
			}
	}else{
		nesto1="nesto";
	}
	
$.ajax({
		
		url: "bioskop/zauzmiSjediste/"+nesto1+"/"+sessionStorage.getItem("concept"),
		type: "GET",
		success: function(data){
			alert("Uspjesno ste zauzeli sjedista!");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


function daljePozoriste(){
	
	var conceptName = $('#pozoriste').find(":selected").attr('id');
	$.ajax({
		
		url: "pozoriste/prikaziProjekcijuPozorista/"+conceptName,
		type: "GET",
		success: function(data){
			$("#daljePozoriste").empty();
			$("#pozoristeProjekcija").empty();
			for(i = 0; i < data.length;i++){
				console.log(data[i])
			
			    $('#pozoristeProjekcija').append($('<option>', { 
			        id : data[i].id,
			    	value: data[i].name,
			        text : data[i].name 
			    }));
		
			};

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


function daljeProjekcijaPozoriste(){
	
	
	var conceptName = $('#pozoriste').find(":selected").attr('id');
	var conceptNameDrugi = $('#pozoristeProjekcija').find(":selected").index();
	var idPozorista =  $('#pozoristeProjekcija').find(":selected").attr("id");
	sessionStorage.setItem("concept1",idPozorista);
	
	
$.ajax({
		
		url: "pozoriste/prikaziProjekcijuPozorista/"+conceptName,
		type: "GET",
		success: function(data){
			console.log(data)
			$("#daljeProjekcijaPozoriste").empty();
			$("#daljeSalaPozoriste").empty();
			$("#datumPozoristeProjekcija").empty();
			$("#salePozoriste").empty();
			$("#zauzetaMjestaPozoriste").append("<input type=\"button\" onclick=\"zauzetaMjestaPozoriste()\" value=\"Zauzmi\">");
			var d = new Date(data[conceptNameDrugi].projectionDateTime);
			$("#datumPozoristeProjekcija").append(d.toUTCString())
			$("#salePozoriste").append(data[conceptNameDrugi].hall.name);
			
		
			if(data[conceptNameDrugi].hall.segments != null){
					var segments = data[conceptNameDrugi].hall.segments;
					for(var j = 0; j < segments.length; j++){
						$("#segmentsPozoriste").append("Id segmenta: "+segments[j].id+"<br>Naziv segmenta: "+segments[j].name);
						
						$("#segmentsPozoriste").append("<br>Sedista<br>" +
						"<table id="+segments[j].id+" class='sedista_tabelaPozoriste'></table><hr>");
						
						//prvo pravimo praznu tabelu sa mxn kolona i redova pa posle dodajemo sedisa u tu tabelu
						for(var m = 0; m < segments[j].rowNum; m++){
							$("#"+segments[j].id+".sedista_tabelaPozoriste").append("<tr class="+(m+1)+">");
							for(var n = 0; n < segments[j].colNum; n++){
								var tooltip = String((m+1) + "," + (n+1));
								console.log(tooltip);
								$("#"+segments[j].id+".sedista_tabelaPozoriste").append("<td class="+(n+1)+">"+
								"<input type='checkbox' class = \"checkboxPozoriste\"  title=' "+tooltip+" ' ></td>");
							
							}
							$("#"+segments[j].id+".sedista_tabelaPozoriste").append("</tr>");
						}
						
						if(segments[j].rowNum  == 0){
							$("#"+segments[j].id+".sedista_tabelaPozoriste").append("Segment bez sedista. <br>");
						}

					}		
				}
				$("div.sala").append("</div>");
				$("#salePozoriste").append("<br>");
		
		
			
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


function zauzetaMjestaPozoriste(){
	
	var inputElements = document.getElementsByClassName('checkboxPozoriste');
	console.log(inputElements)
	var nesto= ""
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked){
	           checkedValue = inputElements[i].title;
	           nesto = nesto + checkedValue;
	      }
	}

$.ajax({
		
		url: "pozoriste/postaviZauzetaSjedistaPozoriste/"+nesto+"/"+sessionStorage.getItem("concept1"),
		type: "GET",
		success: function(data){
			$("#zauzetaMjestaPozoriste").empty();
			$("#daljeSalaPozoriste").append("<input type=\"button\" onclick=\"daljeSalaPozoriste()\" value=\"Dalje\">");
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}


function daljeSalaPozoriste(friendId){
	
	var inputElements = document.getElementsByClassName('checkboxFriendsPozoriste');
	console.log(inputElements)
	var nesto1= ""
	if(inputElements.length != 0){
		for(var i=0; inputElements[i]; ++i){
		      if(inputElements[i].checked){
		           checkedValue = inputElements[i].title;
		           nesto1 = nesto1 + checkedValue;
		      }
			}
	}else{
		nesto1="nesto";
	}
	
$.ajax({
		
		url: "pozoriste/zauzmiSjedistePozoriste/"+nesto1+"/"+sessionStorage.getItem("concept1"),
		type: "GET",
		success: function(data){
			alert("Uspjesno ste zauzeli sjedista!");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}




