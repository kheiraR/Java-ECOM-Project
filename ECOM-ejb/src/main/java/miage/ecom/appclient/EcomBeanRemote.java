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

import java.util.List;
import miage.ecom.entity.Product;
import miage.ecom.entity.Purchase;
import miage.ecom.entity.Store;

/**
 *
 * @author MRABARIS
 */
public interface EcomBeanRemote {

    public void authenticate(String login, String password);


    public List<Store> getStoresList();

    public List<Product> getProductsList();

    public List<Product> getProductsByStore(int idStore);

    public List<Product> getProductsByName(String name);

    public List<Product> getProductsByNameLike(String name);

    public List<Product> getProductsByRangePrice(Long priceMin, Long priceMax);

    

    
    public void beginTransaction()  throws Exception;


    public void commitTransaction()  throws Exception;


    public void rollbackTransaction()  throws Exception;




    /*
     * GESTION CART
     */

    public List<Purchase> getCartContents();

    public void addProductToCart(int idProduct, int quantity);

    public void removeProductFromCart(int idProduct, int quantity);

    public void buyCartWith(String iban);

    public List<String> getCartMessages();

    public Long getTotalValue();



    /**
     * Output format
     */

    public String getOutput();

    public void setOutput(String output);

    public void removeBean();
}
