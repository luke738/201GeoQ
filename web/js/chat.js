var socketChat;
var historylength = 23;
var messageHistory = new Array(historylength);


function connectToServer()
{
    for(var i = 0; i<historylength; i++)
    {
        messageHistory[i] = "&nbsp;";
        document.getElementById("chatHis").innerHTML += "<tr class=\"chatRow\"><td class=\"chatMessage\">&nbsp;</td></tr>\n";
    }

    socketChat = new WebSocket("ws://localhost:8080/GeoQ/Chat"); //Needs to be changed for deployment to a real server
    socketChat.onopen = function (ev)
    {
        addToHistory("Connected to chat!");
    };
    socketChat.onmessage = function (ev)
    {
        addToHistory(ev.data);
    };
    socketChat.onclose = function (ev)
    {
        addToHistory("Disconnected from chat.");
    };
}

function sendMessage()
{
    var message = document.getElementById("message").value;
    socketChat.send(message);
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