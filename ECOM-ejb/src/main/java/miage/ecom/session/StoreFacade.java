/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.ecom.session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.ecom.entity.Product;
import miage.ecom.entity.Store;
import miage.ecom.entity.StoreHasProduct;

/**
 *
 * @author Lou
 */
@Stateless
public class StoreFacade extends AbstractFacade<Store> implements StoreFacadeLocal {
    @PersistenceContext(unitName = "EcomPersistenceUnit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public StoreFacade() {
        super(Store.class);
    }


    public List<Product> findProductsByStore(int idStore){
        List<Product> result = new ArrayList<Product>();
        Store store = this.find(idStore);
        if(store != null && store.getStoreHasProductsCollection() != null){
            for(StoreHasProduct storeHasProduct : store.getStoreHasProductsCollection()){
                result.add(storeHasProduct.getProduct());
            }
        }
        return result;
    }
}
