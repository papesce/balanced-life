define([
        "dojo/_base/declare",
        "dijit/_WidgetBase",
        "dijit/_AttachMixin",
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dijit/layout/TabContainer",
        "dijit/layout/ContentPane",
        "life/ListGrid",
        "life/HistoryGrid",
        "life/TreeGrid",
        "dojo/text!./templates/centerPane.html"
], function(declare, _WidgetBase,
		_AttachMixin,
		_TemplatedMixin,
		_WidgetsInTemplateMixin,
		TabContainer,
		ContentPane,
		ListGrid, HistoryGrid, TreeGrid,
		template) {
	
	return declare([_WidgetBase, _AttachMixin, _TemplatedMixin, _WidgetsInTemplateMixin
	                ], {
        templateString: template,
        
		constructor : function() {
      	  this.inherited(arguments);
      	  this.id = "balancedLifeCenterPane";
		},
      
      postCreate: function(){
      	// Run any parent postCreate processes - can be done at any point
			this.inherited(arguments);
			
			 this._tabContainer = new TabContainer({
			        style: "height: 100%; width: 100%;"
			    }, this.balancedCenterTabContainerDiv);


			    var listPane = new ContentPane({
			    	title: "List View",
			    });
			    var listGrid = new ListGrid();
			    listPane.set("content", listGrid);
			    this._tabContainer.addChild(listPane);

			    var treePane = new ContentPane({
			         title: "Tree View",
			    });
			    var treeGrid = new TreeGrid();
			    treePane.set("content", treeGrid);
			    this._tabContainer.addChild(treePane);

			    
			    var historyPane = new ContentPane({
			         title: "History",
			    });
			    var historyGrid = new HistoryGrid();
			    historyPane.set("content", historyGrid);
			    this._tabContainer.addChild(historyPane);
			    


      },
      startup: function() {
    	  this.inherited(arguments);
    	  this._tabContainer.startup();
      },
      resize: function(){
     	 this.inherited(arguments);
     	 this._tabContainer.resize();
     },
      
   
});

});