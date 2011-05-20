/*
 *  The MIT License
 * 
 *  Copyright 2011 MRABARIS.
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
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import miage.ecom.cart.Cart;
import miage.ecom.cart.CartBean;
import miage.ecom.entity.Account;
import miage.ecom.entity.CustomerOrder;
import miage.ecom.entity.Product;
import miage.ecom.entity.Purchase;
import miage.ecom.entity.Status;
import miage.ecom.entity.Store;
import miage.ecom.session.AccountFacadeLocal;
import miage.ecom.session.CategoryFacadeLocal;
import miage.ecom.session.CustomerOrderFacadeLocal;
import miage.ecom.session.ProductFacadeLocal;
import miage.ecom.session.StatusFacadeLocal;
import miage.ecom.session.StoreFacadeLocal;
import miage.ecom.session.StoreHasProductFacadeLocal;

/**
 *
 * @author MRABARIS
 */
public abstract class AbstractEcomBean {

    @EJB
    protected StoreFacadeLocal storeFacade;
    @EJB
    protected StoreHasProductFacadeLocal storeHasProductFacade;
    @EJB
    protected ProductFacadeLocal productFacade;
    @Resource
    protected UserTransaction ux;
    @EJB
    protected CustomerOrderFacadeLocal customerOrderFacade;
    @EJB
    protected AccountFacadeLocal accountFacade;
    @EJB
    protected StatusFacadeLocal statusFacade;
    @EJB
    protected CategoryFacadeLocal categoryFacade;
    protected String output;
    protected Cart cart;
    protected List<String> cartErrors;

    public abstract void authenticate(String login, String password);

    public List<Store> getStoresList() {
	return this.storeFacade.findAll();
    }

    /**
     *
     * PRODUCTS
     */
    public List<Product> getProductsList() {
	return this.productFacade.findAll();
    }

    public List<Product> getProductsByName(String name) {
	return this.productFacade.findProductsByName(name);
    }

    public List<Product> getProductsByNameLike(String name) {
	return this.productFacade.findProductsByNameLike(name);
    }

    public List<Product> getProductsByRangePrice(Long priceMin, Long priceMax) {
	return this.productFacade.findProductsByRangePrice(priceMin, priceMax);
    }

    public void beginTransaction() throws Exception {
	this.ux.begin();
    }

    public void commitTransaction() throws Exception {
	this.ux.commit();
    }

    public void rollbackTransaction() throws Exception {
	this.ux.rollback();
    }

    public List<Product> getProductsByStore(int idStore) {
	return this.storeFacade.findProductsByStore(idStore);
    }

    public String getOutput() {
	return output;
    }

    public void setOutput(String output) {
	this.output = output;
    }

    public List<Purchase> getCartContents() {
	ensureCustomerOrderNotNull();

	List<Purchase> result = new ArrayList<Purchase>();
	Map<Integer, Integer> items = this.cart.getItems();
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

    

    public void addProductToCart(int idProduct, int quantity) {
	ensureCustomerOrderNotNull();
	Product product = this.productFacade.find(idProduct);
	if (product != null) {
	    this.cart.addItem(idProduct, quantity);
	    cartErrors.add("Product added");
	} else {
	    cartErrors.add("Unknown product");
	}
    }

    public void removeProductFromCart(int idProduct, int quantity) {
	ensureCustomerOrderNotNull();
	Product product = this.productFacade.find(idProduct);
	if (product != null) {
	    this.cart.removeItem(idProduct, quantity);
	    cartErrors.add("Product removed");
	} else {
	    cartErrors.add("Unknown product");
	}
    }

    public void buyCartWith(String iban) {
	ensureCustomerOrderNotNull();
	try {

	    Account account = this.accountFacade.findByIban(iban);
	    if (account != null) {


		Long totalValue = getTotalValue();
		if (account.getBalance() > totalValue) {

		    CustomerOrder customerOrder = new CustomerOrder();
		    Status status = this.statusFacade.find(100);
		    customerOrder.setStatus(status);

		    customerOrder.setTotalValue(totalValue);
		    List<Purchase> purchases = getCartContents();
		    for (Purchase p : purchases) {
			p.setCustomerOrder(customerOrder);
		    }
		    customerOrder.setPurchaseCollection(purchases);

		    account.setBalance(account.getBalance() - totalValue);
		    this.accountFacade.edit(account);
		    customerOrder.setAccount(account);
		    this.customerOrderFacade.create(customerOrder);

		    cartErrors.add("Cart buyed");
		} else {
		    cartErrors.add("Not enough amount in the account");
		}

	    } else {
		cartErrors.add("Unknown Account");
	    }


	} catch (ConstraintViolationException cv) {
	    System.out.println("CONSTRAINT VIOLATION EXCEPTION : " + cv.getMessage());
	    Iterator it = cv.getConstraintViolations().iterator();
	    while (it.hasNext()) {
		ConstraintViolation c = (ConstraintViolation) it.next();
		System.out.println("CONSTRAINT VIOLATION : " + c);
		cartErrors.add(c.getMessage());
	    }


	} catch (Exception e) {
	    e.printStackTrace(System.err);

	}
    }

    protected void ensureCustomerOrderNotNull() {
	clearCartErrors();
	if (cart == null) {
	    cart = new CartBean();
	}
    }

    public List<String> getCartMessages() {
	return this.cartErrors;
    }

    public Long getTotalValue() {
	ensureCustomerOrderNotNull();
	Long totalVue = 0l;
	Map<Integer, Integer> items = this.cart.getItems();
	if (items != null && !items.isEmpty()) {

	    for (Entry<Integer, Integer> entry : items.entrySet()) {
		Product p = this.productFacade.find(entry.getKey());
		totalVue += p.getPrice() * entry.getValue();
	    }
	}
	return totalVue;
    }

    protected void clearCartErrors() {
	if (cartErrors == null) {
	    cartErrors = new ArrayList<String>();
	} else {
	    this.cartErrors.clear();
	}

    }
}
