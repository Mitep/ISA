function izmjeniKorisnika(){
	
	var $form = $("#editForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	console.log(s);
	$.ajax({
		
		url: "user/editUser",
		type: "PUT",
		data: s,
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			if(data){
				alert("Uspjesno ste se izmjenili!");
				top.location.href="homePage.html";
			}else
				alert("Niste izmjenili podatke!");
			
		}
	
	});
	
}