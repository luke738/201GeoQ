function loadLeaderboard()
{
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/GeoQ/Leaderboard", false);
    xhttp.send();
    var results = JSON.parse(xhttp.response);
    var board = document.getElementById("board");
    board.innerHTML = "";
    for(var i = 0; i<results.length; i++)
    {
        board.innerHTML += " <tr class=\"boardRow\"> " +
                            "   <td>"+results[i].username+" "+results[i].emblems+"</td>" +
                            "   <td>"+results[i].score+" "+results[i].tieSymbol+"</td> " +
                            "</tr>";
    }
}