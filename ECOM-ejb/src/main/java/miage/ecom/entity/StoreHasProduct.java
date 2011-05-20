package miage.ecom.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lou
 */
@Entity
@Table(name = "store_has_product")
@XmlRootElement
@IdClass(StoreHasProductPK.class)
@NamedQueries({
    @NamedQuery(name = "StoreHasProduct.findAll", query = "SELECT s FROM StoreHasProduct s"),
    @NamedQuery(name = "StoreHasProduct.findByIdStore", query = "SELECT s FROM StoreHasProduct s WHERE s.store = :id_store"),
    @NamedQuery(name = "StoreHasProduct.findByIdProduct", query = "SELECT s FROM StoreHasProduct s WHERE s.product = :id_product"),
    @NamedQuery(name = "StoreHasProduct.findByQuantity", query = "SELECT s FROM StoreHasProduct s WHERE s.quantity = :quantity")
})

public class StoreHasProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Column(name = "quantity")
    private Integer quantity;

    @Id
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", insertable = false, updatable = false)
    @ManyToOne
    private Product product;

    @Id
    @JoinColumn(name = "id_store", referencedColumnName = "id_store", insertable = false, updatable = false)
    @ManyToOne
    private Store store;

    public StoreHasProduct() {
    }

    

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (product != null  && store != null ? product.hashCode() + store.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreHasProduct)) {
            return false;
        }
        StoreHasProduct other = (StoreHasProduct) object;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals(other.product))) {
            return false;
        }

        if ((this.store == null && other.store != null) || (this.store != null && !this.store.equals(other.store))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.StoreHasProduct[ storeHasProductPK=" + store.getIdStore() + "_"+ product.getIdProduct()+ " ]";
    }
    
}
