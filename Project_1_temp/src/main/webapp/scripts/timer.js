/**
 * 
 */
function countDown(timerId, date, buttonID) {
	var countDownDate = new Date(date).getTime();

	// Update the count down every 1 second
	var x = setInterval(function() {

		// Get today's date and time
		var now = new Date().getTime();

		// Find the distance between now and the count down date
		var distance = countDownDate - now;

		// Time calculations for days, hours, minutes and seconds
		var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Output the result in an element with id="demo"
		document.getElementById(timerId).innerHTML = days + "d " + hours + "h "
			+ minutes + "m " + seconds + "s ";

		// If the count down is over, write some text 
		if (distance < 0) {
			clearInterval(x);
			document.getElementById(timerId).innerHTML = "EXPIRED";
			document.getElementById(buttonID).disabled = true;  
		}
	}, 1000);
}

function displayTimeAndDate(elementId) {
	// Update the count down every 1 second
	var x = setInterval(function() {
		
		var date = new Date();
		var current_date = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+ date.getDate();
		var current_time = date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds();
		var date_time = current_date+" "+current_time;	
		document.getElementById(elementId).innerHTML = date_time;
		
	}, 1000);
}

function goBack() {
  window.history.back();
}


function updateWeather(input) {
		$(document).ready(function() {
			const settings = {
				"async": true,
				"crossDomain": true,
				//"url": "https://weatherapi-com.p.rapidapi.com/current.json?q=" + '${input}',
				"url": "https://weatherapi-com.p.rapidapi.com/current.json?q=" + input,
				"method": "GET",
				"headers": {
					"X-RapidAPI-Key": "aaafa6204emshca8ca811f383bc2p166c8ajsnfa1e6b20fae5",
					"X-RapidAPI-Host": "weatherapi-com.p.rapidapi.com"
				}
			};

			$.ajax(settings).done(function (response) {
				console.log(response);

				$("#temperature").html("Current temperature: " + response.current.temp_c + "&#8451; / " + response.current.temp_f + " &#8457;");

				$("#feels-like").html("Feels like: " + response.current.feelslike_c + " &#8451; / " + response.current.feelslike_f + " &#8457;");

				$("#time-zone").html("Time Zone: " + response.location.tz_id);

				$("#country").html("Country: " + response.location.country);

				$("#city").html("City: " + response.location.name);

				$("#latitude").html("Latitude: " + response.location.lat);

				$("#longitude").html("Longitude: " + response.location.lon);
				/* document.getElementById("temperature").textContent = "Current temperature (Celsius): " + response.current.temp_c + " &#8451;";
				document.getElementById("temperature").textContent += "\nCurrent temperature: (Fahrenheit)" + response.current.temp_f + " &#8457;";

				document.getElementById("feels-like").textContent = "Current temperature: (Celsius)" + response.current.condition.feelslike_c + " &#8451;";
				document.getElementById("feels-like").textContent += "\nCurrent temperature: (Fahrenheit)" + response.current.condition.feelslike_f + " &#8457;";

				document.getElementById("time-zone").textContent = "Time Zone: " + response.location.tz_id;
				document.getElementById("country").textContent = "Country: " + response.location.country;
				document.getElementById("city").textContent = "City: " + response.location.name;
				document.getElementById("latitude").textContent = "Latitude: " + response.location.lat;
				document.getElementById("longitude").textContent = "Longitude: " + response.location.lon; */
			});
		});

		setTimeout(updateWeather, 20000);
	}