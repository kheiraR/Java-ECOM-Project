package miage.ecom.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maria Rabarison
 */

public class CartBean implements Cart{

    private Map<Integer, Integer> items;
 

    public CartBean() {
        items = new HashMap<Integer, Integer>();
    }

    public void addItem(int idProduit, int quantity){

            if(this.items.containsKey(idProduit)){
                this.items.put(idProduit, this.items.get(idProduit) + quantity);
            }else{
                this.items.put(idProduit, quantity);
            }
    }

    
    public void removeItem(int idProduit, int quantity){

            if(this.items.containsKey(idProduit)){
                int currentQuantity = this.items.get(idProduit);
                if(quantity < currentQuantity){
                    this.items.put(idProduit, currentQuantity - quantity);
                }else{
                    this.items.remove(idProduit);
                }
            }
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }


    

    
}
