function signup()
{
    var xhttp = new XMLHttpRequest();
    var username = document.getElementById("un").value;
    var password = sha1(document.getElementById("pw").value);
    var confpass = sha1(document.getElementById("cpw").value);
    xhttp.open("GET", "/GeoQ/LoginValidation?username="+username+"&password="+password+"&confirmpassword="+confpass, false);
    xhttp.send();
    var valid = xhttp.response;
    if(valid === "accept")
    {
        sessionStorage.setItem("username", username);
        window.location.href = "/GeoQ/chat.html";
    }
    else if(valid === "userExists")
    {
        document.getElementById("error").innerHTML = "Error! User already exists!";
    }
    else if(valid === "passNoMatch")
    {
        document.getElementById("error").innerHTML = "Error! Your passwords do not match."
    }

    return false;
}