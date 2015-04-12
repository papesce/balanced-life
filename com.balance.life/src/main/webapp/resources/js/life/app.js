define([ "life/TaskGrid",
         "dijit/layout/StackContainer",
         "dijit/layout/BorderContainer",
         "dijit/layout/ContentPane",
         ], function(TaskGrid, StackContainer, BorderContainer, ContentPane){
	_mainContainer : null; 
	_topContentPane: null;
	_centerContentPane: null;
	startup = function() {
		_initializeMainContainer();
	};
	
	_initializeMainContainer = function() {
		this._mainContainer = new BorderContainer({
	        id: "mainContainer",
	        style: "height: 100%; width: 100%;"
	    }, "mainContainer");
		_addTitleBar();
		_addTaskGrid();
	    this._mainContainer.startup();
	};
	_addTitleBar = function() {
		this._topContentPane= new ContentPane({
			region: "top",
			"class" : "balancedTopPanel",
			content: "Balanced Life v0.3"
			});
		this._mainContainer.addChild(this._topContentPane);
	},
	_addTaskGrid = function() {
		this._centerContentPane = new ContentPane({
			region: "center",
		});
		var taskGrid = new TaskGrid();
		this._centerContentPane.addChild(taskGrid);
		this._mainContainer.addChild(this._centerContentPane);
	};
	
	 return {
	     init: function() {
	            //startLoading();
	            // register callback for when dependencies have loaded
	            startup();
		 }
	 };
		 
});		