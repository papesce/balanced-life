<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<title>Access from mobile</title>
	
	 <!-- stylesheet will go here -->
    
    <!-- dojo/javascript will go here -->
	<script type="text/javascript"
    	src="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dojox/mobile/deviceTheme.js"></script>
	<script type="text/javascript"
   		src="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dojo/dojo.js"></script>
   	
   	 <!-- dojo configuration options -->
   	<script type="text/javascript">
    	dojoConfig = {
        	async: true,
        	parseOnLoad: false
    	};
	</script>	
	
	<!-- dojo application code -->
    <script type="text/javascript">
    require([
 			"dojox/mobile/parser",
 			"dojox/mobile/compat",
 			"dojo/domReady!",
 			"dojox/mobile/View",
 			"dojox/mobile/Heading",
 			"dojox/mobile/RoundRectList",
 			"dojox/mobile/ListItem",
 			"dojox/mobile/Switch",
 			"dojox/mobile/RoundRectCategory"
 		], function (parser) {
 			// now parse the page for widgets
 			parser.parse();
 		});
    </script>
</head>
<body style="visibility:hidden;">
	<!-- application will go here -->
	<!-- The "General" sub-page -->
<div id="general" data-dojo-type="dojox/mobile/View">
    <!-- a sample heading -->
    <h1 data-dojo-type="dojox/mobile/Heading" data-dojo-props="back:'Settings', moveTo:'settings'">General View</h1>
    <!-- a rounded rectangle list container -->
    <ul data-dojo-type="dojox/mobile/RoundRectList">
        <li data-dojo-type="dojox/mobile/ListItem" data-dojo-props="moveTo:'about'">About</li>
        <li data-dojo-type="dojox/mobile/ListItem" data-dojo-props="rightText:'2h 40m', moveTo:'about'">Usage</li>
    </ul>
</div>
<!-- And let's add another view "About" -->
<div id="about" data-dojo-type="dojox/mobile/View">
    <!-- Main view heading -->
    <h1 data-dojo-type="dojox/mobile/Heading" data-dojo-props="back:'General', moveTo:'general'">About</h1>
    <!-- subheading -->
    <h2 data-dojo-type="dojox/mobile/RoundRectCategory">Generic Mobile Device</h2>
    <!-- a rounded rectangle list container -->
    <ul data-dojo-type="dojox/mobile/RoundRectList">
        <li data-dojo-type="dojox/mobile/ListItem" data-dojo-props="rightText:'AcmePhone'">Network</li>
        <li data-dojo-type="dojox/mobile/ListItem" data-dojo-props="rightText:'AcmePhone'">Line</li>
        <li data-dojo-type="dojox/mobile/ListItem" data-dojo-props="rightText:'1024'">Songs</li>
        <li data-dojo-type="dojox/mobile/ListItem" data-dojo-props="rightText:'10'">Videos</li>
    </ul>
</div>
</body>
</html>