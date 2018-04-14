function initialize()
{
    sessionStorage.clear();
    var xhttp = new XMLHttpRequest();
    var key = Math.floor(Math.random()*100000000);
    xhttp.open("GET", "/GeoQ/Settings?key="+key, false);
    xhttp.send();
    var settings = JSON.parse(xhttp.response);
    sessionStorage.setItem('startTime', settings.startTime);
    sessionStorage.setItem('loadTime', Date.now());
    sessionStorage.setItem('timeFromLoad', settings.timeToStart);
    sessionStorage.setItem('numQuest', settings.numberOfQuestions);
    sessionStorage.setItem('key', key);

    document.getElementById("nextGame").innerHTML = "The next game starts at:<br/> " + settings.startTime;
    document.title += " | Next game at " + settings.startTime;
    //startTimer(settings.timeToStart, document.getElementById("clock"));

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
            display.innerHTML = "Now!";
        }

        if (diff <= 0) {
            // add one second so that the count down starts at the full duration
            start = Date.now() + 1000;
        }
    }
    timer();
    setInterval(timer, 1000);
}