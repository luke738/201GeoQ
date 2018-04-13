function initialize()
{
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/GeoQ/Settings", false);
    xhttp.send();
    var settings = JSON.parse(xhttp.response);
    sessionStorage.setItem('startTime', settings.startTime);
    sessionStorage.setItem('timeToStart', settings.timeToStart);
    sessionStorage.setItem('numQuest', settings.numQuest);

}