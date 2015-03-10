<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<title>Access from mobile</title>
	
	 <script type="text/javascript">
        var dojoConfig= {isDebug: false, async: true, noGlobals:false,
            packages:[
               	{ name: 'life', location: '${pageContext.request.contextPath}/resources/js/mobile/life'},
            ]
        };
</script>
	
    <!-- dojo/javascript will go here -->
	<script type="text/javascript"
    	src="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dojox/mobile/deviceTheme.js"></script>
	<script type="text/javascript"
   		src="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dojo/dojo.js"></script>
   	
</head>
<body style="visibility:hidden;">

	<div id="mainContainer"></div>
  	<script src="${pageContext.request.contextPath}/resources/js/mobile/launch.js"></script> 
</body>
</html>