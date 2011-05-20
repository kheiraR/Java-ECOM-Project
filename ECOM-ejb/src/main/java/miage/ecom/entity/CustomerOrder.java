package miage.ecom.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lou
 */
@Entity
@Table(name = "customer_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c"),
    @NamedQuery(name = "CustomerOrder.findByIdOrder", query = "SELECT c FROM CustomerOrder c WHERE c.idOrder = :idOrder"),
    @NamedQuery(name = "CustomerOrder.findByShippingAddress", query = "SELECT c FROM CustomerOrder c WHERE c.shippingAddress = :shippingAddress"),
    @NamedQuery(name = "CustomerOrder.findByCreationDate", query = "SELECT c FROM CustomerOrder c WHERE c.creationDate = :creationDate")
})
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_order", unique = true, nullable = false)
    private Integer idOrder;

    @Size(max = 200)
    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    
    @Column(name = "total_value")
    private Long totalValue;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "customerOrder")
    private Collection<Purchase> purchaseCollection;

    @JoinColumn(name = "id_status")
    @ManyToOne
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Long totalValue) {
        this.totalValue = totalValue;
    }

    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }
    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status idStatus) {
        this.status = idStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account idAccount) {
        this.account = idAccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.CustomerOrder[ idOrder=" + idOrder + " ]";
    }
    
}
