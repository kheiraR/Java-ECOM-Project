/*
 *  The MIT License
 * 
 *  Copyright 2011 mrabaris.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package miage.ecom.appclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import miage.ecom.cart.Cart;
import miage.ecom.entity.Account;
import miage.ecom.entity.CustomerOrder;
import miage.ecom.entity.Product;
import miage.ecom.entity.Purchase;
import miage.ecom.entity.Status;
import miage.ecom.session.AccountFacadeLocal;
import miage.ecom.session.CustomerOrderFacadeLocal;
import miage.ecom.session.ProductFacadeLocal;
import miage.ecom.session.StatusFacadeLocal;

/**
 *
 * @author mrabaris
 */
@Stateless
public class EcomBeanFront implements EcomBeanFrontLocal {


    @EJB
    AccountFacadeLocal accountFacade;

    @EJB
    ProductFacadeLocal productFacade;

    @EJB
    StatusFacadeLocal statusFacade;

    @EJB
    CustomerOrderFacadeLocal customerOrderFacade;

     public List<Purchase> getCartContents(Cart cart) {
	

	List<Purchase> result = new ArrayList<Purchase>();
	Map<Integer, Integer> items = cart.getItems();
	if (items != null && !items.isEmpty()) {

	    for (Entry<Integer, Integer> entry : items.entrySet()) {
		Purchase purchase = new Purchase();
		purchase.setProduct(this.productFacade.find(entry.getKey()));
		purchase.setQuantity(entry.getValue());
		result.add(purchase);
	    }
	}
	return result;
    }



    public void addProductToCart(Cart cart, int idProduct, int quantity) {
	
	Product product = this.productFacade.find(idProduct);
	if (product != null) {
	    cart.addItem(idProduct, quantity);
	}
    }

    public void removeProductFromCart(Cart cart, int idProduct, int quantity) {
	Product product = this.productFacade.find(idProduct);
	if (product != null) {
	    cart.removeItem(idProduct, quantity);
	}
    }

    public void buyCartWith(Cart cart, String iban) {
	try {

	    Account account = this.accountFacade.findByIban(iban);
	    if (account != null) {


		Long totalValue = getTotalValue(cart);
		if (account.getBalance() > totalValue) {

		    CustomerOrder customerOrder = new CustomerOrder();
		    Status status = this.statusFacade.find(100);
		    customerOrder.setStatus(status);

		    customerOrder.setTotalValue(totalValue);
		    List<Purchase> purchases = this.getCartContents(cart);
		    for (Purchase p : purchases) {
			p.setCustomerOrder(customerOrder);
		    }
		    customerOrder.setPurchaseCollection(purchases);

		    account.setBalance(account.getBalance() - totalValue);
		    this.accountFacade.edit(account);
		    customerOrder.setAccount(account);
		    this.customerOrderFacade.create(customerOrder);

		}

	    }


	} catch (ConstraintViolationException cv) {
	    System.out.println("CONSTRAINT VIOLATION EXCEPTION : " + cv.getMessage());
	    Iterator it = cv.getConstraintViolations().iterator();
	    while (it.hasNext()) {
		ConstraintViolation c = (ConstraintViolation) it.next();
		System.out.println("CONSTRAINT VIOLATION : " + c);
            }


	} catch (Exception e) {
	    e.printStackTrace(System.err);

	}
    }

   


    @Override
    public Long getTotalValue(Cart cart) {
	Long totalVue = 0l;
	Map<Integer, Integer> items = cart.getItems();
	if (items != null && !items.isEmpty()) {

	    for (Entry<Integer, Integer> entry : items.entrySet()) {
		Product p = this.productFacade.find(entry.getKey());
		totalVue += p.getPrice() * entry.getValue();
	    }
	}
	return totalVue;
    }

}
