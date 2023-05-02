<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Weather API Example</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<script type="text/javascript" src="scripts/timer.js">
; 
</script>

<body>
	<div id="timer"></div>

	<h1>Current Weather</h1>
	<div id="temperature"></div>
	<div id="feels-like"></div>
	<div id="time-zone"></div>
	<div id="country"></div>
	<div id="city"></div>
	<div id="latitude"></div>
	<div id="longitude"></div>
	
	<button onclick="goBack()">Back</button>
	
	<script>
		displayTimeAndDate("timer");
		updateWeather('${input}');

		//setInterval(refreshData, 20000);
		window.onload = function() {
			updateWeather('${input}');
		}
	</script>
	
	
	
</body>
</html>
