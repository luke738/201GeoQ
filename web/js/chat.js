var socket;
var messageHistory = new Array(10);
var historylength = 10;
var username = ""; //Temporary, replace with server session once sign-up/log-in is done

function connectToServer()
{
    for(var i = 0; i<historylength; i++)
    {
        messageHistory[i] = "";
        document.getElementById("chatHis").innerHTML += "<tr class=\"chatRow\"><td class=\"chatMessage\"></td></tr>\n";
    }
    socket = new WebSocket("ws://localhost:8080/Chat"); //Needs to be changed for deployment to a real server
    socket.onopen = function (ev)
    {
        addToHistory("Connected to chat!");
    };
    socket.onmessage = function (ev)
    {
        addToHistory(ev.data);
    };
    socket.onclose = function (ev)
    {
        addToHistory("Disconnected from chat.");
    };
}

function sendMessage()
{
    var message = document.getElementById("message").value;
    //Anything involving username needs to change once logging in works
    if(username == "")
    {
        username = message;
        addToHistory("Your username is now " + username + ".");
    }
    socket.send(username + ": " + message);
    document.getElementById("message").value = "";
    return false;
}

function addToHistory(message)
{
    for(var i = 0; i<historylength-1; i++)
    {
        messageHistory[i] = messageHistory[i+1];
    }
    messageHistory[historylength-1] = message;
    updateMessages();
}

function updateMessages()
{
    var slots = document.getElementsByClassName("chatMessage");
    for(var i = 0; i<historylength; i++)
    {
        slots[i].innerHTML = messageHistory[i];
    }
}