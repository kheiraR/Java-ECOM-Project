package miage.ecom.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lou
 */
@Entity
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findByIdType", query = "SELECT t FROM Type t WHERE t.idType = :idType"),
    @NamedQuery(name = "Type.findByName", query = "SELECT t FROM Type t WHERE t.name = :name")
})
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_type")
    private Integer idType;

    @Size(max = 45)
    @Column(name = "name")
    private String name;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idType")
    private Collection<AdminLog> adminLogCollection;

    public Type() {
    }

    public Type(Integer idType) {
        this.idType = idType;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<AdminLog> getAdminLogCollection() {
        return adminLogCollection;
    }

    public void setAdminLogCollection(Collection<AdminLog> adminLogCollection) {
        this.adminLogCollection = adminLogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.Type[ idType=" + idType + " ]";
    }
    
}
