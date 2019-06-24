initPage();

function initPage(){
	var Httpreq = new XMLHttpRequest();
	Httpreq.open("GET", "https://ipapi.co/json/", false);
	Httpreq.send(null);
	var json_obj = JSON.parse(Httpreq.responseText);
	
	document.getElementById("landcode").innerText = json_obj["country"];
	document.getElementById("land").innerText = json_obj["country_name"];
	document.getElementById("regio").innerText = json_obj["region"];
	document.getElementById("stad").innerText = json_obj["city"];
	document.getElementById("postcode").innerText = json_obj["postal"];
	document.getElementById("latitude").innerText = json_obj["latitude"];
	document.getElementById("longitude").innerText = json_obj["longitude"];
	document.getElementById("ip").innerText = json_obj["ip"];
	
	showWeather(json_obj["latitude"], json_obj["longitude"], json_obj["city"]);
	loadCountries();
	
	document.querySelector("#login").addEventListener("click", login);
	
	document.getElementById("locatie").addEventListener("click", function(){
		showWeather(json_obj["latitude"], json_obj["longitude"], json_obj["city"]);
	})
	
	document.querySelector("#post").addEventListener('click', function(){
		var formData = new FormData(document.querySelector("#POSTcountry"));
		var encData = new URLSearchParams(formData.entries());
		
		fetch("../restservices/countries", { method: 'POST', body: encData, headers: {
			'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
		}})
			.then(response => Promise.all([response.status, response.json()]))
			.then(function([status, myJson]) { 
				if (status == 200) console.log(myJson.success); 
				else console.log(myJson.error);
			})
			setTimeout(function(){ loadCountries(); }, 500);
	})
}

function showWeather(latitude, longitude, city){
	var weather = JSON.parse(window.sessionStorage.getItem("weather"+latitude+longitude+city));
	var time = window.sessionStorage.getItem("time"+latitude+longitude+city);
	
	var updateInterval = 1 * 10 * 1000; //Time interval to wait in seconds (min * sec * ms)
	
	if(weather == null || ((new Date).getTime() - time) > updateInterval){
		var Httpreq = new XMLHttpRequest();
		Httpreq.open("GET", "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude +
				"&lon=" + longitude +
				"&units=metric&APPID=700f58482f5e1f311f2b6b36f7cc22e5", false);
		Httpreq.send(null);
		var json_obj = JSON.parse(Httpreq.responseText);
		weather = json_obj;
		
		window.sessionStorage.setItem("weather"+latitude+longitude+city, JSON.stringify(json_obj));
		window.sessionStorage.setItem("time"+latitude+longitude+city, (new Date).getTime());
		
		console.log("Weather in "+city+" updated.");
	}
	
	var zonsopgangDate = new Date(weather["sys"]["sunrise"]*1000);
	var zonsopgangHours = zonsopgangDate.getHours();
	var zonsopgangMinutes = "0" + zonsopgangDate.getMinutes();
	var zonsopgangSeconds = "0" + zonsopgangDate.getSeconds();
	var zonsopgang = zonsopgangHours + ':' + zonsopgangMinutes.substr(-2) + ':' + zonsopgangSeconds.substr(-2);
	
	var zonsondergangDate = new Date(weather["sys"]["sunset"]*1000);
	var zonsondergangHours = zonsondergangDate.getHours();
	var zonsondergangMinutes = "0" + zonsondergangDate.getMinutes();
	var zonsondergangSeconds = "0" + zonsondergangDate.getSeconds();
	var zonsondergang = zonsondergangHours + ':' + zonsondergangMinutes.substr(-2) + ':' + zonsondergangSeconds.substr(-2);
	
	var deg = weather["wind"]["deg"];
	
	if(deg >= 337.50 && deg <= 22.50){
		var degText = "Noord";
	} else if (deg > 22.50 && deg < 67.50) {
		var degText = "Noord-Oost";
	} else if (deg >= 67.50 && deg <= 112.50) {
		var degText = "Oost";
	} else if (deg > 112.50 && deg < 157.50) {
		var degText = "Zuid-Oost";
	} else if (deg >= 157.50 && deg <= 202.50) {
		var degText = "Zuid";
	} else if (deg > 202.50 && deg < 247.50) {
		var degText = "Zuid-West";
	} else if (deg >= 247.50 && deg <= 292.50) {
		var degText = "West";
	} else if (deg > 292.50 && deg < 337.50) {
		var degText = "Noord-West";
	} else {
		var degText = "not found."
	}
	
	document.getElementById("stadHeader").innerText = city;
	document.getElementById("temperatuur").innerText = weather["main"]["temp"];
	document.getElementById("luchtvochtigheid").innerText = weather["main"]["humidity"];
	document.getElementById("windsnelheid").innerText = weather["wind"]["speed"];
	document.getElementById("windrichting").innerText = degText;
	document.getElementById("zonsopgang").innerText = zonsopgang;
	document.getElementById("zonsondergang").innerText = zonsondergang;
}

function loadCountries(){
	fetch('../restservices/countries')
		.then(response => response.json())
		.then(function(myJson) {
			var table = document.getElementById("bestemmingen");
			
			for(var i = table.rows.length - 1; i > 0; i--){
				table.deleteRow(i);
			}
			
			for(var i=0; i < myJson.length; i++){
				(function (i) {
					var row = table.insertRow(i+1);
					var lat = myJson[i]["latitude"];
					var lng = myJson[i]["longitude"];
					var city = myJson[i]["capital"];
					
					row.addEventListener('click', function(){
						showWeather(lat, lng, city);
					});
				
					row.className = 'bestemming';

					row.insertCell(0).innerHTML = myJson[i]["name"];
					row.insertCell(1).innerHTML = myJson[i]["capital"];
					row.insertCell(2).innerHTML = myJson[i]["region"];
					row.insertCell(3).innerHTML = myJson[i]["surface"];
					row.insertCell(4).innerHTML = myJson[i]["population"];
					var rowCell = row.insertCell(5);
				
					var btnEdit = document.createElement("BUTTON");
					
					btnEdit.innerHTML = "Wijzig";
					btnEdit.className = "btn";
					rowCell.appendChild(btnEdit);
				
					btnEdit.addEventListener('click', function(){
						document.getElementById("landEdit").innerHTML = myJson[i]["name"];
						document.getElementById("edit").style.display = "block";
						window.location.hash = "#edit";
						
						var button = document.createElement("INPUT");
						button.setAttribute("type", "button");
						button.setAttribute("value", "Bevestig");
						
						document.getElementById("buttonContainer").innerHTML = "";
						document.getElementById("buttonContainer").appendChild(button);
						button.addEventListener('click', function() {
							var formData = new FormData(document.querySelector("#PUTcountry"));
							var encData = new URLSearchParams(formData.entries());
							
							fetch("../restservices/countries/"+myJson[i]["code"], { method: 'PUT', body: encData, headers: {
								'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
							}})
								.then(response => Promise.all([response.status, response.json()]))
								.then(function([status, myJson]) { 
									if (status == 200) console.log(myJson.success); 
									else console.log(myJson.error);
								})
							setTimeout(function(){ loadCountries(); }, 500);
						});
					});
					
					var btnDelete = document.createElement("BUTTON");
					btnDelete.innerHTML = "Verwijder";
					btnDelete.className = "btn";
					rowCell.appendChild(btnDelete);
					
					btnDelete.addEventListener('click', function(){
						fetch("../restservices/countries/"+myJson[i]["code"], { method: 'DELETE', headers: {
							'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
						}})
							.then(function (response) {
								if (response.ok) console.log("Country deleted!");
								else if (response.status == 404) console.log("Country not found!");
								else console.log("Can't delete country!")
							})
						setTimeout(function(){ loadCountries(); }, 500);
					});
				})(i);
			}
		});
}

function login(event) {
	var formData = new FormData(document.querySelector("#POSTlogin"));
	var encData = new URLSearchParams(formData.entries());
	
	fetch("../restservices/authentication", { method: 'POST', body: encData })
		.then(function(response) {
			if(response.ok) return response.json();
			else throw "Onjuiste gebruikersnaam of wachtwoord";
		})
		.then(myJson => window.sessionStorage.setItem("sessionToken", myJson.JWT))
		.catch(error => console.log(error));
}

