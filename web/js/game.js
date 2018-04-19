var panorama;
var socket;
var samp;
var question;
var first;

function startPage() {
//    panorama = new google.maps.StreetViewPanorama(
//        document.getElementById('street-view'),
//        
//        {
//          position: {lat: question.latitude, lng: question.longitude},
//          pov: {heading: question.heading, pitch: question.pitch},
//          zoom: 1,
//          disableDefaultUI: true,
//          clickToGo: false,
//          showRoadLabels: false,
//          addressControl: false,
//          fullscreenControl: false
//        });
//    startTimer(10, document.getElementById("clock"));
}

function initialize() {
	console.log(question.latitude);
	console.log(question.longitude);
	console.log(question.heading);
	console.log(question.pitch);
	console.log(question.correctAnswerString);
    panorama = new google.maps.StreetViewPanorama(
        document.getElementById('street-view'),
        
        {
          position: {lat: question.latitude, lng: question.longitude},
          pov: {heading: question.heading, pitch: question.pitch},
          zoom: 1,
          disableDefaultUI: true,
          clickToGo: false,
          showRoadLabels: false,
          addressControl: false,
          fullscreenControl: false
        });
    startTimer(sessionStorage.getItem('questionTime'), document.getElementById("clock"));
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
	first = true;
	//overwriting the function in javascript
	socket.onopen = function(event) {
		//document.getElementById("game").innerHTML += "Connected<br />";
	};
	
	socket.onmessage = function(event) 
	{
		// show leaderboard, highlight correct/incorrect answers
		if(event.data === "Show Leaderboard") {
			showLeaderboard();
			if(question.correctAnswerString === document.getElementById("button1").value) {
				document.getElementById("button1").style.background = "#28AF6E";
				document.getElementById("button2").style.background = "#bab9b4";
				document.getElementById("button3").style.background = "#bab9b4";
				document.getElementById("button4").style.background = "#bab9b4";
			}
			else if(question.correctAnswerString === document.getElementById("button2").value) {
				document.getElementById("button1").style.background = "#bab9b4";
				document.getElementById("button2").style.background = "#28AF6E";
				document.getElementById("button3").style.background = "#bab9b4";
				document.getElementById("button4").style.background = "#bab9b4";
			}
			else if(question.correctAnswerString === document.getElementById("button3").value) {
				document.getElementById("button1").style.background = "#bab9b4";
				document.getElementById("button2").style.background = "#bab9b4";
				document.getElementById("button3").style.background = "#28AF6E";
				document.getElementById("button4").style.background = "#bab9b4";
			}
			else {
				document.getElementById("button1").style.background = "#bab9b4";
				document.getElementById("button2").style.background = "#bab9b4";
				document.getElementById("button3").style.background = "#bab9b4";
				document.getElementById("button4").style.background = "#28AF6E";
			}

			document.getElementById("button1").disabled = true;
			document.getElementById("button2").disabled = true;
			document.getElementById("button3").disabled = true;
			document.getElementById("button4").disabled = true;
			document.getElementById("button1").style.color = "black";
			document.getElementById("button2").style.color = "black";
			document.getElementById("button3").style.color = "black";
			document.getElementById("button4").style.color = "black";

			// answer checking
			
		}
		// change to the next street view image
		else if(event.data === "End Game") {
		}
		else {
			question = JSON.parse(event.data);

			document.getElementById("button1").disabled = false;
			document.getElementById("button2").disabled = false;
			document.getElementById("button3").disabled = false;
			document.getElementById("button4").disabled = false;

			document.getElementById("button1").style.background = "#bab9b4";
			document.getElementById("button2").style.background = "#bab9b4";
			document.getElementById("button3").style.background = "#bab9b4";
			document.getElementById("button4").style.background = "#bab9b4";
			
			document.getElementById("button1").value = question.answers[0];
			document.getElementById("button2").value = question.answers[1];
			document.getElementById("button3").value = question.answers[2];
			document.getElementById("button4").value = question.answers[3];
			// get new street view image
			initialize();
			// change button values
			if(first) {
				first = false;
			}
			else {
				hideLeaderboard();
			}
		}
	}
	
	socket.onclose = function(event) {
		//document.getElementById("mychat").innerHTML += "Disconnected<br />";					
	}
}

function sendChoice1() {
	document.getElementById("button1").style.background = "#A2E8DD";
	document.getElementById("button2").style.background = "#bab9b4";
	document.getElementById("button3").style.background = "#bab9b4";
	document.getElementById("button4").style.background = "#bab9b4";
	socket.send("0");
	return false;
}

function sendChoice2() {
	document.getElementById("button1").style.background = "#bab9b4";
	document.getElementById("button2").style.background = "#A2E8DD";
	document.getElementById("button3").style.background = "#bab9b4";
	document.getElementById("button4").style.background = "#bab9b4";
	socket.send("1");
	return false;
}

function sendChoice3() {
	document.getElementById("button1").style.background = "#bab9b4";
	document.getElementById("button2").style.background = "#bab9b4";
	document.getElementById("button3").style.background = "#A2E8DD";
	document.getElementById("button4").style.background = "#bab9b4";
	socket.send("2");
	return false;
}

function sendChoice4() {
	document.getElementById("button1").style.background = "#bab9b4";
	document.getElementById("button2").style.background = "#bab9b4";
	document.getElementById("button3").style.background = "#bab9b4";
	document.getElementById("button4").style.background = "#A2E8DD";
	socket.send("3");
	return false;
}

function showLeaderboard() {
	loadLeaderboard();
	document.getElementById("leaderboard").style.animation = "fadeIn 1s forwards";	
	document.getElementById("street-view").style.filter = "blur(5px)";
	
}

function hideLeaderboard() {
	document.getElementById("leaderboard").style.animation = "fadeOut 1s forwards";
	document.getElementById("street-view").style.filter = "blur(0)";
}

