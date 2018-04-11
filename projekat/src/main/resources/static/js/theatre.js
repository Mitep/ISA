function dodajPozoriste() {
	var $form = $("#pozoristeForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "pozoriste/addPozoriste",
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste dodali pozoriste!");
				top.location.href="theatre.html";
			}else
				alert("Niste dodali pozoriste!");
			
		}
	
	});
	
}

window.onload = function() {
	$.ajax({
			
			url: "pozoriste/prikaziPozoriste",
			type: "GET",
			success: function(data){
				$(".prikaz").empty();
				for(i=0;i<data.length;i++) {
					if(data[i].type == "THEATRE") {
					$(".prikaz").append("<tr><td>Naziv: </td><td>" + data[i].name + "</td></tr> "+
							 "<tr><td>Adresa: </td><td>" + data[i].adress + "</td></tr> "+
							 "<tr><td>Opis: </td><td>" + data[i].description + "</td></tr> " +
							 "<tr><td><input type=\"button\" value = \"Ukloni\">" +
								"<input type=\"button\" value = \"Izmijeni\"></td></tr>"
							);
					}
					
				}
				
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