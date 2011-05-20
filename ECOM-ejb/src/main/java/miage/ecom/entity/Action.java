/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
    @NamedQuery(name = "Action.findByIdAction", query = "SELECT a FROM Action a WHERE a.idAction = :idAction"),
    @NamedQuery(name = "Action.findByLabel", query = "SELECT a FROM Action a WHERE a.label = :label")
})
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_action")
    private Integer idAction;

    @Size(max = 45)
    @Column(name = "label")
    private String label;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAction")
    private Collection<AdminLog> adminLogCollection;

    public Action() {
    }

    public Action(Integer idAction) {
        this.idAction = idAction;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.Action[ idAction=" + idAction + " ]";
    }
    
}
