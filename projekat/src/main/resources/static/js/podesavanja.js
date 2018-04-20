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
				$("#oldPassword").val(data.oldPassword);
				$("#newPassword").val(data.newPassword);
				$("#repeatNewPassrword").val(data.repeatNewPassword);
				alert("Uspjesno ste izmjenili password.");
				top.location.href="fanZona.html";
			}else
				alert("Morate izmijeniti password da bi nastavili sa daljim aktivnostima.");
				
		}
	
	});
	
}