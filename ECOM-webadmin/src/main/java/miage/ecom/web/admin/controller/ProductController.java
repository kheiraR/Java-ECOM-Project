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
package miage.ecom.web.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import miage.ecom.entity.Product;
import miage.ecom.session.CategoryFacadeLocal;
import miage.ecom.session.ProductFacadeLocal;
import miage.ecom.web.admin.util.ExtJsUtil;
import miage.ecom.web.view.vo.ProductVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Controller
public class ProductController {
	
	@EJB
	private ProductFacadeLocal productFacade;
	
	@EJB
	private CategoryFacadeLocal categoryFacade;
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> create(@RequestBody ProductVo vo) {
		
		Map<String, Object> output = null;
		
		try {
			
			Product entity = new Product();
			
			vo.populate(entity);
			
			entity = productFacade.createNewProduct(entity, categoryFacade.find(vo.getIdCategory()));
			
			vo.extract(entity);
			
			output = ExtJsUtil.getEditSuccessResponse(vo, "Produit " + entity.getIdProduct() + " crée");

		} catch (Exception e) {

			output = ExtJsUtil.getErrorResponse("Impossible de sauvegarder le produit");
		}
		
		return output;
	}
	
	@RequestMapping(value="/product",method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> view( @RequestParam int limit, 
															@RequestParam int start) throws Exception {

		try{

			List<Product> records = productFacade.findAll(start,limit);
			
			List<ProductVo> valueObjects = new ArrayList<ProductVo>();
			for(Product record : records) {
				ProductVo vo = new ProductVo(record);
				vo.setIdCategory(record.getCategory().getIdCategory());
				valueObjects.add(vo);
			}
			
			return ExtJsUtil.getListResponse(valueObjects, productFacade.count());

		} catch (Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> update(
					@PathVariable Integer id,
					@RequestBody ProductVo vo) {
		
		Map<String, Object> output = null;
		
		try {
			Product record = productFacade.find(id);
			
			if (record != null && vo.getIdProduct() == id) {
				
				vo.populate(record);
				record.setCategory(categoryFacade.find(vo.getIdCategory()));
				
				productFacade.edit(record);
				output = ExtJsUtil.getEditSuccessResponse(vo, "Produit " + id + " mis à jour");
				
			} else {
				
				output = ExtJsUtil.getErrorResponse("Ce produit n'existe pas");
				
			}
			
		} catch (Exception e) {

			output = ExtJsUtil.getErrorResponse("Impossible de sauvegarder le produit");
		}
		
		return output;
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String,? extends Object> delete(
					@PathVariable Integer id,
					@RequestBody ProductVo vo) throws Exception {
		
		Map<String, Object> output = null;
		
		try {
			Product record = productFacade.find(id);
			
			if (record != null && vo.getIdProduct() == id) {
				
				productFacade.remove(record);
				output = ExtJsUtil.getEditSuccessResponse(vo, "Produit " + id + " supprimé");
				
			} else {
				
				output =  ExtJsUtil.getErrorResponse("Ce produit n'existe pas");
				
			}
			
		} catch (Exception e) {

			output =  ExtJsUtil.getErrorResponse("Impossible de sauvegarder le produit");
		}
		
		return output;
	}
	
}
