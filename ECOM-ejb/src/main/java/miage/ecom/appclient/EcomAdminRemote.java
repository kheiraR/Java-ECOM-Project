package miage.ecom.appclient;

import java.util.List;
import javax.ejb.Remote;
import miage.ecom.entity.Account;
import miage.ecom.entity.Product;

/**
 * Interface définissant les méthodes pouvant être appelé a distance
 * pour l'application cliente admin
 * 
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Remote
public interface EcomAdminRemote  extends EcomBeanRemote{

    public String getAdminName();
    
    public List<Account> getAccountList();

    public void saveProduct(Product product);
}
