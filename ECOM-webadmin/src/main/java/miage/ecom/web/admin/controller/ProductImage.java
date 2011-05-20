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

import java.net.URL;
import java.util.Map;
import javax.ejb.EJB;
import miage.ecom.entity.Product;
import miage.ecom.session.CategoryFacadeLocal;
import miage.ecom.session.ProductFacadeLocal;
import miage.ecom.web.admin.service.ImgurImageManager;
import miage.ecom.web.admin.util.ExtJsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Controller
public class ProductImage {
	
	@EJB
	private ProductFacadeLocal productFacade;
	
	@Autowired
	private ImgurImageManager imageManager;
	
	@RequestMapping(value="/product/{id}/image", method=RequestMethod.POST)
	public ResponseEntity<String> create(@PathVariable("id") int productId, 
															 @RequestParam("image") MultipartFile image) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-type", "text/html");
   
		String success = null;
		String msg = null;
		String imageUrl = "";
		
		Product product = productFacade.find(productId);
		
		if (product == null) {
			success = "false";
			msg = "Ce produit n'existe pas / plus";
		}
		
		if (image.isEmpty()) {
			success = "false";
			msg = "Impossible de sauvegarder l'image";
		}
		
		if (!image.getContentType().contains("image")) {
			success = "false";
			msg = "Format de fichier non valide";
		}
		
		if (success == null) {
			try {
				URL thumbUrl = imageManager.uploadThumb(image.getInputStream(), 200);
			
				if (thumbUrl != null) {
					
					imageUrl = thumbUrl.toString();
					product.setImage(imageUrl);
					
					productFacade.edit(product);
					
					success = "true";
					
				} else {
					success = "false";
					msg = "Impossible de générer l'image";
				}
			} catch (Exception ex) {
				success = "false";
				msg = "Impossible de générer l'image : " + ex.getMessage();
			}
		}
		
		return new ResponseEntity<String>("{success:"+success+",msg:\""+msg+"\",image:\""+imageUrl+"\"}", responseHeaders, HttpStatus.OK);
	}
	
}
