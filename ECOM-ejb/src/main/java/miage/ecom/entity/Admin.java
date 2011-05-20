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
@Table(name = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findByIdAdmin", query = "SELECT a FROM Admin a WHERE a.idAdmin = :idAdmin"),
    @NamedQuery(name = "Admin.findByName", query = "SELECT a FROM Admin a WHERE a.name = :name"),
    @NamedQuery(name = "Admin.findByLogin", query = "SELECT a FROM Admin a WHERE a.login = :login"),
    @NamedQuery(name = "Admin.findByPassword", query = "SELECT a FROM Admin a WHERE a.password = :password"),
	@NamedQuery(name = "Admin.findByCredentials", query = "SELECT a FROM Admin a WHERE a.login = :login AND a.password = :password")
})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_admin")
    private Integer idAdmin;

    @Size(max = 45)
    @Column(name = "name")
    private String name;

    @Size(max = 45)
    @Column(name = "login")
    private String login;

    @Size(max = 45)
    @Column(name = "password")
    private String password;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdmin")
    private Collection<AdminLog> adminLogCollection;

    public Admin() {
    }

    public Admin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        hash += (idAdmin != null ? idAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.idAdmin == null && other.idAdmin != null) || (this.idAdmin != null && !this.idAdmin.equals(other.idAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.Admin[ idAdmin=" + idAdmin + " ]";
    }
    
}
