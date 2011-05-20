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

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import miage.ecom.entity.Category;
import miage.ecom.session.CategoryFacadeLocal;
import miage.ecom.web.admin.util.ExtJsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Controller
public class CategoryController {
	
	@EJB
	private CategoryFacadeLocal categoryFacade;
	
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> create(@RequestBody Category category) {
		
		Map<String, Object> output = null;
		
		try {
			
			categoryFacade.create(category);
			output = ExtJsUtil.getEditSuccessResponse(category, "Catégorie " + category.getIdCategory() + " créee");

		} catch (Exception e) {

			output = ExtJsUtil.getErrorResponse("Impossible de sauvegarder la catégorie");
		}
		
		return output;
	}
	
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> view() {

		try{

			List<Category> categories = categoryFacade.findAll();

			return ExtJsUtil.getListResponse(categories);

		} catch (Exception e) {

			return ExtJsUtil.getErrorResponse("Impossible de charger les catégories");
		}
	}
	
	@RequestMapping(value="/category/{idCategory}", method=RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> update(
					@PathVariable Integer idCategory,
					@RequestBody Category category) {
		
		Map<String, Object> output = null;
		
		try {
			
			if (categoryFacade.find(idCategory) != null && category.getIdCategory() == idCategory) {
				
				categoryFacade.edit(category);
				output = ExtJsUtil.getEditSuccessResponse(category, "Catégorie " + idCategory + " mise à jour");
				
			} else {
				
				output = ExtJsUtil.getErrorResponse("Cette catégorie n'existe pas");
				
			}
			
		} catch (Exception e) {

			output = ExtJsUtil.getErrorResponse("Impossible de sauvegarder la catégorie");
		}
		
		return output;
	}
	
	@RequestMapping(value="/category/{idCategory}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String,? extends Object> delete(
					@PathVariable Integer idCategory,
					@RequestBody Category category) {
		
		Map<String, Object> output = null;
		
		try {
			
			if (categoryFacade.find(idCategory) != null && category.getIdCategory() == idCategory) {
				
				categoryFacade.remove(category);
				output = ExtJsUtil.getEditSuccessResponse(category, "Catégorie " + idCategory + " supprimée");
				
			} else {
				
				output =  ExtJsUtil.getErrorResponse("Cette catégorie n'existe pas");
				
			}
			
		} catch (Exception e) {

			output =  ExtJsUtil.getErrorResponse("Impossible de sauvegarder la catégorie");
		}
		
		return output;
	}
	
}
