define([ "dojox/mobile/View",
         "dojox/mobile/Heading",
         "dojox/mobile/ScrollableView",
         "life/ItemGrid"
         ], function(View, Heading, ScrollableView
        		 , ItemGrid
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
    	
		this._heading = new Heading({fixed: 'top', label: 'Items'});
		this._mainContainer.addChild(this._heading);
    	this._scrollableView =	new ScrollableView();
    	this._mainContainer.addChild(this._scrollableView);
		this._itemGrid = new ItemGrid();
	    this._scrollableView.addChild(this._itemGrid);
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