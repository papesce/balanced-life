define([
        "dojo/_base/declare",
        "dijit/_WidgetBase",
        "dijit/_AttachMixin",
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dijit/layout/TabContainer",
        "dijit/layout/ContentPane",
        "life/TaskGrid",
        "life/HistoryGrid",
        "dojo/text!./templates/centerPane.html"
], function(declare, _WidgetBase,
		_AttachMixin,
		_TemplatedMixin,
		_WidgetsInTemplateMixin,
		TabContainer,
		ContentPane,
		TaskGrid, HistoryGrid,
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

			    var goalsPane = new ContentPane({
			         title: "Goals",
			         content: "goals here"
			    });
			    this._tabContainer.addChild(goalsPane);

			    var taskPane = new ContentPane({
			    	title: "Tasks",
			    });
			    var taskGrid = new TaskGrid();
			    taskPane.set("content", taskGrid);
			    this._tabContainer.addChild(taskPane);

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