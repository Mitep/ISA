function izmijeniPassword(){
	
	var $form = $("#passwordForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "user/izmijeniPassword",
		type: "PUT",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste izmjenili password.");
				top.location.href="fanZona.html";
			}else
				alert("Niste izmjenili password.");
			
		}
	
	});
	
}