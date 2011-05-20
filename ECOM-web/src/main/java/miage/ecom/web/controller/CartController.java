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
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import miage.ecom.appclient.EcomBeanFrontLocal;
import miage.ecom.cart.CartBean;
import miage.ecom.entity.Customer;
import miage.ecom.entity.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lou
 */
@Controller
public class CartController extends ApplicationController {

    public CartController() {
    }

    @RequestMapping(value = "/cart")
    public String index(Model model, HttpSession session) {
	
	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	    session.setAttribute("cart", cart);
	}
	
	List<Purchase> cartContents = ecomBeanFrontLocal.getCartContents(cart);
	model.addAttribute("cartTotalValue", ecomBeanFrontLocal.getTotalValue(cart));
	model.addAttribute("nbProducts", ecomBeanFrontLocal.getCartContents(cart).size());
	model.addAttribute("products", cartContents);
	return "cart";
    }

    
    
    
    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public String addProductToCart(
	    @RequestParam(value = "idProduct") Integer productId,
	    @RequestParam(value = "quantity") Integer quantity,
	    HttpSession session) {

	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	}
	ecomBeanFrontLocal.addProductToCart(cart, productId, quantity);
	session.setAttribute("cart", cart);
	return "redirect:/cart";
    }

    
    
    
    @RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
    public String checkout(HttpSession session, Model model) {
	Customer customer = (Customer) session.getAttribute("customer");
	if (customer == null) {
	    model.addAttribute("loginError", "You have to be logged in in order to checkout");
	    return "login";
	}
	
	return "home";
    }

    
    
    
    @RequestMapping(value = "/cart/remove", method = RequestMethod.POST)
    public String removeProduct(@RequestParam(value = "idProduct") Integer idProduct, HttpSession session) {
	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	}
	ecomBeanFrontLocal.removeProductFromCart(cart, idProduct, 1);
	session.setAttribute("cart", cart);
	return "redirect:/cart";
    }
    
    
}
