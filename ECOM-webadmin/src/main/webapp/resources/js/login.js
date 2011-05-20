Ext.onReady(function(){
    Ext.tip.QuickTipManager.init();
 
	
	var submitForm = function(formPanel) {
		formPanel.getForm().submit({
 
                method:'POST',
 
                // Functions that fire (success or failure) when the server responds.
                // The server would actually respond with valid JSON,
                // something like: response.write "{ success: true}" or
 
                // response.write "{ success: false, errors: { reason: 'Login failed. Try again.' }}"
                // depending on the logic contained within your server script.
                // If a success occurs, the user is notified with an alert messagebox,
 
                // and when they click "OK", they are redirected to whatever page
                // you define as redirect.
 
                success:function(){
					window.location = 'index';
				},
 
				// Failure function, see comment above re: success and failure.
				// You can see here, if login fails, it throws a messagebox
				// at the user telling him / her as much.

				failure:function(form, action){
					if(action.failureType == 'server'){
						obj = Ext.JSON.decode(action.response.responseText);

						Ext.Msg.alert('Login Failed!', obj.errors.reason);
					}else{
						Ext.Msg.alert('Warning!', 'Authentication server is unreachable : ' + action.response.responseText);

					}
					formPanel.getForm().reset();
				}
 
            });
	}
	
 
    // Create a variable to hold our EXT Form Panel.
 
    // Assign various config options as seen.
    var login = Ext.create('Ext.form.Panel', {
		renderTo:'loginPanel',
        labelWidth:80,
        url:'j_spring_security_check',
        frame:true,
        title:'Please Login',
 
        defaultType:'textfield',
        width:300,
        height:150,
        monitorValid:true,
        // Specific attributes for the text fields for username / password.
        // The "name" attribute defines the name of variables sent to the server.
 
        items:[{
            fieldLabel:'Username',
            name:'j_username',
            allowBlank:false
        },{
            fieldLabel:'Password',
 
            name:'j_password',
            inputType:'password',
            allowBlank:false
        }],
 
        // All the magic happens after the user clicks the button
        buttons:[{
 
            text:'Login',
            formBind: true,
            // Function that fires when user clicks the button
            handler:function() {
				submitForm(login);
			}
        }]
    });
	
	var nav = new Ext.util.KeyNav(login.getEl(), {
		"enter" : function(e){
			submitForm(login);
		},
		scope : login
	});
 
});