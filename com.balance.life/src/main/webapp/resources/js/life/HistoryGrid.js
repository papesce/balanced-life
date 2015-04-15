define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_AttachMixin",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dgrid/OnDemandGrid",
    "dgrid/Keyboard",
    "dgrid/Selection",
    "dgrid/extensions/DijitRegistry",
    "dgrid/extensions/ColumnResizer", 
    "dgrid/Editor",  
    "dstore/Rest",
    "dstore/SimpleQuery",
    'dstore/Trackable',
    "dstore/Cache",
    "dstore/Memory",
    "dojo/text!./templates/historyGrid.html"
], function(declare, _WidgetBase, 
		_AttachMixin,
		_TemplatedMixin,
		_WidgetsInTemplateMixin, 
		OnDemandGrid,
		Keyboard,
		Selection,
		DijitRegistry,
		ColumnResizer,
		Editor,
		Rest,
		SimpleQuery,
		Trackable,
		Cache,
		Memory,
		template) {

	return declare([_WidgetBase, _AttachMixin, _TemplatedMixin, _WidgetsInTemplateMixin
	                ], {
        templateString: template,
        
        _grid : null,
        _gridSelection: {},
        _store: null,
        
		constructor : function() {
        	  this.inherited(arguments);
        	  this.id = "balancedLifeHistoryGrid";
        },
        
        postCreate: function(){
        	// Run any parent postCreate processes - can be done at any point
			this.inherited(arguments);
			//this._initButtons(arguments);
			this._initGrid(arguments);
        },
        
        startup: function() {
        	  this.inherited(arguments);
        	  this._grid.startup();
        },
       
        _initGrid : function(arguments) {
        	
        	this._restStore = new declare([Rest, SimpleQuery, Trackable])({
     		   target: 'rest/taskHistory/',
     		   useRangeHeaders: true,
    				idProperty: "taskId"
         	});
    	   
         	var cachedStore = Cache.create(this._restStore, {
         		cachingStore: new Memory()
         	});
         	this._store = cachedStore;
        	
         	var myColumns = [
          	                  { label: "Name", field: "name"
          	                	  //, editor: "text" , autoSave: true,
          	                	//editOn : "dblclick", autoSelect : true 
          	                	},
          	                  //{ label: "Tags", field: "tagString", 
          	                //		editor: "text",
       							//formatter: lang.hitch(this, this._formatTags),
       						//	get: lang.hitch(this, this._formatTags),
       						//	set: lang.hitch(this, this._editTags),
       						//	autoSave: true, editOn : "dblclick"
          	                //			},
          	   					
          	   				];
        	
        	 this._grid = new (declare([OnDemandGrid, Keyboard, Selection,  DijitRegistry, ColumnResizer, Editor]))({
	    			collection: 	this._store,
		   			selectionMode: "toggle",    
//		   			allowSelectAll: true,
		   			getBeforePut: false,
		   			columns: myColumns,
 	            loadingMessage: "<span class='tt2pmpGridLoading'>Loading history...</span>",
 	            noDataMessage: "No tasks found."
 	            
 	        }, this.balancedHistoryGridDiv);
        	
        },
        resize: function(){
       	 this.inherited(arguments);
       	 this._grid.resize();
       },

        
    });
 
});