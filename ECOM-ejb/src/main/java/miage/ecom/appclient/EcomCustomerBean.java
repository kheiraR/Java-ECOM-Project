package miage.ecom.appclient;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import miage.ecom.auth.AuthIdentity;
import miage.ecom.auth.AuthResult;
import miage.ecom.auth.AuthResultCode;
import miage.ecom.entity.Customer;
import miage.ecom.session.CustomerFacadeLocal;

/**
 * Session bean stateful qui fourni le code métier
 * pour le client lourd customer
 * 
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class EcomCustomerBean extends AbstractEcomBean implements EcomCustomerRemote {

        

	@EJB
	private CustomerFacadeLocal customerFacade;
	private AuthResult authResult = AuthResult.ERROR;
        
        

	@Override
	public void authenticate(String login, String password) {

		Customer customer = this.customerFacade.authenticate(login, password);
		
		if (customer != null) {
			authResult = new AuthResult("", AuthResultCode.SUCCESS, new AuthIdentity(customer));
		} else {
			authResult = new AuthResult("Nom d'utilisateur ou mot de passe incorrect", AuthResultCode.INVALID_CREDENTIAL, null);
		}

	}

	@Override
	public AuthResult getAuthResult() {
		return this.authResult;
	}
        
        
        
        @Remove
        @Override
        public void removeBean(){

        }

        


}
