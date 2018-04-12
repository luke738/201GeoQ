<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="QuestionPage.css">
    <link href="https://fonts.googleapis.com/css?family=IBM+Plex+Mono" rel="stylesheet">
    <title>Street View</title>
  </head>
  <body>
  <div class="chatBox"> <span>Insert chatbox here</span></div>
    <div id="street-view"></div>
    <div class = "questionBox">
    		<div class="button">Paris</div>
    		<div class="button">NYC</div>
    		<div class="button">Los Angeles</div>
    		<div class="button">London</div>
    </div>
    <script>
      var panorama;
      function initialize() {
        panorama = new google.maps.StreetViewPanorama(
            document.getElementById('street-view'),
            
            {
              position: {lat: 37.869260, lng: -122.254811},
              pov: {heading: 165, pitch: 0},
              zoom: 1
            });
      
      }
      

    </script>	
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdAJm9Qb2ncMLsHFZSEzDs5U07qk4jRF4
         &callback=initialize">
    </script>
  </body>
</html>