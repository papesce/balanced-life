define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_AttachMixin",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/form/Button",
    "dgrid/OnDemandGrid",
    "dgrid/Keyboard",
    "dgrid/Selection",
    "dgrid/extensions/DijitRegistry",
    "dgrid/extensions/ColumnResizer", 
    "dgrid/Editor",  
    "dgrid/Tree",
    "dstore/Rest",
    "dstore/Tree",
    "dstore/SimpleQuery",
    'dstore/Trackable',
    "dstore/Cache",
    "dstore/Memory",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dojo/text!./templates/treeGrid.html"
], function(declare, _WidgetBase, 
		_AttachMixin,
		_TemplatedMixin,
		_WidgetsInTemplateMixin, 
		Button,
		OnDemandGrid,
		Keyboard,
		Selection,
		DijitRegistry,
		ColumnResizer,
		Editor, Tree, 
		Rest,
		SimpleQuery,
		DTree,
		Trackable,
		Cache,
		Memory, 
		array,
		lang,
		template) {

	return declare([_WidgetBase, _AttachMixin, _TemplatedMixin, _WidgetsInTemplateMixin
	                ], {
        templateString: template,
        
        _grid : null,
        _gridSelection: {},
        _store: null,
        
		constructor : function() {
        	  this.inherited(arguments);
        	  this.id = "balancedLifeTreeGrid";
        },
        
        postCreate: function(){
        	// Run any parent postCreate processes - can be done at any point
			this.inherited(arguments);
			this._initButtons(arguments);
			this._initGrid(arguments);
        },
        _initButtons : function(arguments) {
        	this._refreshButton = new Button({
        	label: "Refresh",
   	        onClick: lang.hitch(this, this._refreshClick)
   	    }, this.refreshButtonDiv);

    	},
    	_refreshClick: function() {
    			this._grid.refresh();
    	},
        startup: function() {
        	  this.inherited(arguments);
        	  this._grid.startup();
        },
       
        _initGrid : function(arguments) {
        	
        	this._restStore = new declare([Rest, SimpleQuery, Trackable, DTree])({
     		   target: 'rest/tree/',
//     			mayHaveChildren: function(parent){
//    				return true;
//   			},
     			useRangeHeaders: true,
    			idProperty: "itemId"
         	});
    	   
         	var cachedStore = Cache.create(this._restStore, {
         		cachingStore: new Memory()
         	});
         	this._store = cachedStore;
        	
         	var myColumns = [
         	                  { label: "ID", field: "itemId"},
          	                  {renderExpando: true, label: "Name", field: "name"
          	                	  , editor: "text" , autoSave: true,
          	                	editOn : "dblclick", autoSelect : true 
          	                	},
             	               { label: "Tags", field: "tagString", 
        						get: lang.hitch(this, this._formatTags) 
             	               }

          	                ];
        	
        	 this._grid = new (declare([OnDemandGrid, Keyboard, Selection,  DijitRegistry, ColumnResizer,
        	                            Editor, Tree]))({
	    			collection: 	this._store,
		   			selectionMode: "toggle",    
//		   			allowSelectAll: true,
		   			getBeforePut: false,
		   			columns: myColumns,
 	            loadingMessage: "<span class='tt2pmpGridLoading'>Loading items...</span>",
 	            noDataMessage: "No items found."
 	            
 	        }, this.balancedTreeGridDiv);
        	
        },
        _insertNewGoalClick : function(arguments) {
        	this._store.add({
        		name: "new item"
        		})//.then(lang.hitch(this, this.refreshGrid));
        		//.then(function(task){
        		//	this._store.notify(task, task.id)})
        },
        _formatTags: function(item){
     	   var tags = item.tags;
     	   var changedArray = array.map(tags, this._tagToString);
        	   return changedArray.join(", ");
        },
        _tagToString: function(tag){
     	   return tag.name;
        },
        resize: function(){
       	 this.inherited(arguments);
       	 this._grid.resize();
       },

        
    });
 
});