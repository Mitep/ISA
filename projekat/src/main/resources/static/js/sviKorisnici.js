
window.onload = function(){
	
	
	$.ajax({
		url: "user/getKorisnici",
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			$("#sviKorisnici").empty();
			for(i=0;i<data.length;i++) {
				$("#sviKorisnici").append("<tr>" +
						"<td>" + data[i].userName + "</td>" +
						"<td>" + data[i].userSurname + "</td>" +
						"<td>" + data[i].city + "</td>" +
						"<td>" + data[i].mobileNumber + "</td>" +
						"<td>" + data[i].email + "</td>" +
						"<td>" + data[i].userPassword + "</td>" +
						"<td>" + data[i].userRole + "</td>" +
						"<td><a href=\"javascript:;\" onclick=\"promoteUser('"+data[i].userId+"')\">Promote	</a>" +
						"<a href=\"javascript:;\" onclick=\"demoteUser('"+data[i].userId+"')\"> Demote	</a>" +
						"</td></tr>");
				}
				
		},
		error: function(){
			alert("Samo administrator sistema ima mogucnost pristupa ovoj stranici.")
		}
		});
}

function promoteUser(userId){
	$.ajax({
		url: "user/promoteUser/" + userId ,
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			$("#sviKorisnici").empty();
			for(i=0;i<data.length;i++) {
				$("#sviKorisnici").append("<tr>" +
						"<td>" + data[i].userName + "</td>" +
						"<td>" + data[i].userSurname + "</td>" +
						"<td>" + data[i].city + "</td>" +
						"<td>" + data[i].mobileNumber + "</td>" +
						"<td>" + data[i].email + "</td>" +
						"<td>" + data[i].userPassword + "</td>" +
						"<td>" + data[i].userRole + "</td>" +
						"<td><a href=\"javascript:;\" onclick=\"promoteUser('"+data[i].userId+"')\">Promote	</a>" +
						"<a href=\"javascript:;\" onclick=\"demoteUser('"+data[i].userId+"')\"> Demote	</a>" +
						"</td></tr>");
				}
				
		},
		error: function(){
			alert("Nije moguce promote-ovati sistemskog administratora ili nemate ovlascenja za to.")
		}
		});
}

function demoteUser(userId){
	
	$.ajax({
		url: "user/demoteUser/" + userId ,
		type:"GET",
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			$("#sviKorisnici").empty();
			for(i=0;i<data.length;i++) {
				$("#sviKorisnici").append("<tr>" +
						"<td>" + data[i].userName + "</td>" +
						"<td>" + data[i].userSurname + "</td>" +
						"<td>" + data[i].city + "</td>" +
						"<td>" + data[i].mobileNumber + "</td>" +
						"<td>" + data[i].email + "</td>" +
						"<td>" + data[i].userPassword + "</td>" +
						"<td>" + data[i].userRole + "</td>" +
						"<td><a href=\"javascript:;\" onclick=\"promoteUser('"+data[i].userId+"')\">Promote	</a>" +
						"<a href=\"javascript:;\" onclick=\"demoteUser('"+data[i].userId+"')\"> Demote	</a>" +
						"</td></tr>");
				}
				
		},
		error: function(){
			alert("Nije moguce demote-ovati obicnog korisnika ili nemate ovlascenja za to.")
		}
		});
}