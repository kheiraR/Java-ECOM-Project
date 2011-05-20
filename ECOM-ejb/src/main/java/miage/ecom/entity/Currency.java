package miage.ecom.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lou
 */
@Entity
@Table(name = "currency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
    @NamedQuery(name = "Currency.findByIdCurrency", query = "SELECT c FROM Currency c WHERE c.idCurrency = :idCurrency"),
    @NamedQuery(name = "Currency.findByCode", query = "SELECT c FROM Currency c WHERE c.code = :code"),
    @NamedQuery(name = "Currency.findByLabel", query = "SELECT c FROM Currency c WHERE c.label = :label")
})
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_currency")
    private Integer idCurrency;

    @Size(max = 45)
    @Column(name = "code")
    private String code;

    @Size(max = 45)
    @Column(name = "label")
    private String label;

    public Currency() {
    }

    public Currency(Integer idCurrency) {
        this.idCurrency = idCurrency;
    }

    public Integer getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Integer idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurrency != null ? idCurrency.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.idCurrency == null && other.idCurrency != null) || (this.idCurrency != null && !this.idCurrency.equals(other.idCurrency))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.Currency[ idCurrency=" + idCurrency + " ]";
    }
    
}
