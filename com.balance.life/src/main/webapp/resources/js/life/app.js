define([ "life/CenterPane",
         "dijit/layout/StackContainer",
         "dijit/layout/BorderContainer",
         "dijit/layout/ContentPane",
         ], function(CenterPane, StackContainer, BorderContainer, ContentPane){
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
		_addCenterPane();
	    this._mainContainer.startup();
	};
	_addTitleBar = function() {
		this._topContentPane= new ContentPane({
			region: "top",
			"class" : "balancedTopPanel",
			content: "Balanced Life v0.6 (8/26/2015)"
			});
		this._mainContainer.addChild(this._topContentPane);
	},
	_addCenterPane = function() {
		this._centerContentPane = new ContentPane({
			region: "center",
		});
		var centerPane = new CenterPane();
		this._centerContentPane.addChild(centerPane);
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