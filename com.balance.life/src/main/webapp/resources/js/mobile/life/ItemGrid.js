define([
        "dojo/_base/declare",
        "dijit/_WidgetBase",
        "dijit/_AttachMixin",
        "dijit/_TemplatedMixin",
         "dojox/mobile/EdgeToEdgeStoreList",
         "dojox/mobile/LongListMixin",
         "dojo/store/Memory",
         "dojo/store/JsonRest",
         "dojo/text!./templates/itemGrid.html"
         ], function(declare, _WidgetBase, 
        			_AttachMixin,
        			_TemplatedMixin, 
        		 EdgeToEdgeStoreList, LongListMixin,
        		 Memory, Rest, template) {
	return declare([_WidgetBase, _AttachMixin, _TemplatedMixin
	                ], {
		
		templateString: template,
		
	    _grid : null,
        _store: null,
	    
		constructor : function() {
      	  this.inherited(arguments);
      	  this.id = "mobileItemContentPane";
      },

		
		postCreate: function(){
        	// Run any parent postCreate processes - can be done at any point
			this.inherited(arguments);
			this._initGrid(arguments);
		},
		
        startup: function() {
      	  this.inherited(arguments);
      	  //this._grid.set('store', this._store);
      	  //alert("refresh;");
      	  this._grid.startup();
        },
        
        _initGrid : function(arguments) {
	    	   
        	 //var items = [];
             //for(var i = 0; i < 1000; i++){
             //        items.push({ label: "Item "+i });
             //}
             //var static_data = {
             //        items: items
             //};
             // store for the dojox/mobile/EdgeToEdgeStoreList
            // var restStore = new Memory({idProperty:"label", data: static_data});
        		var restStore = new Rest({
	    		   target: 'rest/items/mobile',
	    		   useRangeHeaders: true,
	   			   idProperty: "itemId"
	   			});
	       	this._store = restStore;

			this._grid =
                new declare([EdgeToEdgeStoreList, LongListMixin])(
                        {
                        	store: this._store,
                        	itemMap:{name:"label", tagString: "rightText"}
                        	},
                        this.itemListDiv);	
	 		
	 	}
        
	});
});	
