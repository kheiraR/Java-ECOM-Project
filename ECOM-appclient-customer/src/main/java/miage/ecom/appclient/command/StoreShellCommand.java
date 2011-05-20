/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.ecom.appclient.command;

import java.util.List;
import miage.ecom.appclient.EcomBeanRemote;
import miage.ecom.appclient.helpers.StoreHelper;
import miage.ecom.entity.Store;
import miage.shell.command.StoreCommand;


/**
 *
 * @author Lou
 */
public class StoreShellCommand extends StoreCommand {

    private final EcomBeanRemote ecomCustomerRemote;

    public StoreShellCommand(EcomBeanRemote ecomCustomerRemote) {
        this.ecomCustomerRemote = ecomCustomerRemote;
    }

    @Override
    public void defaultAction() {
        List<Store> storesList = ecomCustomerRemote.getStoresList();
        StoreHelper.print(this.getOut(), storesList);
        
    }
    
}
