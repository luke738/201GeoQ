<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://fonts.googleapis.com/css?family=IBM+Plex+Mono" rel="stylesheet">
		<title>201 Project </title>
		<style>
		* { box-sizing: border-box; 
			font-family: 'IBM Plex Mono', monospace;
		}
		.video-background {
		  background: #000;
		  position: fixed;
		  top: 0; right: 0; bottom: 0; left: 0;
		  z-index: -99;
		  width: 120%;
		}
		.video-foreground,.video-background iframe {
		  position: fixed;
		  top: -20%;
		  left: 0;
		  width: 110%;
		  height: 140%;
		  pointer-events: none;
		}
		#vidtop-content {
			top: 0;
			color: #fff;
		}
		html, body {
			margin: 0;
			padding: 0;
			/*make this a clear glossy white*/
			
			opacity: 1;
			/*background-image: url("201_FinalProject/gif.gif");*/
			background-repeat-x: no-repeat;
			background-repeat-y: no-repeat;
			text-align: center;
		}
		html{ 
				background-size: auto 132%;
		}
		#header{
			text-align: center;
			font-size: 65px;
			margin-top: 50px;
			color: white;

		}
		.buttonContainer{
			flex: wrap;
			margin: auto;
			width: 6.5%;
			margin-top: 350px;
		}
		.buttons{
			width: 120%;
			height: 40px;
			background: #E8E8E8;
			margin: 10px;
			flex-direction: column;
			text-align: center;
			font-weight: bold;

		}
		.buttons:hover {
			cursor: pointer;
		}
		.buttons span{
			position: relative;
			top: 10px;
			font-size: 18px;
		}
		.buttons:hover{
			opacity: .7;
			transform: scale(1.1);
		}
		a{
			text-decoration: none;
			color: black; !important
		}

		</style>
	</head>
	<body>
		<div class="video-background">
    			<div class="video-foreground">
      				<iframe src="https://www.youtube.com/embed/L14nXRxJILg?controls=0&showinfo=0&rel=0&autoplay=1&loop=1&playlist=W0LHTWG-UmQ" frameborder="0" allowfullscreen></iframe>
    		</div>
  		</div>
		<div id="header">Welcome to GeoQ</div>
			<div class = "buttonContainer">			
				<div class="buttons" >
					<span><a href="login.jsp">Login</a></span>
				</div>
				<div class="buttons">
					<span><a href="signup.jsp">Signup</a></span>
						
				</div>

				<div class="buttons">
					<span>Guest</span>
						
				</div>

			</div>

		

	</body>
</html>