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
package miage.ecom.session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import miage.ecom.entity.Category;
import miage.ecom.entity.Product;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "EcomPersistenceUnit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public void insert(Product product) {
        try{
            Product result = this.em.merge(product);
            if(result == null){
                this.create(product);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	
	@Override
	public Product createNewProduct(Product product, Category category) {
		
		
		product.setCategory(category);
		
		return em.merge(product);
		
	}

    
    public List<Product> findProductsByName(String name){
        List<Product> result = new ArrayList<Product>();
        TypedQuery<Product> findByCredentials = this.em.createNamedQuery("Product.findByName", Product.class);
		findByCredentials.setParameter("name", name);
        LinkedList<Product> resultList = new LinkedList<Product>(findByCredentials.getResultList());
        if(!resultList.isEmpty()){
            result.addAll(resultList);
        }
        return result;
    }

    public List<Product> findProductsByNameLike(String name){
        List<Product> result = new ArrayList<Product>();
        TypedQuery<Product> findByCredentials = this.em.createNamedQuery("Product.findByNameLike", Product.class);
	findByCredentials.setParameter("name", name);
        LinkedList<Product> resultList = new LinkedList<Product>(findByCredentials.getResultList());
        if(!resultList.isEmpty()){
            result.addAll(resultList);
        }
        return result;
    }

    public List<Product> findProductsByDescriptionLike(String description){
        List<Product> result = new ArrayList<Product>();
        TypedQuery<Product> findByDescriptionLike = this.em.createNamedQuery("Product.findByDescriptionLike", Product.class);
	findByDescriptionLike.setParameter("description", description);
        LinkedList<Product> resultList = new LinkedList<Product>(findByDescriptionLike.getResultList());
        if(!resultList.isEmpty()){
            result.addAll(resultList);
        }
        return result;
    }

    public List<Product> findProductsByRangePrice(Long priceMin, Long priceMax){
        List<Product> result = new ArrayList<Product>();

        TypedQuery<Product> findByCredentials = this.em.createNamedQuery("Product.findByRangePrice", Product.class);
	findByCredentials.setParameter("priceMax", priceMax);
        if(priceMin == null || priceMin <= 0l){
            findByCredentials.setParameter("priceMin", 0l);
        }else{
            findByCredentials.setParameter("priceMin", priceMin);
        }
        LinkedList<Product> resultList = new LinkedList<Product>(findByCredentials.getResultList());
        if(!resultList.isEmpty()){
            result.addAll(resultList);
        }
        return result;
    }


	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findAll(int start, int limit) {
		
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(Product.class));
		return getEntityManager().createQuery(cq).setMaxResults(limit).setFirstResult(start).getResultList();
		
	}

    public List<Product> findProductsByCategory(Integer idCategory) {
	List<Product> result = new ArrayList<Product>();
	TypedQuery<Product> findByCategory = this.em.createNamedQuery("Product.findByCategory", Product.class);
	findByCategory.setParameter("idCategory", idCategory);
        LinkedList<Product> resultList = new LinkedList<Product>(findByCategory.getResultList());
        if(!resultList.isEmpty()){
            result.addAll(resultList);
        }
	return result;
    }

}
