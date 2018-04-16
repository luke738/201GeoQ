var panorama;
var socket;
var choice;
var samp;

function initialize() {
    panorama = new google.maps.StreetViewPanorama(
        document.getElementById('street-view'),
        
        {
          position: {lat: 37.869260, lng: -122.254811},
          pov: {heading: 165, pitch: 0},
          zoom: 1,
          disableDefaultUI: true,
          clickToGo: false,
          showRoadLabels: false,
          addressControl: false,
          fullscreenControl: false
        });
    startTimer(10, document.getElementById("clock"));
}

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

function connectToGame() 
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
			document.getElementById("button1").style.background = "#Ff8784";
			document.getElementById("button2").style.background = "#Ff8784";
			document.getElementById("button3").style.background = "#Ff8784";
			document.getElementById("button4").style.background = "#Ff8784";
			hideLeaderboard();
		}
		else if(event.data === "Show Leaderboard") {
			console.log("leaderboard");
			showLeaderboard();
		}
		else {
			console.log("2" + event.data);
			console.log(document.getElementById("button1").value);
			if(event.data === document.getElementById("button1").value) {
				document.getElementById("button1").style.background = "#e0ddc5";
				document.getElementById("button2").style.background = "#bab9b4";
				document.getElementById("button3").style.background = "#bab9b4";
				document.getElementById("button4").style.background = "#bab9b4";
			}
			else if(event.data === document.getElementById("button2").value) {
				document.getElementById("button1").style.background = "#bab9b4";
				document.getElementById("button2").style.background = "#e0ddc5";
				document.getElementById("button3").style.background = "#bab9b4";
				document.getElementById("button4").style.background = "#bab9b4";
			}
			else if(event.data === document.getElementById("button3").value) {
				document.getElementById("button1").style.background = "#bab9b4";
				document.getElementById("button2").style.background = "#bab9b4";
				document.getElementById("button3").style.background = "#e0ddc5";
				document.getElementById("button4").style.background = "#bab9b4";
			}
			else {
				document.getElementById("button1").style.background = "#bab9b4";
				document.getElementById("button2").style.background = "#bab9b4";
				document.getElementById("button3").style.background = "#bab9b4";
				document.getElementById("button4").style.background = "#e0ddc5";
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
	
	document.getElementById("button1").style.background = "white";
	document.getElementById("button2").style.background = "#Ff8784";
	document.getElementById("button3").style.background = "#Ff8784";
	document.getElementById("button4").style.background = "#Ff8784";
	
	return false;
}

function sendChoice2() {
	choice = document.game.choice2.value;
	
	document.getElementById("button1").style.background = "#Ff8784";
	document.getElementById("button2").style.background = "white";
	document.getElementById("button3").style.background = "#Ff8784";
	document.getElementById("button4").style.background = "#Ff8784";
	
	return false;
}

function sendChoice3() {
	choice = document.game.choice3.value;
	
	document.getElementById("button1").style.background = "#Ff8784";
	document.getElementById("button2").style.background = "#Ff8784";
	document.getElementById("button3").style.background = "white";
	document.getElementById("button4").style.background = "#Ff8784";
	
	return false;
}

function sendChoice4() {
	choice = document.game.choice4.value;
	
	document.getElementById("button1").style.background = "#Ff8784";
	document.getElementById("button2").style.background = "#Ff8784";
	document.getElementById("button3").style.background = "#Ff8784";
	document.getElementById("button4").style.background = "white";
	
	return false;
}

function showLeaderboard() {
	document.getElementById("leaderboard").style.animation = "fadeIn 1s";	
	document.getElementById("street-view").style.blur = "5px";
	
}

function hideLeaderboard() {
	document.getElementById("leaderboard").style.animation = "fadeOut 1s";
	document.getElementById("street-view").style.blur = "0px";
}

