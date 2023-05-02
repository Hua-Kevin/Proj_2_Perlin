

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>
	<style>
		body {
			background-image: url("images/sky_draw.jpg");
			background-size: cover;
			background-position: center;
			font-family: Arial, sans-serif;
		}
		h1 {
			text-align: center;
			color: black;
			font-size: 3em;
			margin-top: 10%;
		}
		.button-container {
			display: flex;
			justify-content: center;
			margin-top: 10%;
		}
		.button {
			display: block;
			padding: 1em 2em;
			background-color: #4CAF50;
			color: white;
			text-align: center;
			text-decoration: none;
			font-size: 20em;
			margin: 0 6em;
			border-radius: 100px;
			transition: background-color 0.3s ease-in-out;
		}
		.button:hover {
			background-color: #3e8e41;
		}
	</style>
</head>
<body bgcolor = "#f0f0f0">
<h1> WELCOME </h1>
<form action="ServiceInvokerServlet">
	Enter your location: <input type="text" name="theInput"/><br/>
	<input type="submit" value="submit"/> </form>
</body>

<p> Query parameter based on which data is sent back. <br> <br>
It could be following: <br>
1) Latitude and Longitude (Decimal degree) e.g: q=48.8567,2.3508 <br>
2) City name e.g.: q=Paris <br>
3) US zip e.g.: q=10001 <br>
4) UK postcode e.g: q=SW1 <br>
5) Canada postal code e.g: q=G2J <br>
6) metar: e.g: q=metar:EGLL <br>
7) iata:(3 digit airport code) e.g: q=iata:DXB <br>
8) auto:ip IP lookup e.g: q=auto:ip <br>
9) IP address (IPv4 and IPv6 supported) e.g: q=100.0.0.1</p>
</html>

