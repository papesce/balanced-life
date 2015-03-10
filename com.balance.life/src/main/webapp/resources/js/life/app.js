define([ "life/TaskGrid", "dijit/layout/StackContainer"
         ], function(TaskGrid, StackContainer){
	_mainContainer : null; 
	startup = function() {
		_initializeMainContainer();
	};
	
	_initializeMainContainer = function() {
		this._mainContainer = new StackContainer({
	        id: "mainContainer"
	    }, "mainContainer");
		_addTaskGrid();
	    this._mainContainer.startup();
	};
	
	_addTaskGrid = function() {
		var taskGrid = new TaskGrid();
		this._mainContainer.addChild(taskGrid);
	};
	
	 return {
	     init: function() {
	            //startLoading();
	            // register callback for when dependencies have loaded
	            startup();
		 }
	 };
		 
});		