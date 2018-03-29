function loadLeaderboard()
{
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/Leaderboard?gameid=0", false); //Not sure on the gameid system, gonna hardcode it for now
    xhttp.send();
    var results = JSON.parse(xhttp.response);
    var board = document.getElementById("board");
    for(var i = 0; i<results.length; i++)
    {
        board.innerHTML += " <tr class=\"boardRow\"> " +
                            "   <th>"+results[i].username+"</th> " +
                            "   <th>"+results[i].score+"</th> " +
                            "</tr>";
    }
}