package miage.ecom.cart;

import java.util.Map;

/**
 *
 * @author mrabaris
 */

public interface Cart {

    public void addItem(int id, int quantity);
    public void removeItem(int id, int quantity);
    public Map<Integer,Integer> getItems();
    
}
