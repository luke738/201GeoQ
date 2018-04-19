function setting()
{
    var xhttp = new XMLHttpRequest();
    var password = sha1(document.getElementById("password").value);
    var startDay = document.getElementById("startDay").value;
    var startHour = document.getElementById("startHrs").value;
    var startMin = document.getElementById("startMin").value;
    var timeBetweenGames = document.getElementById("tBet").value;
    var numQuestions = document.getElementById("numQ").value;
    var questionTime = document.getElementById("qTime").value;
    var leaderboardTime = document.getElementById("lTime").value;
    var startNow = document.getElementById("startNow").checked;
    var startTime = Math.floor(Date.now()/1000)-(Math.floor(Date.now()/1000)%86400)+startDay*86400+(startHour*1+7)*3600+startMin*60;
    if(Math.floor(Date.now()/1000)%86400<3600*7) startTime-=86400;
    console.log(Date.now());
    console.log(startTime);
    var query = "password="+password+"&startTime="+startTime+"&timeBetweenGame="+timeBetweenGames+"&numQuestions="+numQuestions;
    query += "&questionTime="+questionTime+"&leaderboardTime="+leaderboardTime+"&startNow="+startNow;
    xhttp.open("GET", "/GeoQ/Management?"+query, false);
    xhttp.send();
    var valid = xhttp.response;
    if(valid)
    {
        document.getElementById("error").innerHTML = "Changes received."
    }
    else
    {
        document.getElementById("error").innerHTML = "Failure."
    }

    return false;
}