window.onload = function() {
	var form_type = sessionStorage.getItem('create_edit_projection_form_type');
	var cinTheId = sessionStorage.getItem('edit_cinema_projections');
	
	if(form_type == "create"){
		$.ajax({
			url: "movie/prikaziFilmove/",
			type: "GET",
			contentType:"application/json",
			success: function(data){
				for(var i = 0; i < data.length; i++){
					$("#projection_form_input_movie_performance").append("<option value="+data[i].movieId+">Id: "+data[i].movieId+",Ime: "+data[i].name+"</option>");
				}
			},
			error: function() {
			}
		});
		
		$.ajax({
			url: "hall/allHalls/"+cinTheId,
			type: "GET",
			contentType:"application/json",
			success: function(data){
				for(var i = 0; i < data.length; i++){
					$("#projection_form_input_hall").append("<option value="+data[i].id+">Id: "+data[i].id+",Ime: "+data[i].name+"</option>");
				}
			},
			error: function() {
			}
		});
		
	} else {
		//edit hall
		var proj_id = sessionStorage.getItem("edit_projection_id");
		console.log(proj_id);
		var movie_id;
		$.ajax({
			url: "projekcije/"+proj_id,
			type: "GET",
			contentType:"application/json",
			success: function(data){
				console.log(data);
				$("#projection_form_input_name").val(data.name);
				$("#projection_form_input_description").val(data.description);
				$("#projection_form_input_date").val(millisToDate(data.projectionDateTime));
				$("#projection_form_input_time").val(millisToTime(data.projectionDateTime));
				$("#projection_form_input_hall").append("<option value="+data.hallId+">Id: "+data.hallId+",Ime: "+data.hallName+"</option>");
				movie_id = data.moviePerformanceId;
			},
			error: function() {
			}
		});
		
		$.ajax({
			url: "movie/prikaziFilmove/",
			type: "GET",
			contentType:"application/json",
			success: function(data){
				for(var i = 0; i < data.length; i++){
					if(data[i].movieId == movie_id){
						$("#projection_form_input_movie_performance").append("<option value="+data[i].movieId+" selected>Id: "+data[i].movieId+",Ime: "+data[i].name+"</option>");
					}else
						$("#projection_form_input_movie_performance").append("<option value="+data[i].movieId+">Id: "+data[i].movieId+",Ime: "+data[i].name+"</option>");
				}
			},
			error: function() {
			}
		});
	
	}
}

$(document).ready(function(){
	$("#submit_button_projection_form").click(function(){
		var form_type = sessionStorage.getItem('create_edit_projection_form_type');
		
		var cinTheId = sessionStorage.getItem('edit_cinema_projections');
		var name = $("#projection_form_input_name").val();
		var description = $("#projection_form_input_description").val();
		var projectionDate = $("#projection_form_input_date").val();
		var projectionTime = $("#projection_form_input_time").val();
		var moviePerformanceId = $("#projection_form_input_movie_performance").val();
		var hallId = $("#projection_form_input_hall").val();
		var price = $("#projection_form_input_price").val();
		var discount = $("#projection_form_input_discount").val();
		var discountSeatNumber = $("#projection_form_input_discount_seat_number").val();
		
		var theatreCinemaId = cinTheId;
		var projectionDateTime = dateTimeToMillis(projectionDate, projectionTime);
		if(form_type == "create"){
			
			var a = { name, description, projectionDateTime, moviePerformanceId, hallId, theatreCinemaId, price, discount, discountSeatNumber };
			var dto = JSON.stringify( a );
			
		} else {
			
			var id = sessionStorage.getItem('edit_projection_id');
			var a = { id, name, description, projectionDateTime, moviePerformanceId, hallId, theatreCinemaId, price, discount, discountSeatNumber };
			var dto = JSON.stringify( a );
		}
		
		
		if(form_type == "create"){
			$.ajax({
				url: "projekcije/dodaj",
				type: "POST",
				contentType:"application/json",
				dataType:"application/json",
				data : dto,
				success: function(data){
					alert("Dodata nova projekcija");
					
				},
				error: function() {
				}
			});
		} else {
			$.ajax({
				url: "projekcije/izmeni",
				type: "POST",
				contentType:"application/json",
				dataType:"application/json",
				data : dto,
				success: function(data){
					alert("Dodata nova projekcija");
					
				},
				error: function() {
				}
			});
		}
		top.location.href="projekcije.html";
		//console.log("aha! "+name+" "+description+" "+projectionDate+" "+projectionTime+" "+moviePerformanceId+" "+hallId+" "+theatreCinemaId);
		//console.log("id bioskopa "+cinTheId);
	});
	
});

function millisToTime(millis){
	var time = new Date(millis);
	
	return time.getHours() + ":" + time.getMinutes() ;
}

function millisToDate(millis){
	var date = new Date(millis);
	//yyyy-MM-dd
	//return date.getDate() + "." + date.getMonth() + "." + date.getFullYear();

	var day = ("0" + date.getDate()).slice(-2);
	var month = ("0" + (date.getMonth() + 1)).slice(-2);

	return date.getFullYear()+"-"+(month)+"-"+(day) ;
}

function dateTimeToMillis(d, t){
	//console.log(d.substring(0,4), d.substring(5,7), d.substring(8,10), t.substring(0,2), t.substring(3.5));
	var d = new Date(d.substring(0,4), d.substring(5,7), d.substring(8,10), t.substring(0,2), t.substring(3.5), 0, 0);
	console.log(d);
	return d.getTime();
}