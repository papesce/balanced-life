define([ "dojox/mobile/View",
         "dojox/mobile/Heading",
         "dojox/mobile/ScrollableView",
         "life/TaskGrid"
         ], function(View, Heading, ScrollableView
        		 , TaskGrid
        		 ){
	_mainContainer : null;
	_heading : null;
	_scrollableView : null;
	_heading : null;
	_taskGrid: null;
	startup = function() {
		_initializeMainContainer();
	};
	
	_initializeMainContainer = function() {
		this._mainContainer =	new View({id: "mainContainer"}, "mainContainer");
    	
		this._heading = new Heading({fixed: 'top', label: 'Tasks'});
		this._mainContainer.addChild(this._heading);
    	this._scrollableView =	new ScrollableView();
    	this._mainContainer.addChild(this._scrollableView);
		this._taskGrid = new TaskGrid();
	    this._scrollableView.addChild(this._taskGrid);
		this._mainContainer.startup();
		
	};
	 return {
	     init: function() {
	            //startLoading();
	            // register callback for when dependencies have loaded
	            startup();
		 }
	 };
		 
});		