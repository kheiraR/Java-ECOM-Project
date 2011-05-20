
/**
 * Création des modèles
 */
var setupModels = function(storeBaseUrl) {
	
	Ext.define('Category', {
		extend: 'Ext.data.Model',
		fields: [
			{name: 'idCategory', type: 'int', useNull:true},
			{name: 'image', type: 'string'},
			{name: 'name', type: 'string'}
		],
		idProperty:'idCategory'
	});
	
	Ext.define('Product', {
		extend: 'Ext.data.Model',
		fields: [
			{name: 'idProduct', type: 'int', useNull:true},
			{name: 'image', type: 'string'},
			{name: 'description', type: 'string'},
			{name: 'reference', type: 'string'},
			{name: 'price', type: 'int', defaultValue: 0},
			{name: 'name', type: 'string'},
			{name: 'idCategory', type: 'int'}
		],
		validations: [
			{type: 'presence',  field: 'name'},
			{type: 'presence',  field: 'description'},
			{type: 'presence',  field: 'reference'},
			{type: 'presence',  field: 'price'},
			{type: 'presence',  field: 'idCategory'}
		],
		idProperty:'idProduct'
	});
	
	
	Ext.create('Ext.data.Store',{
		storeId:'storeCategory',
		autoSync:true,
		model: 'Category',
		proxy: {
			type: 'rest',
			url : storeBaseUrl + 'category',
			reader: {
				type: 'json',
				root: 'data',
				idProperty:'idCategory',
				messageProperty: 'message'
			},
			writer: {
				type: 'json'
			}
		},
		autoLoad: true
	});
	
	
	Ext.create('Ext.data.Store',{
		storeId:'storeProduct',
		autoSync:false,
		model: 'Product',
		proxy: {
			type: 'rest',
			url : storeBaseUrl + 'product',
			reader: {
				type: 'json',
				root: 'data',
				idProperty:'idProduct',
				messageProperty: 'message'
			},
			writer: {
				type: 'json',
				nameProperty: 'mapping'
			}
		},
		autoLoad: {start: 0, limit: 10},
		pageSize: 10
	});
	
}

/**
 * Création du dashboard
 */
var setupDashboard = function(baseUrl) {
	
	var tab = Ext.getCmp('tabDashboard');
	
	tab.add({
		title:'Produits',
		html:'<h1>Chargement...</h1>',
		loader:{
			renderer:'html',
			url:baseUrl + 'index/product',
			autoLoad:true
		}
	});
	
	tab.add({
		title:'Magasins',
		html:'<h1>Chargement...</h1>',
		loader:{
			renderer:'html',
			url:baseUrl + 'index/store',
			autoLoad:true
		}
	});
	
	tab.add({
		title:'Clients',
		html:'<h1>Chargement...</h1>',
		loader:{
			renderer:'html',
			url:baseUrl + 'index/customer',
			autoLoad:true
		}
	});

}

/**
 * Gestion des catégories
 */
var setupCategory = function() {
	
	var tab = Ext.getCmp('tabCategory');
	
	var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		clicksToEdit: 1
	});
	
	var categoryStore = Ext.data.StoreManager.lookup('storeCategory');
	
	tab.add({
		id:'gridCategory',
		xtype:'grid',
		store:'storeCategory',
		columns: [
			{text: 'ID',  dataIndex:'idCategory'},
			{
				text: 'Nom',
				dataIndex:'name',
				flex:1,
				editor: {
					xtype:'textfield',
					allowBlank:false
				}
			},
		],
		selType: 'rowmodel',
		plugins: [
			rowEditing
		],
		dockedItems: [{
			xtype: 'toolbar',
			dock: 'top',
			items: [
				{
					text: 'Ajouter une cat&eacute;gorie',
					handler: function(){
						// empty record
						categoryStore.insert(0, new Category());
						rowEditing.startEdit(0, 0);
					}
				}, '-', {
					text: 'Supprimer la selection',
					handler: function(){
						var selection = Ext.getCmp('gridCategory').getView().getSelectionModel().getSelection()[0];
						if (selection) {
							categoryStore.remove(selection);
						}
					}
				}
			]
		}]
	});
	
}


var setupProduct = function(baseUrl) {

	var tab = Ext.getCmp('tabProduct');

	var productStore = Ext.data.StoreManager.lookup('storeProduct');
	var categoryStore = Ext.data.StoreManager.lookup('storeCategory');
	
	var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit: 1
	});
	
	tab.add({
		id:'gridProduct',
		xtype:'grid',
		store:productStore,
		columns: [
			{text: 'ID',  dataIndex:'idProduct'},
			{
				text: 'Référence',
				dataIndex:'reference',
				editor: {
					xtype:'textfield',
					allowBlank:false
				}
			},
			{
				text: 'Nom',
				dataIndex:'name',
				flex:1,
				editor: {
					xtype:'textfield',
					allowBlank:false
				}
			},
			{
				text: 'Description',
				dataIndex:'description',
				flex:2,
				editor: {
					xtype:'textfield',
					allowBlank:false
				}
			},
			{
				text: 'Prix',
				renderer:function(value) {
					var numberRenderer = Ext.util.Format.numberRenderer('0.00');
					return numberRenderer((value / 100)) + " &euro;"
				},
				dataIndex:'price',
				editor: {
					xtype:'textfield',
					allowBlank:false
				}
			},
			{
				text: 'Catégorie',
				dataIndex:'idCategory',
				renderer: function(value) {
					var c = categoryStore.getById(value);
					return (c != null) ? c.get('name') : 'Aucune';
				},
				editor: {
					xtype:'combo',
					store: categoryStore,
					displayField: 'name',
					valueField: 'idCategory',
					allowBlank:false,
					forceSelection:true,
					listClass: 'x-combo-list-small'
				}
			},
			{
				xtype:'actioncolumn',
				items: [
					{
				
					icon: 'resources/images/icon-image-add.png',  // Use a URL in the icon config
					tooltip: 'Ajouter une image',
					handler: function(grid, rowIndex, colIndex) {
						var rec = grid.getStore().getAt(rowIndex);
						window.createUploadWindow(baseUrl, rec);
					}
				}
			]
			}
		],
		selType: 'cellmodel',
		plugins: [
			cellEditing
		],
		dockedItems: [{
			xtype: 'toolbar',
			dock: 'top',
			items: [
				{
					text: 'Ajouter un produit',
					handler: function(){
						productStore.insert(0, new Product());
					}
				}, '-', {
					text: 'Synchroniser',
					handler: function(){
						productStore.sync();
					}
				}, '-', 
				{
					text: 'Supprimer la selection',
					handler: function(){
						var selection = Ext.getCmp('gridProduct').getView().getSelectionModel().getSelection()[0];
						cellEditing.cancelEdit();
						if (selection) {
							productStore.remove(selection);
						}
					}
				}
			]
		}],
        // paging bar on the bottom
        bbar: Ext.create('Ext.PagingToolbar', {
            store: productStore,
            displayInfo: true,
            displayMsg: 'Affichage des produits {0} - {1} of {2}',
            emptyMsg: 'Pas de produit à afficher'
        })
	});

}

/**
 * ECOM ADMIN PANEL
 */
var ecomAdmin = function(baseUrl) {
   
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items: [
			{
				region:'north',
				xtype:'panel',
				layout:'column',
				items:[
					{
						columnWidth:0.8,
						html: '<h1 class="x-panel-header" style="font-size:24px;">ECOM Admin Panel</h1>',
						border: false,
						margin: '0 0 1 0'
					},
					{
						columnWidth:0.2,
						xtype:'button',
						text: 'Deconnexion',
						margin:'8 5 0 0',
						handler:function() {
							window.location.href = baseUrl + 'logout';
						}
					}
				]
			},
			{
				region:'center',
				xtype:'tabpanel',
				activeTab: 0,
				autoHeight:true,
				items: [
					{
						id:'tabDashboard',
						title: 'Tableau de bord',
						layout:{
							'type' : 'hbox'
						},
						defaults: {
							margin:'20px',
							border:true,
							height: 250,
							flex:1
						}
					},
					{
						id:'tabProduct',
						title: 'Produits',
						layout:'fit'
					},
					{
						id:'tabCategory',
						title: 'Categories',
						layout:'fit'
					},
					{
						id:'tabStore',
						title: 'Magasins'
					},
					{
						id:'tabCustomer',
						title: 'Clients'
					},
					{
						id:'tabOrder',
						title: 'Commandes'
					}
				]
			}
		]
	});
	
	setupModels(baseUrl);
	setupDashboard(baseUrl);
	setupCategory();
	setupProduct(baseUrl);
}
