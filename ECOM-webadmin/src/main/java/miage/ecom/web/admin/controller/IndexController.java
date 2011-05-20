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
package miage.ecom.web.admin.controller;

import javax.ejb.EJB;
import miage.ecom.session.CustomerFacadeLocal;
import miage.ecom.session.ProductFacadeLocal;
import miage.ecom.session.StoreFacadeLocal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lou
 */
@Controller
public class IndexController {
    
	@EJB
	private ProductFacadeLocal productFacade;
	
	@EJB
	private CustomerFacadeLocal customerFacade;
	
	@EJB
	private StoreFacadeLocal storeFacade;
	
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home() {
		return "index";
    }
    
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		return "login";
    }
	
	
	@RequestMapping(value = "/index/product", method = RequestMethod.GET)
    public @ResponseBody String productIndex() {
		return "<h1 style='font-size:32px;padding:20px;'>"+productFacade.count()+"<br/><span style='font-size:22px;'>produit(s)</span></h1>";
    }
    
	@RequestMapping(value = "/index/customer", method = RequestMethod.GET)
    public @ResponseBody String customerIndex() {
		return "<h1 style='font-size:32px;padding:20px;'>"+customerFacade.count()+"<br/><span style='font-size:22px;'>client(s)</span></h1>";
    }
	
	@RequestMapping(value = "/index/store", method = RequestMethod.GET)
    public @ResponseBody String storeIndex() {
		return "<h1 style='font-size:32px;padding:20px;'>"+storeFacade.count()+"<br/><span style='font-size:22px;'>magasin(s)</span></h1>";
    }
}
