package miage.ecom.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "admin_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminLog.findAll", query = "SELECT a FROM AdminLog a"),
    @NamedQuery(name = "AdminLog.findByIdLog", query = "SELECT a FROM AdminLog a WHERE a.idLog = :idLog"),
    @NamedQuery(name = "AdminLog.findByComment", query = "SELECT a FROM AdminLog a WHERE a.comment = :comment")
})
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_log")
    private Integer idLog;

    @Size(max = 45)
    @Column(name = "comment")
    private String comment;

    @JoinColumn(name = "id_action", referencedColumnName = "id_action")
    @ManyToOne(optional = false)
    private Action idAction;

    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private Type idType;

    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    @ManyToOne(optional = false)
    private Admin idAdmin;

    public AdminLog() {
    }

    public AdminLog(Integer idLog) {
        this.idLog = idLog;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Action getIdAction() {
        return idAction;
    }

    public void setIdAction(Action idAction) {
        this.idAction = idAction;
    }

    public Type getIdType() {
        return idType;
    }

    public void setIdType(Type idType) {
        this.idType = idType;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminLog)) {
            return false;
        }
        AdminLog other = (AdminLog) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.ecom.entity.AdminLog[ idLog=" + idLog + " ]";
    }
    
}
