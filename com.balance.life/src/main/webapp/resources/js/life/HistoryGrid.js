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
    "dojo/date/locale",
    "dojo/_base/lang",
    "dojo/_base/array",
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
		Button,
		OnDemandGrid,
		Keyboard,
		Selection,
		DijitRegistry,
		ColumnResizer,
		Editor,
		locale, lang, array, 
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
        	
        	this._restStore = new declare([Rest, SimpleQuery, Trackable])({
     		   target: 'rest/history/',
     		   useRangeHeaders: true,
    				idProperty: "itemId"
         	});
    	   
         	var cachedStore = Cache.create(this._restStore, {
         		cachingStore: new Memory()
         	});
         	this._store = cachedStore;
        	
         	var myColumns = [  { label: "ID", field: "itemId"},
          	                  { label: "Name", field: "name"},
          	                {label : "Associations", field:"associationString",  
         	 						formatter: lang.hitch(this, this._formatAssociations)},
          	                  { label: "Completed Time", field: "timestamp",
          	                		formatter: lang.hitch(this, this._formatTimestamp)
          	                	} 
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
        _formatAssociations: function(assocString, item){
     	   var associations = item.associations;
     	   var changedArray = array.map(associations, this._associationToString);
        	   return changedArray.join(", ");
        },
        _associationToString: function(association){
     	   var assocMetadata = association.associationMetadata;
     	   var assocName = assocMetadata.name;
     	   var target = association.target;
     	   var targetId = target.itemId;
     	   return "("+ assocName +"," + targetId +")";
        },
        _formatTimestamp: function(dateValue) {
        	return locale.format(new Date(dateValue),{
        	    selector: "date",
        	    formatLength: "short"
        	  });
        },
        resize: function(){
       	 this.inherited(arguments);
       	 this._grid.resize();
       },

        
    });
 
});