function initialize()
{
//    var xhttp = new XMLHttpRequest();
//    var key = "guest"+Math.floor(Math.random()*100000000);
//    xhttp.open("GET", "/GeoQ/Settings?key="+key, false);
//    xhttp.send();
//    var settings = JSON.parse(xhttp.response);
//    sessionStorage.setItem('startTime', settings.startTime);
//    sessionStorage.setItem('loadTime', Date.now());
//    sessionStorage.setItem('timeFromLoad', settings.timeToStart);
//    sessionStorage.setItem('numQuest', settings.numberOfQuestions);
//    sessionStorage.setItem('key', key);
//	  YOU COULD MAYBE MAKE A CALL AND DO THIS BUT LOOK AT SETTINGS SERVLET BEFORE DOING IT
//	  BECAUSE THE SESSION STORAGE MIGHT GET WIPED
	
    var startTime = sessionStorage.getItem('startTime');
    var loadTime = sessionStorage.getItem('loadTime');
    var timeFromLoad = sessionStorage.getItem('timeFromLoad');
    var numQuest = sessionStorage.getItem('numQuest');
    var key = sessionStorage.getItem('key');
    
    document.getElementById("nextGame").innerHTML = "The next game starts at:<br/> " + startTime;
    document.title += " | Next game at " + startTime;
    startTimer(timeFromLoad, document.getElementById("clock"));

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