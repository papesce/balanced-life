define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_AttachMixin",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/form/Button",
    "dgrid/Grid",
    "dgrid/OnDemandGrid",
    "dgrid/Selection",
    "dgrid/Keyboard",
    "dgrid/extensions/ColumnResizer", 
    "dstore/Cache",
    'dstore/Trackable',
    "dstore/Memory",
    "dstore/Rest",
    "dstore/SimpleQuery",
    "dgrid/Editor",   
    "dgrid/extensions/DijitRegistry",
    "dijit/layout/ContentPane",
    "dijit/form/ValidationTextBox",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dijit/registry",
    "dojo/text!./templates/taskGrid.html"
], function(declare, _WidgetBase, 
		_AttachMixin,
		_TemplatedMixin,
		_WidgetsInTemplateMixin, 
		Button,
		Grid, 
		OnDemandGrid, Selection, 
		Keyboard, ColumnResizer, 
		Cache,
		Trackable,
		Memory, 
		Rest,
		SimpleQuery,
		Editor,
		DijitRegistry, 
		ContentPane,
		ValidationTextBox,
		array,
		//locale,
		lang, registry, template) {

    
	//var rpcJsonRest = lang.getObject("dojox.rpc.JsonRest", true);
	
	return declare([_WidgetBase, _AttachMixin, _TemplatedMixin, _WidgetsInTemplateMixin
	                ], {
        templateString: template,
        
        _grid : null,
        _gridSelection: {},
        _store: null,
          
		constructor : function() {
        	  this.inherited(arguments);
        	  this.id = "staffingPlansContentPane";
        },
        
        postCreate: function(){
        	// Run any parent postCreate processes - can be done at any point
			this.inherited(arguments);
			this._initButtons(arguments);
			this._initGrid(arguments);
        },
        
        startup: function() {
        	  this.inherited(arguments);
        	  //this._grid.set('collection', this._store);
        	  this._grid.startup();
        	  this._addRowButton.startup();
        },
        _initButtons : function(arguments) {
        	 this._addRowButton = new Button({
        	        label: "Insert New Task",
        	        onClick: lang.hitch(this, this._insertNewTaskClick)
        	    }, this.addRowButtonDiv);
        	 this._removeRowButton = new Button({
     	        label: "Remove Task",
     	        onClick: lang.hitch(this, this._removeTaskClick)
     	    }, this.removeRowButtonDiv);
        },
        
        _insertNewTaskClick : function(arguments) {
        	this._store.add({
        		name: "new task"
        		})//.then(lang.hitch(this, this.refreshGrid));
        		//.then(function(task){
        		//	this._store.notify(task, task.id)})
        },
        _removeTaskClick : function(arguments) {
        	var selection = this._getGridSelection();
        	for (var int = 0; int < selection.length; int++) {
       	     	var item = selection[int];  	
       	     	this._store.remove(item.taskId);       	               		 
        	};
        },
        _initGrid : function(arguments) {
        	
   	   var restStore = new declare([Rest, SimpleQuery, Trackable])({
    		   target: 'rest/tasks/',
    		   useRangeHeaders: true,
   				idProperty: "taskId"
   		});
   	   var cachedStore = Cache.create(restStore, {
   		   cachingStore: new Memory()
   	   });
   	   //this._store = restStore;
   	   this._store = cachedStore;
   	   
   	   var myColumns = [
   	                  { label: "Name", field: "name", editor: "text" , autoSave: true,
   	                	editOn : "dblclick", autoSelect : true },
   	                  { label: "Tags", field: "tagString", formatter: lang.hitch(this, this._formatTags),
   	                		editor: "text" , 
   	                		autoSave: true, editOn : "dblclick"}];
   	   
       this._grid = new (declare([OnDemandGrid, Keyboard, Selection,  DijitRegistry, ColumnResizer, Editor]))({
       	    			collection: 	this._store,
       		   			selectionMode: "toggle",    
//       		   			allowSelectAll: true,
       		   			getBeforePut: false,
       		   			columns: myColumns,
        	            loadingMessage: "<span class='tt2pmpGridLoading'>Loading tasks...</span>",
        	            noDataMessage: "No tasks found."
        	            
        	        }, this.taskGridDiv);
      	   this._grid.on("dgrid-select", lang.hitch(this, function (event) {
       		   this._updateSelection(event.rows, true);
//       		   this._updateButtons();
       	   }));
       	   this._grid.on("dgrid-deselect", lang.hitch(this, function (event) {
       		   this._updateSelection(event.rows, false);
//       		   this._updateButtons();
       	   }));
       	   
       },
       _formatTags: function(tagString, task){
    	   var tags = task.tags;
    	   var changedArray = array.map(tags, this._tagToString);
       	   return changedArray.join(", ");
    	   //return this._tagToString("x");
       },

       _tagToString: function(tag){
    	   return tag.name;
       },
        refreshGrid : function() {
        	this._grid.refresh();
        },
        resize: function(){
        	 this.inherited(arguments);
        	 this._grid.resize();
        },
        _updateSelection: function (rows, select) {
    		   for (var int = 0; int < rows.length; int++) {
     			   var row = rows[int];
        			   if (select)
        				   this._gridSelection[row.id] = row.data;
        			   else
        				   this._gridSelection[row.id] = null;
    		   };
         },
         _getGridSelection: function () {
    		 	var selection = [];
    		 	for (var prop in this._gridSelection) {
    		 		var item = this._gridSelection[prop]; 
    		 		if (item) {
    		 			selection.push(item);
    		 		};
    		 	}
    		 	return selection;

         },
        
        
    });
 
});