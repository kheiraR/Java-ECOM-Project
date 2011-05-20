/*
 * The MIT License
 *
 * Copyright 2011 Lou.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package miage.ecom.web.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import miage.ecom.cart.CartBean;
import miage.ecom.entity.Category;
import miage.ecom.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Lou
 */
@Controller
public class ProductController extends ApplicationController {

    public ProductController() {
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public String product(@PathVariable("productId") Integer productId, Model model, HttpSession session) {
	model.addAttribute("product", productFacadeLocal.find(productId));

	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	    session.setAttribute("cart", cart);
	}

	model.addAttribute("cartTotalValue", ecomBeanFrontLocal.getTotalValue(cart));
	model.addAttribute("nbProducts", ecomBeanFrontLocal.getCartContents(cart).size());
	List<Category> categories = categoryFacade.findAll();
	model.addAttribute("categories", categories);

	model.addAttribute("products", productFacadeLocal.findAll());

	return "product";
    }

    @RequestMapping(value = "/products/store/{storeId}", method = RequestMethod.GET)
    public String productsForStore(@PathVariable("storeId") Integer storeId, Model model, HttpSession session) {
	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	    session.setAttribute("cart", cart);
	}

	model.addAttribute("cartTotalValue", ecomBeanFrontLocal.getTotalValue(cart));
	model.addAttribute("nbProducts", ecomBeanFrontLocal.getCartContents(cart).size());
	List<Category> categories = categoryFacade.findAll();
	model.addAttribute("categories", categories);

	List<Product> products = storeFacadeLocal.findProductsByStore(storeId);
	model.addAttribute("products", products);
	model.addAttribute("store", storeFacadeLocal.find(storeId));
	
	return "store";
    }
}
