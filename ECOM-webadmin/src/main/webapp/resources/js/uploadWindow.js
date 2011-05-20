(function() {
	
	window.createUploadWindow = function(baseUrl, record) {
		
	

		var image = Ext.create('Ext.Img', {
					src: record.get('image') ? record.get('image') : 'http://placehold.it/150x150&text=image',
					id:'productImage',
					padding:'20',
					border:true,
					width:150,
					height:150
			});
			
		var form = Ext.create('Ext.form.Panel', {
			bodyPadding: '10 10 0',
			defaults: {
				anchor: '100%',
				allowBlank: false,
				msgTarget: 'side',
				labelWidth: 50
			},
			items: [
				{
					xtype: 'filefield',
					id: 'productImageUploadField',
					emptyText: 'Choisir une image',
					fieldLabel: 'Photo',
					name: 'image',
					buttonText: 'Parcourir',
					listeners: {
						'change': function(fb, v){
							if(form.getForm().isValid()){
								form.submit({
									url: baseUrl + 'product/' + record.get('idProduct') + '/image',
									waitMsg: 'Chargement en cours...',
									waitTitle: 'Chargement',
									success: function(fp, o) {
										image.setSrc(o.result.image);
										record.set('image', o.result.image);
									},
									failure: function(fp, o) {
										console.log(o);
										Ext.Msg.alert('Status',  o.result.msg);
									}
								});
							}
						}
					}
				}
			]
	    });

		Ext.create('Ext.window.Window', {
			id:'uploadWindow',
			width: 400,
			height:300,
			title: 'Gestion de l\'image produit',
			bodyPadding: '10 10 0',
			resizable:false,
			modal:true,
			layout:{type:'vbox', align:'center'},
			items:[
				image,
				form
			],
			buttons: [
				{
					text: 'Annuler',
					handler: function(){
						Ext.getCmp('uploadWindow').close();
					}
				}
			]
		}).show();
		
		
	}
	
})();