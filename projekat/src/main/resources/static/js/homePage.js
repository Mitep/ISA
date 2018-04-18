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
	
	$.ajax({
		url: "medalje/getUserNaSkali",
		type:"GET",
		success : function(data){
				
		},
		error: function(){
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
				console.log(data[i])
		
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
	
