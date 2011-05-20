package miage.ecom.entity;

import java.io.Serializable;

/**
 *
 * @author Lou
 */

public class StoreHasProductPK implements Serializable {

    private static final long serialVersionUID = -4521117802599702503L;
	
    
    private int store;

    private int product;

    public StoreHasProductPK() {
    }

    public StoreHasProductPK(int store, int product) {
        this.store = store;
        this.product = product;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StoreHasProductPK other = (StoreHasProductPK) obj;
        if (this.store != other.store) {
            return false;
        }
        if (this.product != other.product) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.store;
        hash = 37 * hash + this.product;
        return hash;
    }

    
    

    @Override
    public String toString() {
        return "miage.ecom.entity.StoreHasProductPK[ idStore=" + store + ", idProduct=" + product + " ]";
    }
    
}
