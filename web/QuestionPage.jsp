<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/QuestionPage.css">
    <link href="https://fonts.googleapis.com/css?family=IBM+Plex+Mono" rel="stylesheet">
    <title>Street View</title>
    <script src="js/chat.js"></script>
    <script src="js/leaderboard.js"></script>
    <script>
		var choice;
	    function connectToGame() 
		{
			// asychronous connection
			var socket = new WebSocket("ws://localhost:8080/GeoQ/ws");
			
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
					
					//initialize();
					//gotta call initialize() again with different parameters
					//unhide the image
					//hide the leaderboard
				}
				else if(event.data === "Show Leaderboard") {
					console.log("leaderboard");
					//get the leaderboard from the server
					//unhide the leaderboard
					//hide the image
				}
				else {
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
						//update the user field in leaderboard
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
  <body onload="connectToServer();connectToGame();">
  <div class="chatBox"> 
  		<table id="chatHis">
        </table>
        <form onsubmit="return sendMessage()">
            <input id="message" type="text" value="Type something..." name="chat"/>
            <input type="submit"/>
        </form>
  </div>
  	<div class="header" id = "clock"></div>
    	<div id="street-view"></div>
    <div class = "questionBox">
    		<form name="game">
    			<input type="button" class="button" id="button1" name="choice1" onclick="sendChoice1()" value="Paris" />
			<input type="button" class="button" id="button2" name="choice2" onclick="sendChoice2()" value="NYC" />
			<input type="button" class="button" id="button3" name="choice3" onclick="sendChoice3()" value="Los Angeles" />
			<input type="button" class="button" id="button4" name="choice4" onclick="sendChoice4()" value="London" />
		</form>
		<div class="bottom">GeoQ</div>
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
      	startTimer(10, document.getElementById("clock"));
      }
      var samp;
      function startTimer(duration, display) {
    	  	var pro = "2";
    	    var start = Date.now(),
    	        diff,
    	        minutes,
    	        seconds,
    	        hours;
    	    function timer() {
    	        // get the number of seconds that have elapsed since
    	        // startTimer() was called
    	        diff = duration - (((Date.now() - start) / 1000) | 0);

    	        hours = (diff / 3600) | 0;
    	        minutes = ((diff-hours*3600) / 60) | 0;
    	        seconds = ((diff-hours*3600) % 60) | 0;

    	        hours = hours < 10 ? "0" + hours : hours;
    	        minutes = minutes < 10 ? "0" + minutes : minutes;
    	        seconds = seconds < 10 ? "0" + seconds : seconds;

    	        display.innerHTML = seconds;
    	        if(seconds == 0) {
    	        		clearInterval(samp);
    	        }
    	    }
    	    //timer(pro);
    	    timer();
    		//samp = setInterval(function(){timer(samp);}, 1000);
    		samp = setInterval(timer, 1000);
    	}
      

    </script>	
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdAJm9Qb2ncMLsHFZSEzDs5U07qk4jRF4
         &callback=initialize">
    </script>
  </body>
</html>