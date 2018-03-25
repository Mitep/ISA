$(document).ready(function(){
	$("#home_div").hide();
	$("#prijatelji_div").hide();
	$("#pozorista_div").hide();
	$("#bioskopi_div").hide();
	$("#rezervacije_div").hide();
	$("#podesavanja_div").hide();
	$("#fanzona_div").hide();
	
	$('a[href="#home"]').click(function(){
		$("#home_div").show();
		$("#prijatelji_div").hide();
		$("#pozorista_div").hide();
		$("#bioskopi_div").hide();
		$("#rezervacije_div").hide();
		$("#podesavanja_div").hide();
		$("#fanzona_div").hide();
	});
	
	$('a[href="#prijatelji"]').click(function(){
		$("#home_div").hide();
		$("#prijatelji_div").show();
		$("#pozorista_div").hide();
		$("#bioskopi_div").hide();
		$("#rezervacije_div").hide();
		$("#podesavanja_div").hide();
		$("#fanzona_div").hide();
	});
		
	$('a[href="#pozorista"]').click(function(){
		$("#home_div").hide();
		$("#prijatelji_div").hide();
		$("#pozorista_div").show();
		$("#bioskopi_div").hide();
		$("#rezervacije_div").hide();
		$("#podesavanja_div").hide();
		$("#fanzona_div").hide();
	});
	
	$('a[href="#bioskopi"]').click(function(){
		$("#home_div").hide();
		$("#prijatelji_div").hide();
		$("#pozorista_div").hide();
		$("#bioskopi_div").show();
		$("#rezervacije_div").hide();
		$("#podesavanja_div").hide();
		$("#fanzona_div").hide();
	});
	
	$('a[href="#rezervacije"]').click(function(){
		$("#home_div").hide();
		$("#prijatelji_div").hide();
		$("#pozorista_div").hide();
		$("#bioskopi_div").hide();
		$("#rezervacije_div").show();
		$("#podesavanja_div").hide();
		$("#fanzona_div").hide();
	});
	
	$('a[href="#podesavanja"]').click(function(){
		$("#home_div").hide();
		$("#prijatelji_div").hide();
		$("#pozorista_div").hide();
		$("#bioskopi_div").hide();
		$("#rezervacije_div").hide();
		$("#podesavanja_div").show();
		$("#fanzona_div").hide();
	});
	
	$('a[href="#fanzona"]').click(function(){
		$("#home_div").hide();
		$("#prijatelji_div").hide();
		$("#pozorista_div").hide();
		$("#bioskopi_div").hide();
		$("#rezervacije_div").hide();
		$("#podesavanja_div").hide();
		$("#fanzona_div").show();
	});
		
});

function noviOglas(){
	window.location.href="stranicaOglas.html";		
		}
	
	
