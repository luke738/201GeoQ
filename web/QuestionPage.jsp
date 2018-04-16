 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/QuestionPage.css">
    <link rel"stylesheet" href="">
    <link href="https://fonts.googleapis.com/css?family=IBM+Plex+Mono" rel="stylesheet">
    <title>Street View</title>
    <script src="js/chat.js"></script>
    <script src="js/leaderboard.js"></script>
    <script src="js/game.js"></script>
  </head>
  <body onload="connectToServer();connectToGame();loadLeaderboad()">
  	<div class="header" id = "clock"></div>
  	<div id="wrapper">
	  	<div id="container">
	  		<div id="street-view"></div>
	    		<div id="leaderboard">
	    			<h1>Leaderboard</h1>
		        <table id="board">
		            <tr class="boardHeader">
		                <th>Username</th>
		                <th>Score</th>
		            </tr>
		        </table>
	    		</div>
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
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdAJm9Qb2ncMLsHFZSEzDs5U07qk4jRF4
         &callback=initialize">
    </script>
  </body>
</html>