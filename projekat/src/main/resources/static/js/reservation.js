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
		
		url: "pozoriste/prikaziPozoristeZaHome",
		type: "GET",
		success: function(data){
			$("#pozorista").empty();
			for(i = 0; i < data.length;i++){
				if(data[i].type == "THEATRE") {
			    $('#pozorista').append($('<option>', { 
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
	var conceptNameDrugi = $('#bioskopProjekcija').find(":selected").attr('id');
	var cn = conceptNameDrugi-1;
	
$.ajax({
		
		url: "bioskop/prikaziProjekcijuBioskopa/"+conceptName,
		type: "GET",
		success: function(data){
			console.log(data)
			$("#datumBioskopProjekcija").empty();
			$("#sale").empty();
			var d = new Date(data[conceptNameDrugi-1].projectionDateTime);
			$("#datumBioskopProjekcija").append(d.toUTCString())
			$("#sale").append(data[conceptNameDrugi-1].hall.name);
			
		
			if(data[cn].hall.segments != null){
					var segments = data[cn].hall.segments;
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

function daljePozoriste(){
	
	var conceptName = $('#pozorista').find(":selected").attr('id');
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
function daljeSala(){
/*
	var inputElements = document.getElementsByClassName('checkbox');
	console.log(inputElements)
	var nesto= ""
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked){
	           checkedValue = inputElements[i].title;
	           nesto = nesto + checkedValue;
	      }
	}
	console.log(nesto)
	
	var pero = nesto.split(" ");
	console.log(pero.length)
	console.log(pero)
	*/
	
	var checkedValue = $('.checkbox:checked').attr('title');
	console.log(checkedValue)
	var nesto = checkedValue.split(",");
	console.log(nesto)
	
	
	var red = nesto[0].trim();
	var kolona = nesto[1].trim();
	$.ajax({
		
		url: "bioskop/zauzetiMjesto/"+red+"/"+kolona,
		type: "GET",
		success: function(data){
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
}
