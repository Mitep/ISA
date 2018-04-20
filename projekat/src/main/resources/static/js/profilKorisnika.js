window.onload = function(){
	
	$.ajax({
		url: "user/getUser" ,
		type:"GET",
		
		contentType:"application/json",
		dataType:"json",
		success : function(data){
			if (data!= null) {
				renderUser(data);
			}else{	
			}
		},
		error: function(jqxhr,textStatus,errorThrown){
			alert(errorThrown);
		}
	});
	
	$.ajax({
		 url: "user/getFriendRequests",
		 method: "GET",
		 success: function(data){
			 console.log(data);
			 $(".friendsRequest").empty();
			 for(i=0;i<data.length;i++){
				 $(".friendsRequest").append( 
						 "<tr><td>Ime:</td><td>" + data[i].userName + "</td></tr>"+
						 "<tr><td>Prezime:</td><td>" + data[i].userSurname + "</td></tr>"+
						 "<tr><td><input type=\"button\" value = \"Prihvati\" onclick = \"prihvatiPrijatelja("+data[i].userId+")\"></td><tr>"+
						 "<tr><td><input type=\"button\" value = \"Odbij\" onclick = \"odbijPrijatelja("+data[i].userId+")\"></td><tr>");
				 
					
			 }
		 },
		 error: function(){
			 alert("Doslo je do greske!");
		 }
	});
	
	
	$.ajax({
		url: "user/getFriends",
		 method: "GET",
		 success: function(data){
			 $(".friends").empty();
			 for(i=0;i<data.length;i++){
				 $(".friends").append("<tr><td>" + data[i].userName + "</td><td>" + data[i].userSurname + "</td><td>" + data[i].email + "</td><td>" + data[i].city + "</td><td>" + data[i].mobileNumber + "</td><td><input type=\"button\" value = \"Obrisi prijatelja\" onclick = \"obrisiPrijatelja("+data[i].userId+")\"></td></tr>");
			 }
		 },
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
	
	$.ajax({
		 url: "user/getPozorista",
		 method: "GET",
		 success: function(data){
			 $(".listaPozorista").empty();
			 for(i=0;i<data.length;i++){
				 if(data[i].type == "THEATRE"){
					 $(".listaPozorista").append("<tr><td>" + data[i].name + "</td><td>" + data[i].adress + "</td><td>" + data[i].description + "</td></tr>");
				 	}
				 }
		 },
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
	
	$.ajax({
		 url: "user/getBioskopi",
		 method: "GET",
		 success: function(data){
			 $(".listaBioskopa").empty();
			 for(i=0;i<data.length;i++){
				if(data[i].type == "CINEMA"){ 
					 $(".listaBioskopa").append("<tr><td>" + data[i].name + "</td><td>" + data[i].adress + "</td><td>" + data[i].description + "</td></tr>");
				} 
			}
		 },
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
	
	
	
}


function formaZaIzmjenu(){
	
	window.location.href = "formaZaIzmjenu.html"
}

	

function renderUser(user){
	
	$("#userName").empty();
	$("#userName").append("Korisnik : " + user.userName);
	
	$("#email").empty();
	$("#email").append(user.email);
	
	$("#userPassword").empty();
	$("#userPassword").append(user.userPassword);
	
	$("#userPasswordConf").empty();
	$("#userPasswordConf").append(user.userPasswordConf);
	
	$("#userIme").empty();
	$("#userIme").append(user.userName);
	
	$("#userSurname").empty();
	$("#userSurname").append(user.userSurname);
	

	$("#city").empty();
	$("#city").append(user.city);
	
	
	$("#mobileNumber").empty();
	$("#mobileNumber").append(user.mobileNumber);
		
}

function searchUsers(){
	

	var userName = $("#name").val();
	var userSurname = $("#surname").val();

	if(userName == ""){
		userName = "nista";
	}
	if(userSurname == ""){
		userSurname = "nista";
	}
	
	$.ajax({	
	url: "user/searchUsers/"+userName+"/"+userSurname,
	type: "GET",
	success: function(data){
		 $(".users").empty();
		 for(i=0;i<data.length;i++){
				 $(".users").append(
						 "<tr><td>Ime:</td><td>" + data[i].userName + "</td></tr>"+
						 "<tr><td>Prezime:</td><td>" + data[i].userSurname + "</td></tr>"+
						 "<tr><td>Email:</td><td>" + data[i].email + "</td></tr>"+
						 "<tr><td>Grad:</td><td>" + data[i].city + "</td></tr>"+
						 "<tr><td>Broj telefona:</td><td>" + data[i].mobileNumber + "</td></tr>+" +
						 "<tr><td><input type=\"button\" value = \"Dodaj prijatelja\" onclick = \"dodajPrijatelja("+data[i].userId+")\"></td><tr>");
	
		 	}
	 	}
	});
}

function dodajPrijatelja(userId){
	
	$.ajax({	
		url: "user/dodajPrijatelja/"+userId,
		type: "GET",
		success: function(data){
			if(data){
				alert("Uspjesno ste poslali zahtjev");
			}else{
				alert("Niste poslali zahtjev, jer saljete sami sebi ili nekom od admina!");
			}
		},
	 	error: function(){
	 		alert("Niste poslali zahtjev!");
	 	}
		});
	
}

function prihvatiPrijatelja(userId){
	
	$.ajax({
		 url: "user/prihvatiPrijatelja/"+userId,
		 method: "GET",
		 success: function(){
			 
			 alert("Prihvatili ste zahtjev za prijateljstvo!");
			 top.location.href="profilKorisnika.html";
			 
		 },
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
	
}

function odbijPrijatelja(userId){
	
	$.ajax({
		 url: "user/odbijPrijatelja/"+userId,
		 method: "GET",
		 success: function(){
			 
			 alert("Odbili ste zahtjev za prijateljstvo!");
			 top.location.href="profilKorisnika.html";
		 },
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
}

function obrisiPrijatelja(userId){
	
	$.ajax({
		 url: "user/obrisiPrijatelja/"+userId,
		 method: "GET",
		 success: function(data){
			 if(data){
			 alert("Obrisali ste prijatelja!");
			 top.location.href="profilKorisnika.html";
			 }
		},
		 error: function(){
			 alert("Doslo je do greske");
		 }
	});
	
	
}

function sortTable(n) {

	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById("myTable");
		console.log(table)
	  switching = true;
	  //Set the sorting direction to ascending:
	  dir = "asc"; 
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.getElementsByTagName("TR");
		console.log(rows)
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 1; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("TD")[n];
	      y = rows[i + 1].getElementsByTagName("TD")[n];
	      console.log(x)
	      console.log(y)
	      /*check if the two rows should switch place,
	      based on the direction, asc or desc:*/
	      if (dir == "asc") {
	        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      } else if (dir == "desc") {
	        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      }
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	      //Each time a switch is done, increase this count by 1:
	      switchcount ++;      
	    } else {
	      /*If no switching has been done AND the direction is "asc",
	      set the direction to "desc" and run the while loop again.*/
	      if (switchcount == 0 && dir == "asc") {
	        dir = "desc";
	        switching = true;
	      }
	    }
	  }
	}


function sortTable2(n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById("myTable2");
	  switching = true;
	  //Set the sorting direction to ascending:
	  dir = "asc"; 
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.getElementsByTagName("TR");
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 1; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("TD")[n];
	      y = rows[i + 1].getElementsByTagName("TD")[n];
	      /*check if the two rows should switch place,
	      based on the direction, asc or desc:*/
	      if (dir == "asc") {
	        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      } else if (dir == "desc") {
	        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      }
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	      //Each time a switch is done, increase this count by 1:
	      switchcount ++;      
	    } else {
	      /*If no switching has been done AND the direction is "asc",
	      set the direction to "desc" and run the while loop again.*/
	      if (switchcount == 0 && dir == "asc") {
	        dir = "desc";
	        switching = true;
	      }
	    }
	  }
	}


function sortTable3(n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  table = document.getElementById("myTable3");
	  switching = true;
	  //Set the sorting direction to ascending:
	  dir = "asc"; 
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.getElementsByTagName("TR");
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 1; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("TD")[n];
	      y = rows[i + 1].getElementsByTagName("TD")[n];
	      /*check if the two rows should switch place,
	      based on the direction, asc or desc:*/
	      if (dir == "asc") {
	        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      } else if (dir == "desc") {
	        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      }
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	      //Each time a switch is done, increase this count by 1:
	      switchcount ++;      
	    } else {
	      /*If no switching has been done AND the direction is "asc",
	      set the direction to "desc" and run the while loop again.*/
	      if (switchcount == 0 && dir == "asc") {
	        dir = "desc";
	        switching = true;
	      }
	    }
	  }
	}