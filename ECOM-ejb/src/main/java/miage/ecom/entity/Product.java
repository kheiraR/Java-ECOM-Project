package miage.ecom.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lou
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByIdProduct", query = "SELECT p FROM Product p WHERE p.idProduct = :idProduct"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByReference", query = "SELECT p FROM Product p WHERE p.reference = :reference"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByRangePrice", query = "SELECT p FROM Product p WHERE p.price <= :priceMax AND p.price >= :priceMin"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByDescriptionLike", query = "SELECT p FROM Product p WHERE p.description LIKE :description"),
    @NamedQuery(name = "Product.findByNameLike", query = "SELECT p FROM Product p WHERE p.name LIKE :name"),
    @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category.id = :idCategory")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_product", unique = true, nullable = false)
    private Integer idProduct;

    @Size(max = 45)
    @Column(name = "name")
    private String name;

    @Size(max = 45)
    @Column(name = "reference")
    private String reference;

    @Column(name = "price")
    private Long price;

    @Size(max = 200)
    @Column(name = "description")
    private String description;

    @Size(max = 200)
    @Column(name = "image")
    private String image;

    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy="product")
    private Collection<Purchase> purchaseCollection;

    @OneToMany(mappedBy = "product")
    private Collection<StoreHasProduct> storeHasProductsCollection;

    public Product() {
    }

    public Product(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category idCategory) {
        this.category = idCategory;
    }

    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    @XmlTransient
    public Collection<StoreHasProduct> getStoreHasProductsCollection() {
        return storeHasProductsCollection;
    }

    public void setStoreHasProductsCollection(Collection<StoreHasProduct> storeHasProductsCollection) {
        this.storeHasProductsCollection = storeHasProductsCollection;
    }


	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.Product[ idProduct=" + idProduct + " ]";
    }
    
}
