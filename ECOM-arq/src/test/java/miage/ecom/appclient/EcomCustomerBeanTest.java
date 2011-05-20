/*
 *  The MIT License
 * 
 *  Copyright 2011 Michaël Schwartz <m.schwartz@epokmedia.fr>.
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

import java.util.List;
import miage.ecom.auth.AuthIdentity;
import miage.ecom.entity.Product;
import miage.ecom.entity.Store;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.arquillian.api.Deployment;
import miage.ecom.auth.AuthResultCode;
import miage.ecom.auth.AuthResult;
import javax.ejb.EJB;
import miage.ecom.test.EcomBaseTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author smith
 */
@RunWith(Arquillian.class)
public class EcomCustomerBeanTest extends EcomBaseTest {

	@EJB
	public EcomCustomerRemote ecomCustomerBean;

	@Deployment
	public static JavaArchive createTestArchive() {
		return EcomBaseTest.getTestArchive(EcomCustomerBeanTest.class);
	}
	
	@Test
	public void testInjectBean() {
		assertNotNull(ecomCustomerBean);
	}

	@Test
	public void testInitialLogin() {
		assertEquals(ecomCustomerBean.getAuthResult().getMessage(), AuthResult.ERROR.getMessage());
	}

	@Test
	public void testAuthenticateFailed() {
		
		ecomCustomerBean.authenticate("test", "test");
		assertEquals(ecomCustomerBean.getAuthResult().getAuthResultCode(),AuthResultCode.INVALID_CREDENTIAL);

	}

	@Test
	public void testAuthenticateSuccess() {

		ecomCustomerBean.authenticate("smith", "testtest");
		AuthResult authResult = ecomCustomerBean.getAuthResult();
		AuthIdentity authIdentity = authResult.getIdentity();

		assertEquals(authResult.getAuthResultCode(),AuthResultCode.SUCCESS);
		assertNotNull(authIdentity);
		assertTrue(authIdentity.getValue().getLogin().equals("smith"));


	}

	
	@Test
	public void testStoreList() {
		
		List<Store> storesList = ecomCustomerBean.getStoresList();

		assertEquals(storesList.size(),2);

		assertEquals(storesList.get(0).getIdStore(),new Integer(1));
		assertEquals(storesList.get(0).getName(),"TestStore");


		assertEquals(storesList.get(1).getIdStore(),new Integer(2));
		assertEquals(storesList.get(1).getName(),"TestStore2");

	}


	@Test
	public void testProductList() {
		
		List<Product> productsList = ecomCustomerBean.getProductsList();

		assertEquals(productsList.size(), 2);

		assertEquals(productsList.get(0).getIdProduct(),new Integer(1));
		assertEquals(productsList.get(0).getName(),"Metal Gear Solid 4");


		assertEquals(productsList.get(1).getIdProduct(),new Integer(2));
		assertEquals(productsList.get(1).getName(),"Mortal Kombat");

	}


	@Test
	public void testProductListByStore() {
		
		List<Product> productsByStore = ecomCustomerBean.getProductsByStore(1);

		assertEquals(productsByStore.size(),1);

		assertEquals(productsByStore.get(0).getIdProduct(),new Integer(1));
		assertEquals(productsByStore.get(0).getName(), "Metal Gear Solid 4");

	}


	@Test
	public void testCartAdd() {

		assertEquals( ecomCustomerBean.getCartContents().size(),0);

		ecomCustomerBean.addProductToCart(1, 1);

		assertEquals( ecomCustomerBean.getCartContents().size(),1);

		assertEquals( ecomCustomerBean.getCartContents().get(0).getProduct().getName(), "Metal Gear Solid 4");
		Long totalValue = ecomCustomerBean.getTotalValue();
		
		assertEquals(totalValue, new Long(4000));
	}


	@Test
	public void testCartAddUnknownProduct() {

		assertEquals( ecomCustomerBean.getCartContents().size(),0);


		ecomCustomerBean.addProductToCart(100,1);


		assertEquals( ecomCustomerBean.getCartContents().size(),0);
	}
	
	
	@Test
	public void testCartRemoveUnknownProduct() {
		
		ecomCustomerBean.addProductToCart(1, 1);
		
		assertEquals( ecomCustomerBean.getCartContents().size(),1);
		
		ecomCustomerBean.removeProductFromCart(10, 1);
		
		assertEquals( ecomCustomerBean.getCartContents().size(),1);
		
	}
	
	
	@Test
	public void testCartRemove() {
		
		ecomCustomerBean.addProductToCart(1, 2);
		
		assertEquals( ecomCustomerBean.getCartContents().size(), 1);
		
		Integer quantity = ecomCustomerBean.getCartContents().get(0).getQuantity();
		assertEquals(quantity, new Integer(2));
		
		ecomCustomerBean.removeProductFromCart(1, 1);
		
		assertEquals( ecomCustomerBean.getCartContents().size(),1);
		
		ecomCustomerBean.removeProductFromCart(1, 1);
		
		assertEquals( ecomCustomerBean.getCartContents().size(),0);
		
	}
	
	
	@Test
	public void testGetProductByName() {
		List<Product> products = ecomCustomerBean.getProductsByName("Metal Gear Solid 4");
		
		assertEquals(products.size(), 1);
		
		products = ecomCustomerBean.getProductsByName("M");
		
		assertEquals(products.size(), 0);
	}
	
	
	@Test
	public void testGetProductByNameLike() {
		List<Product> products = ecomCustomerBean.getProductsByNameLike("Metal Gear%");
		
		assertEquals(products.size(), 1);
		
		products = ecomCustomerBean.getProductsByNameLike("M%");
		
		assertEquals(products.size(), 2);
	}
	
	
	@Test
	public void testGetProductByRangePrice() {
		List<Product> products = ecomCustomerBean.getProductsByRangePrice(4000L,6000L);
		
		assertEquals(products.size(), 2);
		
		products = ecomCustomerBean.getProductsByRangePrice(4000L,4000L);
		
		assertEquals(products.size(), 1);
	}
	
	
	
}
