package miage.ecom.appclient;

import javax.ejb.Remote;
import miage.ecom.auth.AuthResult;

/**
 * Interface définissant les méthodes pouvant être appelé a distance
 * pour l'application cliente customer
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Remote
public interface EcomCustomerRemote extends EcomBeanRemote {

	public AuthResult getAuthResult();
    
}
