function initialize()
{
    var startTime = sessionStorage.getItem('startTime');
    var loadTime = sessionStorage.getItem('loadTime');
    var timeFromLoad = sessionStorage.getItem('timeFromLoad');
    var numQuest = sessionStorage.getItem('numQuest');
    var key = sessionStorage.getItem('key');
    
    document.getElementById("nextGame").innerHTML = "The next game starts at:<br/> " + startTime;
    document.title += " | Next game at " + startTime;

    startTimer(timeFromLoad-(Date.now()-loadTime), document.getElementById("clock"));

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "GeoQ/LobbyPull", true);
    xhttp.onreadystatechange = function(ev) {
        if(ev.data = "go") window.location.href = "/GeoQ/QuestionPage.html";
        else xhttp.open("GET", "GeoQ/LobbyPull", true);
    }
}

function startTimer(duration, display) {
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

        display.innerHTML = hours + ":" + minutes + ":" + seconds;
        if(hours == 0 && minutes == 0 && seconds == 0)
        {
        		window.location.href = "/GeoQ/QuestionPage.html";
        }

//        if (diff <= 0) {
//            // add one second so that the count down starts at the full duration
//            start = Date.now() + 1000;
//        }
    }
    timer();
    setInterval(timer, 1000);
}