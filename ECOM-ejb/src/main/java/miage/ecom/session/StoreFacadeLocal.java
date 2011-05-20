/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.ecom.session;

import java.util.List;
import javax.ejb.Local;
import miage.ecom.entity.Product;
import miage.ecom.entity.Store;

/**
 *
 * @author Lou
 */
@Local
public interface StoreFacadeLocal {

    void create(Store store);

    void edit(Store store);

    void remove(Store store);

    Store find(Object id);

    List<Store> findAll();

    List<Store> findRange(int[] range);

    int count();

    List<Product> findProductsByStore(int idStore);
    
}
