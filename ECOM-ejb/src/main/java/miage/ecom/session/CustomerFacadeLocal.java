/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.ecom.session;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import miage.ecom.entity.Customer;

/**
 *
 * @author Lou
 */
@Local
public interface CustomerFacadeLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();

    public Customer authenticate(String login, String password);

	public Customer findByLogin(String login);
	
	public void signup(HashMap<String, String> customerInfo, boolean active);
}
