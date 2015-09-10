<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <html ng-app="app">
     <head>
	 <!-- bower:css -->
	 <link rel="stylesheet" href="resources/bower_components/angular-ui-grid/ui-grid.css" />
	 <link rel="stylesheet" href="resources/bower_components/bootstrap/dist/css/bootstrap.css" />
	 <!-- endbower -->
	  <link rel="stylesheet" href="resources/css/main.css" type="text/css">
     </head>
<body>
 <!-- boostrap navbar -->
    <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Balanced Gym</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
      </ul>
    </div>
  </div>
</nav>
<!--  	<div>
     <label>Name:</label>
        <input type="text" ng-model="yourName" placeholder="Enter a name here">
        <hr>
       <h1>Hello {{yourName}}!</h1>
    </div>
 -->
     <div ng-controller="MainCtrl">
     	<div id="grid1" ui-grid="{ data: myData }" class="grid"></div>
     </div>
     
     <!-- bower:js -->
     <script src="resources/bower_components/jquery/dist/jquery.js"></script>
     <script src="resources/bower_components/angular/angular.js"></script>
     <script src="resources/bower_components/angular-animate/angular-animate.js"></script>
     <script src="resources/bower_components/angular-ui-grid/ui-grid.js"></script>
     <script src="resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
     <!-- endbower -->
	 <script src="resources/js/app.js"></script>
   </body>
</html>

