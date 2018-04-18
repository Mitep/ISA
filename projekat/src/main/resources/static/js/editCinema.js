$(document).ready(function(){
	var cin_id = sessionStorage.getItem('edit_cinema_id');
	$.ajax({
		url: "bioskop/"+cin_id,
		type:"GET",
		contentType:"application/json",
		success : function(data){
			console.log(data);
			
			$("#input_name").val(data.name);
			$("#input_adress").val(data.adress);
			$("#input_description").val(data.description);
			$("#edit_cinema").val(data.id);
			
			for(var i = 0; i < data.halls.length; i++){
				$("#sale").append("<div class='sala' id="+data.halls[i].id+">ID sale:"+data.halls[i].id+"<br> Naziv sale: "+data.halls[i].name+
				"<form accept-charset='UTF-8' >"+
			   	   "<input placeholder='' type='text' name='' id=''>"+
				   "<button> Izmeni ime sale</button></form>" +
				   "<button> Obrisi salu</button><br>" +
				   "Segmenti<hr>");
				
				if(data.halls[i].segments != null){
					var segments = data.halls[i].segments;
					for(var j = 0; j < segments.length; j++){
						$("#"+data.halls[i].id+".sala").append("Id segmenta: "+segments[j].id+"<br>Naziv segmenta: "+segments[j].name);
						
						$("#"+data.halls[i].id+".sala").append("<form accept-charset='UTF-8' >"+
			   	   "<input placeholder='' type='text' name='' id=''>"+
				   "<button> Izmeni ime segmenta</button></form>" +
				   "<button> Obrisi segment</button>");
						
						$("#"+data.halls[i].id+".sala").append("<br>Sedista<br>" +
						"<table id="+segments[j].id+" class='sedista_tabela'></table><hr>");
						
						//prvo pravimo praznu tabelu sa mxn kolona i redova pa posle dodajemo sedisa u tu tabelu
						for(var m = 0; m < segments[j].rowNum; m++){
							$("#"+segments[j].id+".sedista_tabela").append("<tr class="+(m+1)+">");
							for(var n = 0; n < segments[j].colNum; n++){
								var tooltip = String((m+1) + " red, " + (n+1) + " kolona");
								console.log(tooltip);
								$("#"+segments[j].id+".sedista_tabela").append("<td class="+(n+1)+">"+
										"<input type='checkbox'  title=' "+tooltip+" ' ></td>");
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
		
			}	
		},
		error: function(){
	
		}
		});
	//sessionStorage.removeItem('edit_cinema_id');

});