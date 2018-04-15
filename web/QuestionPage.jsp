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
    <script src="js/game.js"></script>
  </head>
  <body onload="connectToServer();connectToGame();">
<<<<<<< HEAD
<<<<<<< HEAD
  <div class="chatBox"> 
  		<table id="chatHis" class="finder2">
        </table>
        <form onsubmit="return sendMessage()"class="finder">
            <input id="message" type="text" placeholder="Type something..." name="chat"/>
 			<input class="myButton" type ="Submit" value = "Send"></input>
        </form>
  </div>
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
      
      }
      

    </script>	
=======
=======
>>>>>>> 20fece7e24bec6e7bfeaf3e2d5fe1b89f462c8ab
  	<div id="wrapper">
	  	<div id="container">
	  		<div id="street-view"></div>
	    		<div id="leaderboard"></div>
	  	</div>
	  	<div class = "questionBox">
	    		<form name="game">
	    			<input type="button" class="button" id="button1" name="choice1" onclick="sendChoice1()" value="Paris" />
				<input type="button" class="button" id="button2" name="choice2" onclick="sendChoice2()" value="NYC" />
				<input type="button" class="button" id="button3" name="choice3" onclick="sendChoice3()" value="Los Angeles" />
				<input type="button" class="button" id="button4" name="choice4" onclick="sendChoice4()" value="London" />
			</form>
			<div class="bottom">GeoQ</div>
    		</div>
  	</div>
  	
  	<div class="chatBox"> 
  		<div id="messages">
	  		<table id="chatHis">
	        </table>
  		</div>
        <div id="submit">
	        <form onsubmit="return sendMessage()"class="finder">
	            <input id="message" type="text" placeholder="Type something..." name="chat"/>
	 			<input class="myButton" type ="Submit" value = "Send"></input>
	        </form>
        </div>
 	</div>
<<<<<<< HEAD
>>>>>>> 20fece7e24bec6e7bfeaf3e2d5fe1b89f462c8ab
=======
>>>>>>> 20fece7e24bec6e7bfeaf3e2d5fe1b89f462c8ab
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdAJm9Qb2ncMLsHFZSEzDs5U07qk4jRF4
         &callback=initialize">
    </script>
  </body>
</html>