   <html ng-app>
     <head>
       <!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script> -->
       <!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
         
	 <!-- bower:css -->
	 <link rel="stylesheet" href="../../resources/bower_components/angular-ui-grid/ui-grid.css" />
	 <link rel="stylesheet" href="../../resources/bower_components/bootstrap/dist/css/bootstrap.css" />
	 <!-- endbower -->
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
     
     
      <div>
        <label>Name:</label>
        <input type="text" ng-model="yourName" placeholder="Enter a name here">
        <hr>
       <h1>Hello {{yourName}}!</h1>
     </div>
     <!-- bower:js -->
     <script src="../../resources/bower_components/jquery/dist/jquery.js"></script>
     <script src="../../resources/bower_components/angular/angular.js"></script>
     <script src="../../resources/bower_components/angular-ui-grid/ui-grid.js"></script>
     <script src="../../resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
     <!-- endbower -->
     
   </body>
</html>

