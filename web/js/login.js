function validate()
{
    var xhttp = new XMLHttpRequest();
    var username = document.getElementById("un").value;
    var password = sha1(document.getElementById("pw").value);
    xhttp.open("GET", "/GeoQ/LoginValidation?username="+username+"&password="+password, false);
    xhttp.send();
    var valid = JSON.parse(xhttp.response);
    if(valid)
    {
        sessionStorage.setItem("username", username);
        window.location.href = "/GeoQ/chat.html";
    }
    else
    {
        document.getElementById("error").innerHTML = "Error! Invalid username or password!";
    }

    return false;
}