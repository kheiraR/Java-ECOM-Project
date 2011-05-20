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

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import miage.ecom.cart.CartBean;
import miage.ecom.session.CustomerFacadeLocal;
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
public class LoginController extends ApplicationController {

    @EJB
    CustomerFacadeLocal customerFacade;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String formSetup(Model model, HttpSession session) {
	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	    session.setAttribute("cart", cart);
	}

	model.addAttribute("cartTotalValue", ecomBeanFrontLocal.getTotalValue(cart));
	model.addAttribute("nbProducts", ecomBeanFrontLocal.getCartContents(cart).size());
	return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupForm(Model model, HttpSession session) {
	CartBean cart;
	if (session.getAttribute("cart") == null) {
	    cart = new CartBean();
	} else {
	    cart = (CartBean) session.getAttribute("cart");
	    session.setAttribute("cart", cart);
	}

	model.addAttribute("cartTotalValue", ecomBeanFrontLocal.getTotalValue(cart));
	model.addAttribute("nbProducts", ecomBeanFrontLocal.getCartContents(cart).size());
	return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processSubmit(
	    @RequestParam(value = "firstname") String firstname,
	    @RequestParam(value = "lastname") String lastname,
	    @RequestParam(value = "login") String login,
	    @RequestParam(value = "password") String password,
	    @RequestParam(value = "password_confirmation") String passwordConfirmation,
	    @RequestParam(value = "address") String address,
	    @RequestParam(value = "iban") String iban,
	    Model model) {

	boolean error = false;
	ArrayList<String> errors = new ArrayList<String>();

	HashMap<String, String> customerInfo = new HashMap<String, String>();
	customerInfo.put("firstname", firstname);
	customerInfo.put("lastname", lastname);
	customerInfo.put("login", login);
	customerInfo.put("password", password);
	customerInfo.put("address", address);
	customerInfo.put("iban", iban);

	if (firstname.equals("") || lastname.equals("") || login.equals("") || address.equals("") || iban.equals("")) {
	    errors.add("The fields cannot be blank");
	    error = true;
	}

	if ((password == null) || (passwordConfirmation == null) || password.equals("") || passwordConfirmation.equals("") || !password.equals(passwordConfirmation)) {
	    errors.add("Incorrect password");
	    error = true;
	}

	if (iban.length() > 34 || iban.length() == 0) {
	    errors.add("IBAN is not valid");
	    error = true;
	}

	if (error == true) {
	    model.addAttribute("errors", errors);
	    model.addAttribute("customer", customerInfo);
	    return "signup";
	}
	
	customerFacade.signup(customerInfo, true);

	model.addAttribute("success", "Signup is successful");
	return "redirect:/";
    }
}
