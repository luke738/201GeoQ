<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/QuestionPage.css">
    <link href="https://fonts.googleapis.com/css?family=IBM+Plex+Mono" rel="stylesheet">
    <title>Street View</title>
    <script>
	    function connectToServer() 
		{
			// asychronous connection
			socket = new WebSocket("ws://localhost:8080/GeoQ/ws");
			
			//overwriting the function in javascript
			socket.onopen = function(event) {
				//document.getElementById("game").innerHTML += "Connected<br />";
			}
			
			socket.onmessage = function(event) 
			{
				console.log("1");
				if(event.data === "Next Question") {
					console.log("next");
					document.getElementById("button1").style.background = "#A8A8A8";
					document.getElementById("button2").style.background = "#A8A8A8";
					document.getElementById("button3").style.background = "#A8A8A8";
					document.getElementById("button4").style.background = "#A8A8A8";
				}
				else 
				{
					console.log("2" + event.data);
					console.log(document.getElementById("button1").value);
					if(event.data === document.getElementById("button1").value) {
						document.getElementById("button1").style.background = "green";
						document.getElementById("button2").style.background = "red";
						document.getElementById("button3").style.background = "red";
						document.getElementById("button4").style.background = "red";
					}
					else if(event.data === document.getElementById("button2").value) {
						document.getElementById("button1").style.background = "red";
						document.getElementById("button2").style.background = "green";
						document.getElementById("button3").style.background = "red";
						document.getElementById("button4").style.background = "red";
					}
					else if(event.data === document.getElementById("button3").value) {
						document.getElementById("button1").style.background = "red";
						document.getElementById("button2").style.background = "red";
						document.getElementById("button3").style.background = "green";
						document.getElementById("button4").style.background = "red";
					}
					else {
						document.getElementById("button1").style.background = "red";
						document.getElementById("button2").style.background = "red";
						document.getElementById("button3").style.background = "red";
						document.getElementById("button4").style.background = "green";
					}
					
					if(choice === event.data) {
						
					}
					else {
						
					}
				}
				//document.getElementById("mychat").innerHTML += event.data + "<br />";
			}
			
			socket.onclose = function(event) {
				//document.getElementById("mychat").innerHTML += "Disconnected<br />";					
			}
		}
	    
	    function sendChoice1() {
			choice = document.game.choice1.value;
			
			document.getElementById("button1").style.background = "blue";
			document.getElementById("button2").style.background = "#A8A8A8";
			document.getElementById("button3").style.background = "#A8A8A8";
			document.getElementById("button4").style.background = "#A8A8A8";
			
			return false;
		}
		
		function sendChoice2() {
			choice = document.game.choice2.value;
			
			document.getElementById("button1").style.background = "#A8A8A8";
			document.getElementById("button2").style.background = "blue";
			document.getElementById("button3").style.background = "#A8A8A8";
			document.getElementById("button4").style.background = "#A8A8A8";
			
			return false;
		}
		
		function sendChoice3() {
			choice = document.game.choice3.value;
			
			document.getElementById("button1").style.background = "#A8A8A8";
			document.getElementById("button2").style.background = "#A8A8A8";
			document.getElementById("button3").style.background = "blue";
			document.getElementById("button4").style.background = "#A8A8A8";
			
			return false;
		}
		
		function sendChoice4() {
			choice = document.game.choice4.value;
			
			document.getElementById("button1").style.background = "#A8A8A8";
			document.getElementById("button2").style.background = "#A8A8A8";
			document.getElementById("button3").style.background = "#A8A8A8";
			document.getElementById("button4").style.background = "blue";
			
			return false;
		}
		
    
    </script>
  </head>
  <body onload="connectToServer()">
  <div class="chatBox"> <span>Insert chatbox here</span></div>
    <div id="street-view"></div>
    <div class = "questionBox">
    		<form name="game">
    			<input type="button" class="button" id="button1" name="choice1" onclick="sendChoice1()" value="Paris" />
			<input type="button" class="button" id="button2" name="choice2" onclick="sendChoice2()" value="NYC" />
			<input type="button" class="button" id="button3" name="choice3" onclick="sendChoice3()" value="Los Angeles" />
			<input type="button" class="button" id="button4" name="choice4" onclick="sendChoice4()" value="London" />
		</form>
    </div>
    <script>
      var panorama;
      function initialize() {
        panorama = new google.maps.StreetViewPanorama(
            document.getElementById('street-view'),
            
            {
              position: {lat: 37.869260, lng: -122.254811},
              pov: {heading: 165, pitch: 0},
              zoom: 1
            });
      
      }
      

    </script>	
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdAJm9Qb2ncMLsHFZSEzDs5U07qk4jRF4
         &callback=initialize">
    </script>
  </body>
</html>