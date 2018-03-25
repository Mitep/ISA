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
				top.location.href="index.html";
			}else
				alert("Niste dodali oglas!");
			
		}
	
	});
}