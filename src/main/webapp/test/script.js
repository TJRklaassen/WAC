fetch("../restservices/countries/largestpopulations")
	.then(response => Promise.all([response.status, response.json()]))
	.then(function([status, myJson]) {
		if(status == 200) console.log(myJson);
		else console.log(myJson.error);
	})
	.catch(error => console.log(error.message));