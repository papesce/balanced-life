define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_AttachMixin",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/form/Button",
    "dgrid/Grid",
    "dgrid/Tree",
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
    "dijit/form/Select",
    "dojo/store/JsonRest",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dojo/date/locale",
    "dijit/registry",
    "dojo/text!./templates/listGrid.html"
], function(declare, _WidgetBase, 
		_AttachMixin,
		_TemplatedMixin,
		_WidgetsInTemplateMixin, 
		Button,
		Grid, Tree,
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
		Select, JsonRest,
		array,
		lang, locale, registry, template) {

    
	//var rpcJsonRest = lang.getObject("dojox.rpc.JsonRest", true);
	
	return declare([_WidgetBase, _AttachMixin, _TemplatedMixin, _WidgetsInTemplateMixin
	                ], {
        templateString: template,
        
        _grid : null,
        _gridSelection: {},
        _store: null,
        _doneStore: null,
        _tagStore: null,
        
		constructor : function() {
        	  this.inherited(arguments);
        	  this.id = "balancedLifeListGrid";
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
        	  this._filterByTagSelect.startup();
        	  this._addRowButton.startup();
        },
        _initButtons : function(arguments) {
        	 this._addRowButton = new Button({
        	        label: "Insert New",
        	        onClick: lang.hitch(this, this._insertNewTaskClick)
        	    }, this.addRowInListButtonDiv);
        	 this._tagStore = new JsonRest({
        		 target: "rest/tags",
        		 idProperty: "tagId"
        	 });
        	 var filterTagStore = new JsonRest({
        		 target: "rest/tags/filter/",
        		 idProperty: "tagId"
        	 });
        	 this._filterByTagSelect = new Select({
        		 	store: filterTagStore,
        		 	labelAttr : 'name',
        		 	style: 'width: 100%',
        	 	},  this.filterByTagDiv);
        	 this._filterByTagSelect.on('change', lang.hitch(this, this._filterSelectChanged));
        	 this._removeRowButton = new Button({
     	        label: "Remove Selected",
     	        onClick: lang.hitch(this, this._removeTaskClick)
     	    }, this.removeRowButtonDiv);
        	 this._markAsDoneButton = new Button({
      	        label: "Mark as Done",
      	        onClick: lang.hitch(this, this._markAsDoneTaskClick)
      	    }, this.markAsDoneButtonDiv);
        	 this._refreshButton = new Button({
       	        label: "Refresh",
       	        onClick: lang.hitch(this, this._refreshClick)
       	    }, this.refreshButtonDiv);

        },
        _refreshClick: function() {
        	this._grid.refresh();
        },
        _filterSelectChanged : function(value) {
        	this._grid.set('collection', this._store.filter({ tagId: value}));
        },
        _insertNewTaskClick : function(arguments) {
        	this._store.add({
        		name: "new item"
        		})//.then(lang.hitch(this, this.refreshGrid));
        		//.then(function(task){
        		//	this._store.notify(task, task.id)})
        },
        _removeTaskClick : function(arguments) {
        	var selection = this._getGridSelection();
        	for (var int = 0; int < selection.length; int++) {
       	     	var item = selection[int];  	
       	     	this._store.remove(item.itemId);       	               		 
        	};
        },
        _markAsDoneTaskClick : function(arguments) {
        	var selection = this._getGridSelection();
        	for (var int = 0; int < selection.length; int++) {
       	     	var item = selection[int];  	
       	     	this._doneStore.put(item);
       	     	//this._store.remove(item.itemId);
        	};
        },
        _initGrid : function(arguments) {
        	
        	this._doneStore = new JsonRest({
       		 	target: "rest/items/markDone/",
       		 	idProperty: "itemId"
       	 	});        	
   	   
        	this._restStore = new declare([Rest, SimpleQuery, Trackable])({
    		   target: 'rest/items/',
    		   useRangeHeaders: true,
   				idProperty: "itemId"
        	});
   	   
        	var cachedStore = Cache.create(this._restStore, {
        		cachingStore: new Memory()
        	});
        	this._store = cachedStore;
   	   
        	var myColumns = [
        	          { label: "ID", field: "itemId"},
   	                  { label: "Name", field: "name", editor: "text" , autoSave: true,
   	                	editOn : "dblclick", autoSelect : true },
   	                  { label: "Tags", field: "tagString", editor: "text",
							get: lang.hitch(this, this._formatTags),
							//set: lang.hitch(this, this._editTags),
							autoSave: true, editOn : "dblclick"	},
					  {label : "Associations", field:"associationString", editor: "text", 
   	 						get: lang.hitch(this, this._formatAssociations),
   	                		autoSave: true, editOn: "dblclick"},
   	                	{label : "Created", field:"creationDate", "formatter": this._formatDate}			
   	                		
   	   					
   	   				];
   	   this._addPartitions(myColumns);
   	   
       this._grid = new (declare([OnDemandGrid, Keyboard, Selection,  DijitRegistry, ColumnResizer,  Editor]))({
       	    			collection: 	this._store,
       		   			selectionMode: "toggle",    
//       		   			allowSelectAll: true,
       		   			getBeforePut: false,
       		   			columns: myColumns,
        	            loadingMessage: "<span class='tt2pmpGridLoading'>Loading tasks...</span>",
        	            noDataMessage: "No tasks found."
        	            
        	        }, this.listGridDiv);
      	   this._grid.on("dgrid-select", lang.hitch(this, function (event) {
       		   this._updateSelection(event.rows, true);
//       		   this._updateButtons();
       	   }));
       	   this._grid.on("dgrid-deselect", lang.hitch(this, function (event) {
       		   this._updateSelection(event.rows, false);
//       		   this._updateButtons();
       	   }));
       	   
       },
       _formatTags: function(item){
    	   var tags = item.tags;
    	   var changedArray = array.map(tags, this._tagToString);
       	   return changedArray.join(", ");
    	   //return changedArray;
       },
       _formatAssociations: function(item){
    	   var associations = item.associations;
    	   var changedArray = array.map(associations, this._associationToString);
       	   return changedArray.join(", ");
       },
       _formatDate: function(jsDate) {
    	    var d = new Date(jsDate);
    	    var today = new Date();
    	    //d.setHours(0,0,0,0);
    	    today.setHours(d.getHours(),d.getMinutes(),d.getSeconds(), d.getMilliseconds());
    	    if (today.getTime() === d.getTime()) 
    	    	return "today";
    	    return locale.format(d, {formatLength: 'short'});
       },
       _editTags: function(task){
//			TODO: warning to the user if new tags are going to be added.    	   
//    	   var parts = task.tagString.split(',');
//    	   for (var int = 0; int < parts.length; int++) {
//    		   var tagString = parts[int];
//    		   this._analyzeTags(task.tags, tagString, function(tag
//    				   ){
//    			   
//    		   });
//    		   newTags.push(tag);
//    		   console.log(tagString);
//    	   }
//    	   delete task.tagString;
       },
      
//       _searchTag: function(tags, tagName) {
//    	   this._tagStore.query("?tagName=" + tagName.trim()).then(function(tasks){
//    		   
//    	   });
//       },
       _addPartitions: function(columns){
    	   //asume: partitions = { Partition{name: temporal, tags: {today, tomorrow}}} 

    	   var partitionColumn = {
    		label: 'When',
    		field: 'temporal',
    		get: function(task){
    			//intersection between tags and 
    			for (var int = 0; int < task.tags.length; int++) {
					var tag = task.tags[int];
					if (tag.name == "tomorrow") 
						{return "tomorrow";}
					else if (tag.name == "today")
						{return "today";}
				};
    			return "";
    		},
    		editor: Select,
    		editorArgs: {
    			options: [{value: "", label: ""},
    			          {value: "today", label: "today"}, {value: "tomorrow", label: "tomorrow"} ],
    			style: { width: "99%" },
    		},
    	   editOn : "dblclick"
    	   };
    	   
    	   columns.push(partitionColumn);
       },
       _tagToString: function(tag){
    	   return tag.name;
       },
       _associationToString: function(association){
    	   var assocMetadata = association.associationMetadata;
    	   var assocName = assocMetadata.name;
    	   var target = association.target;
    	   var targetId = target.itemId;
    	   return "("+ assocName +"," + targetId +")";
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