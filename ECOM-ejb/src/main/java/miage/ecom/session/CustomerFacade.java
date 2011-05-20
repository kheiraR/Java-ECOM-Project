/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.ecom.session;

import java.util.HashMap;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import miage.ecom.entity.Account;
import miage.ecom.entity.Customer;
import miage.ecom.entity.Status;

/**
 *
 * @author Lou
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {

    @PersistenceContext(unitName = "EcomPersistenceUnit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    public Customer authenticate(String login, String password) {
        TypedQuery<Customer> findByCredentials = this.em.createNamedQuery("Customer.findByCredentials", Customer.class);
        findByCredentials.setParameter("login", login);
        findByCredentials.setParameter("password", password);

        LinkedList<Customer> resultList = new LinkedList<Customer>(findByCredentials.getResultList());

        if (!resultList.isEmpty()) {
            return resultList.getFirst();
        } else {
            return null;
        }
    }
	
	
	@Override
    public Customer findByLogin(String login) {
        TypedQuery<Customer> findByCredentials = this.em.createNamedQuery("Customer.findByLogin", Customer.class);
        findByCredentials.setParameter("login", login);

        LinkedList<Customer> resultList = new LinkedList<Customer>(findByCredentials.getResultList());

        if (!resultList.isEmpty()) {
            return resultList.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public void signup(HashMap<String, String> customerInfo, boolean active) {
	Account account = new Account();
	account.setIban(customerInfo.get("iban"));
	account.setStatus(new Status(100));
	this.getEntityManager().persist(account);

	Customer customer = new Customer();
	customer.setFirstname(customerInfo.get("firstname"));
	customer.setLastname(customerInfo.get("lastname"));
	customer.setLogin(customerInfo.get("login"));
	customer.setPassword(customerInfo.get("password"));
	customer.setAddress(customerInfo.get("address"));
	customer.setIdAccount(account);

	this.getEntityManager().persist(customer);
    }
	
}
