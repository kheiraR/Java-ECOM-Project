package miage.ecom.appclient;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import miage.ecom.entity.Account;
import miage.ecom.entity.Admin;
import miage.ecom.entity.Product;
import miage.ecom.session.AdminFacadeLocal;

/**
 * Session bean stateful qui fourni le code métier
 * pour le client lourd admin
 *
 * Ce bean est en mode remote car il est accessible depuis le client lourd
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class EcomAdminBean extends AbstractEcomBean implements EcomAdminRemote {

    @EJB
    private AdminFacadeLocal adminFacade;
 
    private String adminName;

   
    @Override
    public void authenticate(String login, String password){
                Admin admin = this.adminFacade.authenticate(login, password);

		if (admin != null) {
			adminName = admin.getName();
		}
    }




       

        

    @Override
    public String getAdminName() {
        return adminName;
    }

    @Remove
    @Override
    public void removeBean(){

    }

    @Override
    public List<Account> getAccountList(){
        return this.accountFacade.findAll();
    }

    @Override
    public void saveProduct(Product p) {
	p.setCategory(categoryFacade.find(100));
	this.productFacade.insert(p);
    }
}
