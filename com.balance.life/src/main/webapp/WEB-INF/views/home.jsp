<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/life/css/custom.css" />
<!-- <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dojo/resources/dojo.css"> -->
<link rel="stylesheet" href="/com.balance.dojo/dojo/resources/dojo.css">
<!-- <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dijit/themes/claro/claro.css">    -->
<link rel="stylesheet" href="/com.balance.dojo/dijit/themes/claro/claro.css"> 
<!-- <link rel="stylesheet" href="//cdn.rawgit.com/SitePen/dgrid/v0.4.0/css/dgrid.css"> -->
 <link rel="stylesheet" href="/com.balance.dojo/dgrid/css/dgrid.css">
<!-- <link rel="stylesheet" href="//cdn.rawgit.com/SitePen/dgrid/v0.4.0/css/skins/claro.css"> -->
 <link rel="stylesheet" href="/com.balance.dojo/dgrid/css/skins/claro.css">

 <script type="text/javascript">
        var dojoConfig= {isDebug: false, async: true, noGlobals:false, baseUrl: '/com.balance.dojo/',
            packages:[
				{ name: 'dojo', location: 'dojo'},
				{ name: 'dijit', location: 'dijit'},
				{ name: 'dojox', location: 'dojox'},
               //	{ name: 'dgrid', location: '//cdn.rawgit.com/SitePen/dgrid/v0.4.0'},
               { name: 'dgrid', location: 'dgrid'},
               // { name: 'dstore',  location: '//cdn.rawgit.com/SitePen/dstore/master'},
               { name: 'dstore',  location: 'dstore'},
               //	{ name: 'xstyle', location: '//cdn.rawgit.com/kriszyp/xstyle/v0.2.1'},
               { name: 'xstyle', location: 'xstyle'},
               //	{ name: 'put-selector', location: '//cdn.rawgit.com/kriszyp/put-selector/v0.3.5'},
               { name: 'put-selector', location: 'put-selector'},
               //	{ name: 'life', location: '${pageContext.request.contextPath}/resources/js/life'},
               { name: 'life', location: '${pageContext.request.contextPath}/resources/js/life'},
               
            ]
        };
</script>

  <script type="text/javascript" src="/com.balance.dojo/dojo/dojo.js"></script> 
<!-- <script src="//ajax.googleapis.com/ajax/libs/dojo/1.10.3/dojo/dojo.js" 
		data-dojo-config="async: true"></script> -->

<html>
<head>
	<title>Home</title>
</head>
<body class="claro">

<P>  The time on the server is ${serverTime}. </P>


    <h1 id="greeting">Welcome to Balanced Life v0.1</h1>
    
    <div id="mainContainer"></div>
      
    <script src="${pageContext.request.contextPath}/resources/js/launch.js"></script>   
    
</body>
</html>
